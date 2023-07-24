package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Report;

public interface ReportRepositoriy extends CrudRepository <Report, Integer> {

}
