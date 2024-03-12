package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportDTO {
    private String position;
    private long quantityEmployees;
    private int maxSalary;
    private int minSalary;
    private double avrSalary;
}
