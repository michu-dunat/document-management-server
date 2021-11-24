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
public class Judge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String firstnameAndLastName;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn
    private Court court;

    public Judge(String firstnameAndLastName) {
        this.firstnameAndLastName = firstnameAndLastName;
    }
}
