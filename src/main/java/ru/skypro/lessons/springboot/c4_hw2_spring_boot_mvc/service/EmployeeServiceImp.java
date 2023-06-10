package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    @SneakyThrows
    public void addEmployee(Employee employee) {
        if (employeeRepository.isEmployeeExist(employee.getId())) {
            throw new Exception("Неверные данные");
        }
        List<Employee> tempList = new ArrayList<>(List.copyOf(employeeRepository.getAllEmployees()));
        tempList.add(employee);
        employeeRepository.addEmployeeList(tempList);
    }

    @Override
    @SneakyThrows
    public void updateEmployee(int id, Employee employee) {
        if (!employeeRepository.isEmployeeExist(id)) {
            throw new Exception("Неверные данные");
        }

        List<Employee> tempList = new ArrayList<>(List.copyOf(employeeRepository.getAllEmployees()));
        for (int i = 0; i < employeeRepository.getAllEmployees().size(); i++) {
            if (employeeRepository.getAllEmployees().get(i).getId() == (id)) {
                tempList.set(i, new Employee(id, employee.getName(), employee.getSalary()));
            }
        }
        employeeRepository.addEmployeeList(tempList);
    }

    @Override
    @SneakyThrows
    public Employee getEmployeeById(int id) {
        if (!employeeRepository.isEmployeeExist(id)) {
            throw new Exception("Неверные данные");
        }
        Employee tempEmploye = null;
        for (Employee employee : employeeRepository.getAllEmployees()) {
            if (employee.getId() == (id)) {
                tempEmploye = employee;
            }
        }
        return tempEmploye;
    }

    @Override
    @SneakyThrows
    public void deleteEmployee(int id) {
        if (!employeeRepository.isEmployeeExist(id)) {
            throw new Exception("Неверные данные");
        }
        List<Employee> tempList = new ArrayList<>(List.copyOf(employeeRepository.getAllEmployees()));
        for (int i = 0; i < employeeRepository.getAllEmployees().size(); i++) {
            if (employeeRepository.getAllEmployees().get(i).getId() == (id)) {
                tempList.remove(i);
            }
        }
        employeeRepository.addEmployeeList(tempList);
    }

    @Override
    public List<Employee> getAllEmployeesSalaryHigherThanSalary(Integer salary) {
        List<Employee> tempList = new ArrayList<>();
        for (Employee employee : employeeRepository.getAllEmployees()) {
            if (employee.getSalary() > salary) {
                tempList.add(employee);
            }
        }
        return tempList;
    }


}
