import  java.sql.*;
import java.util.*;

public class Emp_Management {

	Connection  con;
	Statement   st;
	ResultSet   rs;
	DatabaseMetaData dbm; 
	Scanner sc = new Scanner(System.in);
	
	Emp_Management() { 
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");
			st = con.createStatement();
			dbm = con.getMetaData();
		
			System.out.println("\n✅ Connection Successfull");
			
			ResultSet rs = dbm.getTables(null, null, "EMPLOYEE", new String[] {"TABLE"});
			if (!rs.next()) {
			    // Table does not exist — create it
			    st.execute("CREATE TABLE Employee(id INT, name VARCHAR2(30), salary NUMBER)");
			    System.out.println("✅ Table 'Employee' created successfully.");
			}
		} catch (Exception error) {
			System.out.println("An error Occured.." + error);
		}
	}
	
	void  insertData(int  id, String name, int  salary) {
		try {
//			st.executeQuery("INSERT INTO Employee VALUES (?, ?, ?)");
			PreparedStatement ps = con.prepareStatement("INSERT INTO Employee VALUES (?, ?, ?)");
			
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, salary);
			
			ps.executeQuery();
			System.out.println("\n✅ Inserted id: " + id);
			
		} catch (Exception error) {
			System.out.println("An error Occured.." + error);
		}
	}
	
	void  updateAll(int oldId, int  id, String name, int  salary) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE Employee SET id = ?, name = ?, salary= ? WHERE id = ?");
	        ps.setInt(1, id);
	        ps.setString(2, name);
	        ps.setInt(3, salary);
	        ps.setInt(4, oldId);
	        ps.executeUpdate();
	        System.out.println("\n✅ Updated Record of Id " + oldId);
			
		} catch (Exception error) {
			System.out.println("An error Occured.." + error);
		}
	}
	
	void updateEmployeeField(String field, Object newValue, int id) {
	    try {
	        String sql = "UPDATE Employee SET " + field + " = ? WHERE id = ?";
	        PreparedStatement ps = con.prepareStatement(sql);

	        // Dynamically set value based on type
	        if (newValue instanceof String) {
	            ps.setString(1, (String) newValue);
	        } else if (newValue instanceof Integer) {
	            ps.setInt(1, (Integer) newValue);
	        } else {
	            throw new IllegalArgumentException("Unsupported data type");
	        }

	        ps.setInt(2, id);
	        ps.executeUpdate();
	        System.out.println("\n✅ Updated " + field + " of ID " + id + " to " + newValue);
	        
	    } catch (Exception error) {
	        System.out.println("An error occurred: " + error);
	    }
	}
	
	void deleteId(int oldId) {
	    try {
	        PreparedStatement ps = con.prepareStatement("DELETE FROM Employee WHERE id = ?");
	        ps.setInt(1, oldId);
	        ps.executeUpdate();
	        System.out.println("\n✅ Deleted ID: " + oldId);
	    } catch (Exception error) {
	        System.out.println("An error occurred: " + error);
	    }
	}
	
	void searchById(int oldId) {
	    try {
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee where id = ?");
	        ps.setInt(1, oldId);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	        	System.out.println("\n✅ Employee data present ");
	        	System.out.print("Do you want to display data (yes/no): ");
	        	String check = sc.next();
	        	
	        	if (check.equals("yes")) {
	        		System.out.println("   ID    NAME    SALARY");
	        		do {
	        			System.out.print("   " + rs.getInt("Id"));
	        			System.out.print("    " + rs.getString("Name"));
	        			System.out.print("    " + rs.getInt("Salary"));
	        			System.out.println();
	        		} while (rs.next());
	        	}
	        } else {
	        	System.out.println("\n✅ Employee data not present ");
	        }
	    } catch (Exception error) {
	        System.out.println("An error occurred: " + error);
	    }
	}
	
	void displayAll() {
		try {
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee");
	        rs = ps.executeQuery();
	        System.out.println("   ID    NAME    SALARY");
	        while (rs.next()) {
	        	System.out.print("   " + rs.getInt("Id"));
	        	System.out.print("    " + rs.getString("Name"));
	        	System.out.print("    " + rs.getInt("Salary"));
	        	System.out.println();
	        } 
	        
	        System.out.println("\nDone...");
	    } catch (Exception error) {
	        System.out.println("An error occurred: " + error);
	    }
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Emp_Management ob = new Emp_Management();
		
		int ch, check, id, oldid, salary;
		String name;
		
		do {
			System.out.print("\n1: Insert Data\n2: Update data\n3: Search Data\n4: Delete Data\n5: Display All Records\n6: Exit\nEnter your choice: ");
			ch = ob.sc.nextInt();
			
			switch (ch) {
				case 1:
					System.out.print("Enter id: ");
					id = ob.sc.nextInt();
					System.out.print("Enter Name: ");
					name = ob.sc.next();
					System.out.print("Enter Salary: ");
					salary = ob.sc.nextInt();
					ob.insertData(id, name, salary);
					break;
				case 2:
					System.out.print("\n1: Update Id\n2: Update Name\n3: Update Salary\n4: Update All\n5: Exit\nEnter your choice: ");
					check = ob.sc.nextInt();
					switch (check) {
							case 1:
								System.out.print("Enter old ID: ");
								oldid = ob.sc.nextInt();
								System.out.print("Enter new ID: ");
								id = ob.sc.nextInt();
								ob.updateEmployeeField("id", id, oldid); 
								break;
							case 2:
								System.out.print("Enter ID: ");
								oldid = ob.sc.nextInt();
								System.out.print("Enter new name: ");
								name = ob.sc.next();
								ob.updateEmployeeField("name", name, oldid); 
								break;
							case 3:
								System.out.print("Enter old ID: ");
								oldid = ob.sc.nextInt();
								System.out.print("Enter new Salary: ");
								salary = ob.sc.nextInt();
								ob.updateEmployeeField("salary", salary, oldid); 
								break;
								
							case 4:
								System.out.print("Enter old ID: ");
								oldid = ob.sc.nextInt();
								System.out.print("Enter new ID: ");
								id = ob.sc.nextInt();
								System.out.print("Enter new name: ");
								name = ob.sc.next();
								System.out.print("Enter new Salary: ");
								salary = ob.sc.nextInt();
								ob.updateAll(oldid, id, name, salary); 
								break;
							case 5:
								System.out.println("Ok");
							
					}
					break;
				case 3:
					System.out.print("Enter id to search: ");
					id = ob.sc.nextInt();
					ob.searchById(id);
					break;
				case 4:
					System.out.print("Enter id to delete: ");
					id = ob.sc.nextInt();
					ob.deleteId(id);
					break;
				case 5:
					System.out.println("Data: \n");
					ob.displayAll();
					break;
				case 6:
					System.out.println("\nExited...");
			}
		
		} while (ch != 6);
	}
}


