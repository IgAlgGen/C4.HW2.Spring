package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Users;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
Users findByLogin (String login);


//@Query(value = "select u.login, u.password, r.role from users u join role r on r.authority_id = u.role_id")
//List<Users> findAllUsers();
}
