package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Position;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeFullInfo {
    private String name;
    private int salary;
    private String positionName;

    public static EmployeeFullInfo fromEmployee(Employee employee) {
        EmployeeFullInfo employeeFullInfo = new EmployeeFullInfo();
        employeeFullInfo.setName(employee.getName());
        employeeFullInfo.setSalary(employee.getSalary());
        employeeFullInfo.setPositionName(employee.getPosition().getPositionName());

        return employeeFullInfo;
    }
    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        employee.setPosition(new Position());
        return employee;
    }

}
