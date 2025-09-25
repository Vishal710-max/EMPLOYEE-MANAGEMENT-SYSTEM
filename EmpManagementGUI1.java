import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/*
public class EmpManagementGUI extends JFrame {
	// Database components 
	Connection con;
	Statement st; 
	ResultSet rs; 
	PreparedStatement ps;
	DatabaseMetaData dbm;

	// GUI components 
	JTabbedPane tabs;
	JTextField nameInput, idInput, salaryInput, idDelete;
	JButton addBtn, DeleteEmp;
	JTable table;
	int displayTabIndex;

	EmpManagementGUI() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");

			setSize(500, 600);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			tabs = new JTabbedPane();
			tabs.addTab("👨‍💻Add New Employee", addEmployeeGUI());
			tabs.addTab("🗑️Delete Employee", deleteEmployeeGUI());
			JPanel displayPanel = displayAll();
			displayTabIndex = tabs.getTabCount();
			
			//	for (int i = 0; i < tabs.getTabCount(); i++) {
			//	    if (tabs.getTitleAt(i).equals("📋Display")) {
			//	        displayTabIndex = i;
			//	        break;
			//	    }
			//	}
			tabs.addTab("📋Display", displayPanel);

			add(tabs);
			
			
		} catch (Exception error) {
			System.out.println("An error Occured.." + error);
		}
	}

	private JPanel addEmployeeGUI() {
		JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

		idInput = new JTextField(15);
		nameInput = new JTextField(15);
		salaryInput = new JTextField(15);

		panel.add(new JLabel("Enter Id:"));
		panel.add(idInput);
		panel.add(new JLabel("Enter Name:"));
		panel.add(nameInput);
		panel.add(new JLabel("Enter Salary:"));
		panel.add(salaryInput);

		addBtn = new JButton("Add");
		panel.add(new JLabel(""));
		panel.add(addBtn);

		addBtn.addActionListener(e -> insertData());

		outerPanel.add(panel);
		return outerPanel;
	}

	void insertData() {
		try {
			int id = Integer.parseInt(idInput.getText().trim());
			String name = nameInput.getText().trim();
			int salary = Integer.parseInt(salaryInput.getText().trim());

			PreparedStatement ps = con.prepareStatement("INSERT INTO Employee (id, name, salary) VALUES (?, ?, ?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, salary);

			int rowsInserted = ps.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(null, "✅ Employee added successfully!");
				idInput.setText("");
				nameInput.setText("");
				salaryInput.setText("");
				refreshDisplayTab();
			} else {
				JOptionPane.showMessageDialog(null, "⚠️ Failed to add employee.");
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "❌ Please enter valid numeric values for ID and Salary.");
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, "❌ Something went wrong: " + error.getMessage());
			error.printStackTrace();
		}
	}

	private JPanel deleteEmployeeGUI() {
		JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

		panel.add(new JLabel("Enter Id To delete:"));
		idDelete = new JTextField(15);
		panel.add(idDelete);
		DeleteEmp = new JButton("🗑️Delete");
		panel.add(new JLabel(""));
		panel.add(DeleteEmp);

		DeleteEmp.addActionListener(e -> deleteId());
		outerPanel.add(panel);
		return outerPanel;
	}

	void deleteId() {
		try {
			int id = Integer.parseInt(idDelete.getText().trim());
			PreparedStatement ps = con.prepareStatement("DELETE FROM Employee WHERE id = ?");
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				JOptionPane.showMessageDialog(null, "✅ Employee deleted successfully!");
				idDelete.setText("");
				refreshDisplayTab();
			} else {
				JOptionPane.showMessageDialog(null, "⚠️ No Employee found with ID: " + id);
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "❌ Please enter a valid numeric ID!");
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, "❌ Error deleting employee: " + error.getMessage());
			error.printStackTrace();
		}
	}

	JPanel displayAll() {
		JPanel panel = new JPanel(new BorderLayout());
		String[] columns = {"ID", "Name", "Salary"};
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		table = new JTable(model);

		try {
			ps = con.prepareStatement("SELECT * FROM Employee");
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int salary = rs.getInt("salary");
				model.addRow(new Object[]{id, name, salary});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
			e.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
		return panel;
	}

	void refreshDisplayTab() {
		if (displayTabIndex != -1) {
			tabs.remove(displayTabIndex);
			JPanel updatedPanel = displayAll();
			tabs.insertTab("📋Display", null, updatedPanel, null, displayTabIndex);
		}
	}

	public static void main(String[] args) {
		new EmpManagementGUI();
	}
}




*/