package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.constants;

import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Position;

import java.util.List;

public class EmployeeConstants {
    public static final Employee EMPLOYEE_1 = new Employee(1, "Ivan", 10000, new Position(1, "test"));
    public static final Employee EMPLOYEE_2 = new Employee(2, "Petr", 20000, new Position(1, "test"));
    public static final Employee EMPLOYEE_3 = new Employee(3, "Vasa", 30000, new Position(1, "test"));
    public static final EmployeeDTO EMPLOYEE_DTO_1 = EmployeeDTO.fromEmployee(EMPLOYEE_1);
    public static final EmployeeDTO EMPLOYEE_DTO_2 = EmployeeDTO.fromEmployee(EMPLOYEE_2);
    public static final EmployeeDTO EMPLOYEE_DTO_3 = EmployeeDTO.fromEmployee(EMPLOYEE_3);
    public static final EmployeeFullInfo EMPLOYEE_FULL_INFO_1 = EmployeeFullInfo.fromEmployee(EMPLOYEE_1);
    public static final EmployeeFullInfo EMPLOYEE_FULL_INFO_2 = EmployeeFullInfo.fromEmployee(EMPLOYEE_2);
    public static final EmployeeFullInfo EMPLOYEE_FULL_INFO_3 = EmployeeFullInfo.fromEmployee(EMPLOYEE_3);


    public static final List<EmployeeDTO> EMPLOYEES_DTO_LIST = List.of(EMPLOYEE_DTO_1, EMPLOYEE_DTO_2, EMPLOYEE_DTO_3);
    public static final List<Employee> EMPLOYEES_LIST = List.of(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3);
    public static final  List<EmployeeFullInfo> EMPLOYEES_FULL_INFO_LIST = List.of(EMPLOYEE_FULL_INFO_1, EMPLOYEE_FULL_INFO_2, EMPLOYEE_FULL_INFO_3);
}
