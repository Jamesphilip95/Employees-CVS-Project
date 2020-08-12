package com.sparta.jp.EmployeeManager;

import com.sparta.jp.DataStorage.EmployeeDAO;
import com.sparta.jp.EmployeeModel.CVSreader;
import com.sparta.jp.EmployeeModel.Employee;
import java.util.HashMap;


public class EmployeeManager {

    public void runManager() {
        CVSreader cvsReader = new CVSreader();
        HashMap<String, Employee> employees = cvsReader.getAllEmployees();
        Long start = System.nanoTime();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.addEmployees(employees);
        Long end = System.nanoTime();
        Long timeElapsed = end - start;
        System.out.println(timeElapsed);
    }
    public void checkDataBase(){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.displayMinimumSalary(199900);
    }
}