package com.example.documentmanagementserver.components;

import com.example.documentmanagementserver.models.Role;
import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.RoleRepository;
import com.example.documentmanagementserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public void run(ApplicationArguments args) {
        if (roleRepository.count() == 0 && userRepository.count() == 0) {
            Role role1 = new Role("ROLE_ADMIN", "Administrator");
            Role role2 = new Role("ROLE_USER", "Użytkownik");
            roleRepository.save(role1);
            roleRepository.save(role2);

            User user1 = new User("Jan Kowalski", "jan@gmail.com",
                    passwordEncoder.encode("Useruseruser1"), role1);
            User user2 = new User("Marta Nowak", "marta@gmail.com",
                    passwordEncoder.encode("Useruseruser1"), role2);
            userRepository.save(user1);
            userRepository.save(user2);
        }
    }
}
