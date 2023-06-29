package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.ReportDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeFullInfo;

import java.util.List;


public interface EmployeeRepository extends CrudRepository <Employee, Integer>  {

    List<Employee> findBySalaryGreaterThan(int salary);

    @Query (value = "SELECT * FROM employeec4 WHERE salary=(SELECT MAX (salary) FROM employeec4) ",
    nativeQuery = true)
    List<Employee> findEmployeeWithHighestSalary();

    @Query(value = "SELECT new ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeFullInfo(e.name, e.salary, p.positionName) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p and p.positionName = ?1")
    List<EmployeeFullInfo> findEmployeesByPosition(String position);

    Page<Employee> findAll(Pageable employeeOfConcretePage);

    @Query(value = "SELECT new ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.ReportDTO(e.position.positionName, COUNT(e.id), MAX(e.salary), MIN(e.salary), AVG(e.salary)) FROM Employee e GROUP BY e.position.positionName")
    List<ReportDTO> buildReports();

}


