package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String firstNameLastName;
    @NotNull
    private String position;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    private Court court;

    public Subject(String firstNameLastName, String position) {
        this.firstNameLastName = firstNameLastName;
        this.position = position;
    }
}
