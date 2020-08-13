# Employees-CVS-Project

Written by James Philip

This project reads 10,000 employee records from a CVS file and uses JDBC to upload the records onto database using MySQL workbench.

To use this project:
  1. Create a database called "employeerecords" with a local instance on port 3306 on mySQL.
  2. Create a table called employee_table on mySQL workbench with all the employee attributes.
  
This project found that usings batching, instead of executing an update for each insert, was considerably faster.
