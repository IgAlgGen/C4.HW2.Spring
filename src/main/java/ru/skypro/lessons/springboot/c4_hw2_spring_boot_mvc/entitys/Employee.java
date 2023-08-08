package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employeec4")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer salary;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;

}
