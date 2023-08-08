package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_rolec4")
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String role;


}
