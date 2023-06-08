package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository;

import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();

    void addEmployeeList(List<Employee> employeeList);


}
