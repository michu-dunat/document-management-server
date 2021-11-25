package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Judge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String firstnameAndLastName;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    private Court court;

    public Judge(String firstnameAndLastName) {
        this.firstnameAndLastName = firstnameAndLastName;
    }
}
