package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service.EmployeeService;


@RestController
@RequestMapping("/report")
public class ReportController {

    private final EmployeeService employeeService;

    public ReportController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/")
    public void createReport() {
        employeeService.createReport();
    }

    @GetMapping(value = "/{reportId}")
    public ResponseEntity<Resource> downloadReport(@PathVariable ("reportId") Integer reportId){
        Resource resource = employeeService.downloadReport(reportId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.json\"")
                .body(resource);

    }
}
