package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    public List<Employee> employeeList = List.of(
            new Employee(1,"Катя", 90_000),
            new Employee(2,"Дима", 102_000),
            new Employee(3,"Олег", 80_000),
            new Employee(4,"Вика", 165_000));

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public void addEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

}
