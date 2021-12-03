package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
