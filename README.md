# Employee Management System

A simple and professional **Employee Management System** built using **Java**, **Java Swing**, and **JDBC**. The application allows users to manage employee records through both **GUI** and **CLI** interfaces, with data stored in an **Oracle Database**.

---

## 📌 Features

* Add new employee records
* Update existing employee details
* Delete employee records
* View all employees
* Graphical User Interface (Java Swing)
* Command Line Interface (CLI)
* Database connectivity using JDBC

---

## 🛠️ Technologies Used

* **Java (JDK 8+)**
* **Java Swing** – for GUI
* **JDBC** – database connectivity
* **Oracle Database** – data storage

---

## 📂 Project Structure

```
├── Emp_Advanced.java        # Employee model / advanced logic
├── Emp_ManagementCLI.java  # Command Line Interface implementation
├── EmpManagementGUI1.java  # Java Swing GUI implementation
├── README.md               # Project documentation
```

---

## ⚙️ Prerequisites

Before running the project, make sure you have:

* Java JDK installed
* Oracle Database installed and running
* Oracle JDBC Driver (ojdbc)
* Basic knowledge of Java and SQL

---

## 🔧 Database Setup

1. Create an Oracle database/schema
2. Create an `EMPLOYEE` table (example):

```sql
CREATE TABLE EMPLOYEE (
  EMP_ID NUMBER PRIMARY KEY,
  NAME VARCHAR2(100),
  DEPARTMENT VARCHAR2(50),
  SALARY NUMBER
);
```

3. Update database connection details in the Java files:

* URL
* Username
* Password

---

## ▶️ How to Run

### Run GUI Version

```bash
javac EmpManagementGUI1.java
java EmpManagementGUI1
```

### Run CLI Version

```bash
javac Emp_ManagementCLI.java
java Emp_ManagementCLI
```

---

## 🧪 Usage

* Use the **GUI** to interact with buttons and forms
* Use the **CLI** to perform operations through terminal commands
* All operations reflect directly in the Oracle database

---

## 🚀 Future Enhancements

* User authentication
* Search and filter functionality
* Validation and exception handling improvements
* Migration to MySQL / PostgreSQL
* Web-based version

---

## 👤 Author

**Vishal Bhingarde**

---

## 📄 License

This project is for educational purposes. You are free to use and modify it.

---

⭐ If you find this project useful, feel free to star it and share!
 
