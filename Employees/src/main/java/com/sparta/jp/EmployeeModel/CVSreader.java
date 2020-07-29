package com.sparta.jp.EmployeeModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class CVSreader {

    public HashMap<String, Employee> getAllEmployees() {
       return readEmployeesFromCVS("Employees/Resources/employees");
    }
    public static HashMap<String, Employee> readEmployeesFromCVS(String fileName) {
        HashMap<String, Employee> employees = new HashMap<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
           String line = br.readLine(); // avoids the header line
            while ( (line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                Employee employee = createEmployees(attributes);
                employees.put(employee.getEmployeeId(),employee);

            }
        } catch (IOException | ParseException ioe) {
            ioe.printStackTrace();
        }
        return employees;

    }



    private static Employee createEmployees(String[] metadata) throws ParseException {

        String employeeID = metadata[0];
        String title = metadata[1];
        String firstName = metadata[2];
        char middleInitial = metadata[3].charAt(0);
        String lastName = metadata[4];
        char gender = metadata[5].charAt(0);
        String email = metadata[6];
        LocalDate dateOfBirth = LocalDate.parse(metadata[7], DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate dateOfJoin = LocalDate.parse(metadata[8], DateTimeFormatter.ofPattern("M/d/yyyy"));
        String salary = metadata[9];

return new Employee(employeeID,title,firstName,middleInitial,lastName,gender,email, dateOfBirth, dateOfJoin, salary);


    }
}

