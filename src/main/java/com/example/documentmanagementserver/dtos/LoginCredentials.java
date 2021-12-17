package com.example.documentmanagementserver.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginCredentials {
    private String emailAddress;
    private String password;
}
