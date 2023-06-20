package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.controller;


import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service.EmployeeService;


import java.util.List;

@RestController
@RequestMapping("/employees")

public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    Получить список всех сотрудников
    @GetMapping("/getAllEmployee")
    public List<EmployeeDTO> getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    //Добавлять нового сотрудника;
    @PostMapping("/add")
    public void addNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
    }

    //Редактировать сотрудника с указанным id
    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(id, employeeDTO);
    }

    //Получить информацию о сотруднике с переданным id
    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Integer id) {
        return employeeService.getEmployeeById(id);
    }

    //Удалить сотрудника с переданным id
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
    }

    //Он должен возвращать всех сотрудников, зарплата которых выше переданного параметра salary
    @GetMapping("/salary/HigherThanSalary")
    public List<EmployeeDTO> getAllEmployeesSalaryHigherThanSalary(@RequestParam("compareSalary") Integer compareSalary) {
        return employeeService.getAllEmployeesSalaryHigherThanSalary(compareSalary);
    }






    //GET-запрос
    //localhost:8080/employee/withHighestSalary
    //Он должен возвращать информацию о сотрудниках с самой высокой зарплатой в фирме;
    @GetMapping("/withHighestSalary")
    public List<EmployeeFullInfo> withHighestSalary() {
        return employeeService.getEmployeeWithHighestSalary();

    }

//    GET-запрос
//    localhost:8080/employee?position=
//    Он должен принимать на вход опциональный параметр
//    position
//    и возвращать информацию о всех сотрудниках фирмы, указанной в параметре должности. Если параметр не указан, то возвращать необходимо всех сотрудников.
    @GetMapping(value = "/")
    public List<EmployeeFullInfo> employeeOnPosition(@RequestParam("position") String position) {
        return employeeService.getEmployeeOnPosition(position);
    }




    //GET-запрос
    //localhost:8080/employee/{id}/fullInfo
    // Он должен возвращать полную информацию о сотруднике (имя, зарплата, название должности) с переданным в пути запроса идентификатором.
    @GetMapping("/{id}/fullInfo")
    public List<EmployeeFullInfo> employeeFullInfoByID(@PathVariable("id") Integer id) {
        return employeeService.getEmployeeFullInfoByID(id);
    }

    //GET-запрос
    //localhost:8080/employee/page?page=
    //Он должен возвращать информацию о сотрудниках, основываясь на номере страницы. Если страница не указана, то возвращается первая страница.
    //Номера страниц начинаются с 0. Лимит на количество сотрудников на странице — 10 человек.
    @GetMapping("page")
    public List<EmployeeFullInfo> employeeList(@RequestParam("page") int page) {
        return employeeService.getPageEmployee(page);
    }


}

