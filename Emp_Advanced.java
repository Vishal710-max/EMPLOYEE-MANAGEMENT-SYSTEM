import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; 
import javax.swing.table.DefaultTableModel;
 
 
public class Emp_Advanced extends JFrame {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    JTextField idField, nameField, desigField, salaryField, deleteIdField;
    JTable employeeTable;
    JScrollPane scrollPane;

    public Emp_Advanced() {
        connectToDB();

        setTitle("Employee Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("➕ Add Employee", createAddEmployeePanel());
        tabbedPane.addTab("🗑️ Delete Employee", createDeleteEmployeePanel());
        tabbedPane.addTab("📋 View Employees", createViewEmployeePanel());

        add(tabbedPane);
        setVisible(true);
    }

    private JPanel createAddEmployeePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(220, 240, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 14);
        Dimension fieldDim = new Dimension(200, 30);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel[] labels = {
            new JLabel("Employee ID:"), new JLabel("Name:"),
            new JLabel("Designation:"), new JLabel("Salary:")
        };

        for (JLabel label : labels) {
            label.setFont(labelFont);
        }

        idField = new JTextField(); nameField = new JTextField();
        desigField = new JTextField(); salaryField = new JTextField();

        JTextField[] fields = {idField, nameField, desigField, salaryField};
        for (JTextField field : fields) {
            field.setFont(fieldFont);
            field.setPreferredSize(fieldDim);
        }

        gbc.gridx = 0; gbc.gridy = 0; panel.add(labels[0], gbc);
        gbc.gridx = 1; panel.add(idField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(labels[1], gbc);
        gbc.gridx = 1; panel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(labels[2], gbc);
        gbc.gridx = 1; panel.add(desigField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(labels[3], gbc);
        gbc.gridx = 1; panel.add(salaryField, gbc);

        JButton addButton = new JButton("Add Employee");
        addButton.setFont(fieldFont);
        addButton.setBackground(new Color(0, 153, 51));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> addEmployee());

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        return panel;
    }

    private JPanel createDeleteEmployeePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40));
        panel.setBackground(new Color(255, 240, 240));

        JLabel label = new JLabel("Enter Employee ID to Delete:");
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        deleteIdField = new JTextField(10);
        deleteIdField.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        deleteButton.setBackground(new Color(204, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteEmployee());

        panel.add(label);
        panel.add(deleteIdField);
        panel.add(deleteButton);

        return panel;
    }

    private JPanel createViewEmployeePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        employeeTable = new JTable();
        employeeTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        employeeTable.setRowHeight(24);

        scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Employee Records"));

        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        refreshButton.setBackground(new Color(0, 102, 204));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.addActionListener(e -> viewEmployees());

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);

        return panel;
    }

    private void connectToDB() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "dbms");
            System.out.println("✅ Connected to database.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Database Connection Failed: " + e.getMessage());
        }
    }

    private void addEmployee() {
        try {
            String sql = "INSERT INTO employees (id, name, designation, salary) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(idField.getText()));
            pstmt.setString(2, nameField.getText());
            pstmt.setString(3, desigField.getText());
            pstmt.setDouble(4, Double.parseDouble(salaryField.getText()));

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "✅ Employee added successfully.");

            // Clear fields
            idField.setText(""); nameField.setText("");
            desigField.setText(""); salaryField.setText("");

            viewEmployees(); // Refresh table
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "❌ Error adding employee: " + ex.getMessage());
        }
    }

    private void deleteEmployee() {
        try {
            String sql = "DELETE FROM employees WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(deleteIdField.getText()));
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "🗑️ Employee deleted successfully.");
                deleteIdField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ No employee found with that ID.");
            }

            viewEmployees(); // Refresh table
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "❌ Error deleting employee: " + ex.getMessage());
        }
    }

    private void viewEmployees() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"ID", "Name", "Designation", "Salary"});

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("designation"),
                        rs.getDouble("salary")
                });
            }

            employeeTable.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "❌ Error fetching employees: " + ex.getMessage());
        }
    }

   /* public static void main(String[] args) {
        SwingUtilities.invokeLater(Emp_Advanced::new);
    }
}*/
