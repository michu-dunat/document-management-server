package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.models.Role;
import com.example.documentmanagementserver.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://document-management-client.herokuapp.com"})
public class RoleController {
    private final RoleRepository roleRepository;

    @GetMapping("/role")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
