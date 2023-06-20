package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;

import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.EmployeeRepository;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository, EmployeeRepository employeeRepository2) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> temp = new ArrayList<>();
        for (Employee e : employeeRepository.findAll()) {
            temp.add(e);
        }
        return temp.stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public void addEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsById(employeeDTO.getId())) {
            throw new Exception("Неверные данные");
        }
        employeeRepository.save(employeeDTO.toEmployee());
    }

    @Override
    @SneakyThrows
    public void updateEmployee(int id, EmployeeDTO employeeDTO) {
        if (!employeeRepository.existsById(id)) {
            throw new Exception("Неверные данные");
        }
        employeeRepository.save(employeeDTO.toEmployee());
    }

    @Override
    @SneakyThrows
    public EmployeeDTO getEmployeeById(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new Exception("Неверные данные");
        }
        return EmployeeDTO.fromEmployee(employeeRepository.findById(id).get());
    }

    @Override
    @SneakyThrows
    public void deleteEmployee(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new Exception("Неверные данные");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesSalaryHigherThanSalary(Integer compareSalary) {
        List<EmployeeDTO> temp = new ArrayList<>();
        for (Employee e :
                employeeRepository.findBySalaryGreaterThan(compareSalary)) {
            temp.add(EmployeeDTO.fromEmployee(e));
        }
        return temp;
    }

    @Override
    public List<EmployeeFullInfo> getEmployeeWithHighestSalary() {
        return employeeRepository.findEmployeeWithHighestSalary().stream().map(EmployeeFullInfo::fromEmployee).toList();
    }

    @Override
    public List<EmployeeFullInfo> getEmployeeOnPosition(String position) {
        return employeeRepository.findEmployeesByPosition(position);
    }

    @Override
    @SneakyThrows
    public List<EmployeeFullInfo> getEmployeeFullInfoByID(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new Exception("Неверные данные");
        }
        return employeeRepository.findById(id).stream().map(EmployeeFullInfo::fromEmployee).toList();
    }

    @Override
    public List<EmployeeFullInfo> getPageEmployee(int page) {
        Pageable employeeOfConcretePage = PageRequest.of(page, 10);
        Page<Employee> pageF = employeeRepository.findAll(employeeOfConcretePage);
        return pageF.stream().map(EmployeeFullInfo::fromEmployee)
                .toList();
    }

}
