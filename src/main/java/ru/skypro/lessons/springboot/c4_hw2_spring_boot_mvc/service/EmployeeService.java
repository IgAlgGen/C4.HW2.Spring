package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;

import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    void addEmployee(EmployeeDTO employeeDTO);

    void updateEmployee(int id, EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(int id);

    void deleteEmployee(int id);

    List<EmployeeDTO> getAllEmployeesSalaryHigherThanSalary(Integer compareSalary);

    List<EmployeeFullInfo> getEmployeeWithHighestSalary();

    List<EmployeeFullInfo> getEmployeeOnPosition(String position);

    List<EmployeeFullInfo> getEmployeeFullInfoByID(Integer id);

    List<EmployeeFullInfo> getPageEmployee(Integer page);
}
