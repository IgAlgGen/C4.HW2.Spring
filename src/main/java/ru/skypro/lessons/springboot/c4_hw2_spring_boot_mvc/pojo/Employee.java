package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo;

import lombok.Data;

@Data
public class Employee {
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

}
