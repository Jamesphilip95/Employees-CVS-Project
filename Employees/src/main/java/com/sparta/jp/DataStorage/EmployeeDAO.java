package com.sparta.jp.DataStorage;

import com.sparta.jp.EmployeeModel.Employee;
import com.sparta.jp.EmployeeModel.Password;

import java.sql.*;
import java.util.HashMap;


public class EmployeeDAO {
    static Password password = new Password();
    private static final String URL = "jdbc:mysql://localhost:3306/employeerecords?user=root&password="+password.getPassword()+"&serverTimezone=UTC";
    private final String INSERT = "INSERT INTO employee_table VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String MINIMUM_SALARY = "SELECT employee_ID, title, firstName, lastName, salary FROM employee_table WHERE Salary > ?";


    public int addEmployees(HashMap<String, Employee> employeeList) {
        int count = 0;
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            for (Employee employee : employeeList.values()) {
                statement.setString(1, employee.getEmployeeId());
                statement.setString(2, employee.getTitle());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, String.valueOf(employee.getMiddleName()));
                statement.setString(5, employee.getLastName());
                statement.setString(6, String.valueOf(employee.getGender()));
                statement.setString(7, employee.getEmail());
                statement.setDate(8, Date.valueOf(employee.getDateOfBirth()));
                statement.setDate(9, Date.valueOf(employee.getDateOfJoin()));
                statement.setString(10, employee.getSalary());
                count++;
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int[] addEmployeesWithBatch(HashMap<String, Employee> Employees) {
        int[] count = new int[0];
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            connection.setAutoCommit(false);
            for (Employee employee : Employees.values()) {
                statement.setString(1, employee.getEmployeeId());
                statement.setString(2, employee.getTitle());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, String.valueOf(employee.getMiddleName()));
                statement.setString(5, employee.getLastName());
                statement.setString(6, String.valueOf(employee.getGender()));
                statement.setString(7, employee.getEmail());
                statement.setDate(8, Date.valueOf(employee.getDateOfBirth()));
                statement.setDate(9, Date.valueOf(employee.getDateOfJoin()));
                statement.setString(10, employee.getSalary());
                statement.addBatch();
            }

            count = statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public void displayMinimumSalary(int minSalary) {
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement statement = connection.prepareStatement(MINIMUM_SALARY);
            statement.setInt(1, minSalary);
            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("\nEmployee's with Salary over £" +minSalary +":");
                System.out.println("------------------------------------");
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String firstName = resultSet.getString("firstName");
                    String secondName = resultSet.getString("lastName");
                    String salary = resultSet.getString("salary");
                    System.out.printf("Salary: £%s   %s %s %s %n", salary, title, firstName, secondName);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

