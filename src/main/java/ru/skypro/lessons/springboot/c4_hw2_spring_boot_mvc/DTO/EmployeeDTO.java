package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO;


import lombok.*;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Employee;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private int id;
    private String name;
    private Integer salary;


    // Метод для преобразования сущности Employee в объект EmployeeDTO
    public static EmployeeDTO fromEmployee(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());

        return employeeDTO;
    }

    // Метод для преобразования объекта EmployeeDTO в сущность Employee
    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        return employee;
    }
}
