package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Role;
import com.example.documentmanagementserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAddress(String emailAddress);
    Integer countAllByRole(Role role);
}
