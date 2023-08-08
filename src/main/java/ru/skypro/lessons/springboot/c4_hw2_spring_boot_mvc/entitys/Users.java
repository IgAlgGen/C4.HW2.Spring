package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usersc4")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    private String password;
    private boolean isEnabled;
    @JoinColumn (name = "role_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserRole userRole;


}
