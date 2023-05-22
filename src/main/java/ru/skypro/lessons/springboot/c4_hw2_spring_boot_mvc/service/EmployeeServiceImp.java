package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
}
