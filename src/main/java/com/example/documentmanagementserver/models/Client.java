package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @NotNull
    private String firstNameLastNameCompanyName;
    private String pesel;
    private String nip;
    private String regon;
    private String krs;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String emailAddress;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Address residenceOrRegisteredOfficeAddress;
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Address mailingAddress;

    @JsonIgnore
    @OneToOne(mappedBy = "client")
    @ToString.Exclude
    private Case aCase;

    public Client(String firstNameLastNameCompanyName, String phoneNumber, String emailAddress,
                  Address residenceOrRegisteredOfficeAddress) {
        this.firstNameLastNameCompanyName = firstNameLastNameCompanyName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.residenceOrRegisteredOfficeAddress = residenceOrRegisteredOfficeAddress;
    }
}
