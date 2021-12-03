package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
