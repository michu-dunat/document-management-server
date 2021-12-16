package com.example.documentmanagementserver.services;

import com.example.documentmanagementserver.dtos.UserNamesForDocumentSenderField;
import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserNamesForDocumentSenderField> findAndPrepareUsersNames() {
        List<UserNamesForDocumentSenderField> userNamesForDocumentSenderFieldList = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users
             ) {
            UserNamesForDocumentSenderField userNamesForDocumentSenderField = new UserNamesForDocumentSenderField(user.getId(), user.getFirstNameLastName());
            userNamesForDocumentSenderFieldList.add(userNamesForDocumentSenderField);
        }
        return userNamesForDocumentSenderFieldList;
    }
}
