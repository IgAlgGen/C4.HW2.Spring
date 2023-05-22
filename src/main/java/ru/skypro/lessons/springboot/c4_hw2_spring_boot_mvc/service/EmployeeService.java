package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;

import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
}
