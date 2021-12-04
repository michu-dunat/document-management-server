package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String firstnameAndLastName;
    @NotNull
    @Column(unique = true)
    private String emailAddress;
    @NotNull
    private String password;

    @ManyToOne
    private Role role;

    public User(String firstnameAndLastName, String emailAddress, String password, Role role) {
        this.firstnameAndLastName = firstnameAndLastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
    }
}
