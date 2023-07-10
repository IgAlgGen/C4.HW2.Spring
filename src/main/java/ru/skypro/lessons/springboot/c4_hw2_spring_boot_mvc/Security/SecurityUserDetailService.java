package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.Security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Users;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.UserRepository;

@Service
@AllArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUserDetail(user);
    }
}
