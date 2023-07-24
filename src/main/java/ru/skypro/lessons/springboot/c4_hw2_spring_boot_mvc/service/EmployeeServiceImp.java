package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Report;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.ReportRepositoriy;

import java.io.IOException;
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
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImp.class);

    public EmployeeServiceImp(EmployeeRepository employeeRepository, ObjectMapper objectMapper, ReportRepositoriy reportRepositoriy) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
        this.reportRepositoriy = reportRepositoriy;

    }


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Was invoked method getAllEmployees");
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
        logger.info("Was invoked method addEmployee with parameter {}", employeeDTO.toString());
        if (employeeRepository.existsById(employeeDTO.getId())) {
            logger.error("There is no employee with id = " + employeeDTO.getId(), new Exception("Сотрудники с такими ID есть в базе"));
            throw new Exception("Сотрудники с такими ID есть в базе");
        }
        employeeRepository.save(employeeDTO.toEmployee());
    }

    @Override
    @SneakyThrows
    public void updateEmployee(int id, EmployeeDTO employeeDTO) {
        logger.info("Was invoked method updateEmployee with parameters {}, {}", id, employeeDTO.toString());
        if (!employeeRepository.existsById(id)) {
            logger.error("There is no employee with id = " + id, new Exception("Неверные данные"));
            throw new Exception("Неверные данные");
        }
        employeeRepository.save(employeeDTO.toEmployee());
    }

    @Override
    @SneakyThrows
    public EmployeeDTO getEmployeeById(int id) {
        logger.info("Was invoked method getEmployeeById with parameter {}", id);
        if (!employeeRepository.existsById(id)) {
            logger.error("There is no employee with id = " + id, new Exception("Неверные данные"));
            throw new Exception("Неверные данные");
        }
        return EmployeeDTO.fromEmployee(employeeRepository.findById(id).get());
    }

    @Override
    @SneakyThrows
    public void deleteEmployee(int id) {
        logger.info("Was invoked method deleteEmployee with parameter {}", id);
        if (!employeeRepository.existsById(id)) {
            logger.error("There is no employee with id = " + id, new Exception("Неверные данные"));
            throw new Exception("Неверные данные");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesSalaryHigherThanSalary(Integer compareSalary) {
        logger.info("Was invoked method getAllEmployeesSalaryHigherThanSalary with parameter {}", compareSalary);
        List<EmployeeDTO> temp = new ArrayList<>();
        for (Employee e :
                employeeRepository.findBySalaryGreaterThan(compareSalary)) {
            temp.add(EmployeeDTO.fromEmployee(e));
        }
        return temp;
    }

    @Override
    public List<EmployeeFullInfo> getEmployeeWithHighestSalary() {
        logger.info("Was invoked method getEmployeeWithHighestSalary");
        return employeeRepository.findEmployeeWithHighestSalary().stream().map(EmployeeFullInfo::fromEmployee).toList();
    }

    @Override
    public List<EmployeeFullInfo> getEmployeeOnPosition(String position) {
        logger.info("Was invoked method getEmployeeOnPosition with parameter {}", position);
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
        logger.info("Was invoked method getEmployeeFullInfoByID with parameter {}", id);
        Optional<Employee> temp = employeeRepository.findById(id);
        if (temp.isEmpty()) {
            logger.error("There is no employee with id = " + id, new Exception("Неверные данные"));
            throw new Exception("Неверные данные");
        }
        return temp.stream().map(EmployeeFullInfo::fromEmployee).toList();
    }

    @Override
    public List<EmployeeFullInfo> getPageEmployee(Integer page) {
        logger.info("Was invoked method getPageEmployee with parameter {}", page);
        Pageable employeeOfConcretePage = PageRequest.of(Objects.requireNonNullElse(page, 0), 10);
        Page<Employee> pageF = employeeRepository.findAll(employeeOfConcretePage);
        return pageF.stream().map(EmployeeFullInfo::fromEmployee)
                .toList();
    }

    @Override
    public void saveEmployee(MultipartFile file) {
        logger.info("Was invoked method saveEmployee with parameter {}", file.getOriginalFilename());
        try {
            List<EmployeeDTO> temp = objectMapper.readValue(file.getBytes(), new TypeReference<List<EmployeeDTO>>() {});
            for (EmployeeDTO e :
                    temp) {
                addEmployee(e);
            }
        } catch (IOException e) {
            logger.error("Wrong file", e);
            throw new JsonException();
        }
    }

    @Override
    public int createReport() {
        logger.info("Was invoked method createReport");
        Report report = new Report();
        report.setReport(buildReport());
        return reportRepositoriy.save(report).getReportId();
    }


    public String buildReport() {
        logger.info("Was invoked method buildReport");
        List<ReportDTO> reports = employeeRepository.buildReports();
        try {
            return objectMapper.writeValueAsString(reports);
        } catch (JsonProcessingException e) {
            logger.error("Wrong file", e);
            throw new JsonException();
        }

    }

    @Override
    public Resource downloadReport(int reportId) {
        logger.info("Was invoked method downloadReport");
        return new ByteArrayResource(reportRepositoriy
                .findById(reportId)
                .get()
                .toString()
                .getBytes(StandardCharsets.UTF_8)
        );
    }


}
