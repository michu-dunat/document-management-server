package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAddress(String emailAddress);
    Optional<User> findByEmailAddressAndPassword(String emailAddress, String password);
}
