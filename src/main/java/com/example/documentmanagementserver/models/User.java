package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    private String firstNameLastName;
    @NotNull
    @Column(unique = true)
    private String emailAddress;
    @NotNull
    private String password;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Document> document;

    public User(String firstNameLastName, String emailAddress, String password, Role role) {
        this.firstNameLastName = firstNameLastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
    }
}
