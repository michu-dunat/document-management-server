package com.example.documentmanagementserver.services;

import com.example.documentmanagementserver.dtos.UserNamesForDocumentSenderField;
import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserNamesForDocumentSenderField> getNamesOfUsers() {
        List<User> users = userRepository.findAll();
        return this.createListOfNamesOfUsers(users);
    }

    private List<UserNamesForDocumentSenderField> createListOfNamesOfUsers(List<User> users) {
        List<UserNamesForDocumentSenderField> namesOfUsers = new ArrayList<>();
        for (User user : users) {
            UserNamesForDocumentSenderField namesOfUser =
                    new UserNamesForDocumentSenderField(user.getId(), user.getFirstNameLastName());
            namesOfUsers.add(namesOfUser);
        }
        return namesOfUsers;
    }

    public List<User> getAllUsersWithNoPassword() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setPassword("");
        }
        return users;
    }

    public User getUserWithNoPassword(int userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(value -> value.setPassword(""));
        return user.orElse(null);
    }
}
