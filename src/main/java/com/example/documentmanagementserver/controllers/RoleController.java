package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.models.Role;
import com.example.documentmanagementserver.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {
    private final RoleRepository roleRepository;

    @GetMapping("/role")
    @ResponseBody
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
