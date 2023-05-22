package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    private final List<Employee> employeeList = List.of(
            new Employee("Катя", 90_000),
            new Employee("Дима", 102_000),
            new Employee("Олег", 80_000),
            new Employee("Вика", 165_000));


    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }
}
