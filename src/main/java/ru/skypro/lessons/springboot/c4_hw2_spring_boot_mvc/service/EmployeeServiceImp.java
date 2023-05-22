package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.EmployeeRepository;

import java.util.ArrayList;
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

    @Override
    public int getSumSalaryEmployee() {
        int sumSalary = 0;
        for (Employee employee : employeeRepository.getAllEmployees()) {
            sumSalary += employee.getSalary();
        }
        return sumSalary;
    }

    @Override
    public Employee getMinSalaryEmployee() {
        Employee desiredEmployee = null;
        int temtInt = Integer.MAX_VALUE;
        for (Employee employee : employeeRepository.getAllEmployees()) {
            if (employee.getSalary() < temtInt) {
                desiredEmployee = employee;
                temtInt = employee.getSalary();
            }
        }
        return desiredEmployee;
    }

    @Override
    public Employee getMaxSalaryEmployee() {
        Employee desiredEmployee = null;
        int temtInt = 0;
        for (Employee employee : employeeRepository.getAllEmployees()) {
            if (employee.getSalary() > temtInt) {
                desiredEmployee = employee;
                temtInt = employee.getSalary();
            }
        }
        return desiredEmployee;
    }

    @Override
    public List<Employee> getHigh_salaryEmployee() {
        List<Employee> high_salaryEmployee = new ArrayList<>();
        int temp = 0;
        for (Employee employee : employeeRepository.getAllEmployees()) {
            temp = temp + employee.getSalary();

        }
        int average = temp / employeeRepository.getAllEmployees().size();
        for (Employee employe1 : employeeRepository.getAllEmployees()) {
            if (employe1.getSalary() > average) {
                high_salaryEmployee.add(employe1);
            }
        }
        return high_salaryEmployee;
    }
}
