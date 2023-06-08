package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.EmployeeRepositoryImpl;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



}
