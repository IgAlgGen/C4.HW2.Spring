package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllEmployee")
    public List<Employee> showCounter() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/salary/sum")
    public int getSumSalaryEmployee() {
        return employeeService.getSumSalaryEmployee();
    }


    @GetMapping("/salary/min")
    public Employee getMinSalaryEmployee() {
        return employeeService.getMinSalaryEmployee();
    }


    @GetMapping("/salary/max")
    public Employee getMaxSalaryEmployee() {
        return employeeService.getMaxSalaryEmployee();
    }


    @GetMapping("/high-salary")
    public List<Employee> getHighSalaryEmployee() {
        return employeeService.getHigh_salaryEmployee();
    }

}

