package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.dtos.UserNamesForDocumentSenderField;
import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.UserRepository;
import com.example.documentmanagementserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/user/add")
    public ResponseEntity<Integer> addUser(@RequestBody User user) {
        userService.encodePassword(user);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping("/user/table")
    public List<User> getUsers() {
        return userService.getAllUsersWithNoPassword();
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable(value = "id") int id) {
        Optional<User> user = userRepository.findById(id);
        if (userService.isUserTryingToDeleteItself(user)) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userService.isUserToBeDeletedOrUpdatedLastAdmin(user)) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PutMapping("/user/update")
    public ResponseEntity<Integer> updateUser(@RequestBody User user) {
        Optional<User> userSavedInDatabase = userRepository.findById(user.getId());
        if (userService.isRoleOfUserIsToBeChangedFromAdminToUser(user, userSavedInDatabase)) {
            if (userService.isLoggedInUserEqualsUserToBeUpdated(user)) {
                return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (userService.isUserToBeDeletedOrUpdatedLastAdmin(userSavedInDatabase)) {
                return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        userService.setPasswordOfUserToBeUpdated(user, userSavedInDatabase);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable(value = "id") int id) {
        return userService.getUserWithNoPassword(id);
    }

    @GetMapping(value = "/user/possible-document-senders")
    public List<UserNamesForDocumentSenderField> getNamesOfUsers() {
        return userService.getNamesOfUsers();
    }
}
