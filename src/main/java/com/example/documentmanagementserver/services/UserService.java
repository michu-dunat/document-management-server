package com.example.documentmanagementserver.services;

import com.example.documentmanagementserver.dtos.UserNamesForDocumentSenderField;
import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

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

    public boolean isUserTryingToDeleteItself(Optional<User> user) {
        return user.isPresent() && Objects.equals(user.get().getEmailAddress(), getUsernameOfLoggedInUser());
    }

    public boolean isUserToBeDeletedOrUpdatedLastAdmin(Optional<User> user) {
        if (user.isPresent()) {
            if (Objects.equals(user.get().getRole().getCode(), "ROLE_ADMIN")) {
                return userRepository.countAllByRole(user.get().getRole()) <= 1;
            }
        }
        return false;
    }

    public boolean isRoleOfUserIsToBeChangedFromAdminToUser(User user, Optional<User> userFromDatabase) {
        return userFromDatabase.filter(value -> Objects.equals(user.getRole().getCode(), "ROLE_USER") &&
                Objects.equals(value.getRole().getCode(), "ROLE_ADMIN")).isPresent();
    }

    public boolean isLoggedInUserEqualsUserToBeUpdated(User user) {
        return Objects.equals(user.getEmailAddress(), getUsernameOfLoggedInUser());
    }

    private boolean wasPasswordChanged(User user) {
        return !Objects.equals(user.getPassword(), "");
    }

    public void setPasswordOfUserToBeUpdated(User user, Optional<User> userSavedInDatabase) {
        if (wasPasswordChanged(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            userSavedInDatabase.ifPresent(value -> user.setPassword(value.getPassword()));
        }
    }

    private Object getUsernameOfLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
