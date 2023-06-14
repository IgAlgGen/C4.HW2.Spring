package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.controller;


import org.springframework.web.bind.annotation.*;
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

    //Он должен создавать множество новых сотрудников;
    @PostMapping("/")
    public void addNewEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    //Он должен редактировать сотрудника с указанным id
    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
            employeeService.updateEmployee(id, employee);
    }

    //Он должен возвращать информацию о сотруднике с переданным id
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Integer id) {
        return employeeService.getEmployeeById(id);
    }

    //Он должен удалять сотрудника с переданным id
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
    }

    //Он должен возвращать всех сотрудников, зарплата которых выше переданного параметра salary
    @GetMapping("/salary/HigherThanSalary")
    public List<Employee> getAllEmployeesSalaryHigherThanSalary(@RequestParam("compareSalary") Integer compareSalary) {
        return employeeService.getAllEmployeesSalaryHigherThanSalary(compareSalary);
    }
}

