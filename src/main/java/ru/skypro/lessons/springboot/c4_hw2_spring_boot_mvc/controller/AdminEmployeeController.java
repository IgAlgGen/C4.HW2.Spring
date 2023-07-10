package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/employees")
@RequiredArgsConstructor
public class AdminEmployeeController {

    private final EmployeeService employeeService;

    //Добавлять нового сотрудника;
    @PostMapping("/")
    public void addNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
    }

    //Редактировать сотрудника с указанным id
    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(id, employeeDTO);
    }

    //Удалить сотрудника с переданным id
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
    }

    //Добавить список сотрудников из файла.
    @PutMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadAndSaveEmployee(@RequestParam("file") MultipartFile file) {
        employeeService.saveEmployee(file);
    }

}