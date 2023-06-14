package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;

import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    int getSumSalaryEmployee();
    Employee getMinSalaryEmployee();
    Employee getMaxSalaryEmployee();
    List<Employee> getHigh_salaryEmployee();
    void addEmployee(Employee employee);
    void updateEmployee(int id, Employee employee);
    Employee getEmployeeById(int id);
    void deleteEmployee(int id);
    List<Employee> getAllEmployeesSalaryHigherThanSalary(Integer salary);


}
