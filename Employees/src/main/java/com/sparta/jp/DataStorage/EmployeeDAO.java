package com.sparta.jp.DataStorage;

import com.sparta.jp.EmployeeModel.Employee;
import com.sparta.jp.EmployeeModel.Password;
import java.sql.*;
import java.util.HashMap;


public class EmployeeDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/employeerecords?user=root&password=root&serverTimezone=UTC";
    private final String INSERT = "INSERT INTO employee_table VALUES (?,?,?,?,?,?,?,?,?,?)";
    Password password = new Password();

    public void addEmployees(HashMap<String, Employee> employeeList) {
        try (Connection connection = DriverManager.getConnection(URL)){
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
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
            }
    }
