package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Entity {

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

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    private Proceeding proceeding;

    public Entity(String firstNameLastName, String position) {
        this.firstNameLastName = firstNameLastName;
        this.position = position;
    }
}
