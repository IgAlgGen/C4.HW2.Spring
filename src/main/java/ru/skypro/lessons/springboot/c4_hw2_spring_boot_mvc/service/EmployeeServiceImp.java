package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.ReportDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.exceptionHandler.JsonException;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Report;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.ReportRepositoriy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    private final ReportRepositoriy reportRepositoriy;

    public EmployeeServiceImp(EmployeeRepository employeeRepository, ObjectMapper objectMapper, ReportRepositoriy reportRepositoriy) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
        this.reportRepositoriy = reportRepositoriy;
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
            throw new Exception("Сотрудники с такими ID есть в базе");
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
        if (position == null) {
            List<EmployeeFullInfo> temp = new ArrayList<>();
            for (Employee e : employeeRepository.findAll()) {
                temp.add(EmployeeFullInfo.fromEmployee(e));
            }
            return temp;
        } else {
            return employeeRepository.findEmployeesByPosition(position);
        }
    }

    @Override
    @SneakyThrows
    public List<EmployeeFullInfo> getEmployeeFullInfoByID(Integer id) {
        Optional<Employee> temp = employeeRepository.findById(id);
        if (temp.isEmpty()) {
            throw new Exception("Неверные данные");
        }
        return temp.stream().map(EmployeeFullInfo::fromEmployee).toList();
    }

    @Override
    public List<EmployeeFullInfo> getPageEmployee(Integer page) {
        Pageable employeeOfConcretePage = PageRequest.of(Objects.requireNonNullElse(page, 0), 10);
        Page<Employee> pageF = employeeRepository.findAll(employeeOfConcretePage);
        return pageF.stream().map(EmployeeFullInfo::fromEmployee)
                .toList();
    }

    @Override
    public void saveEmployee(MultipartFile file) {
        try {
            List<EmployeeDTO> temp = objectMapper.readValue(file.getBytes(), new TypeReference<List<EmployeeDTO>>() {});
            for (EmployeeDTO e :
                    temp) {
                addEmployee(e);
            }
        } catch (IOException e) {
            throw new JsonException();
        }
    }

    @Override
    public int createReport() {
        Report report = new Report();
        report.setReport(buildReport());
        return reportRepositoriy.save(report).getReportId();
    }


    public String buildReport() {
        List<ReportDTO> reports = employeeRepository.buildReports();
        try {
            return objectMapper.writeValueAsString(reports);
        } catch (JsonProcessingException e) {
            throw new JsonException();
        }

    }

    @Override
    public Resource downloadReport(int reportId) {
        return new ByteArrayResource(reportRepositoriy
                .findById(reportId)
                .get()
                .toString()
                .getBytes(StandardCharsets.UTF_8)
        );
    }


}
