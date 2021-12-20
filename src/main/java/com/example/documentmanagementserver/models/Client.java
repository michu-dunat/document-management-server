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
    @Column(length = 11)
    private String pesel;
    @Column(length = 10)
    private String nip;
    @Column(length = 14)
    private String regon;
    @Column(length = 20)
    private String krs;
    @NotNull
    @Column(length = 20)
    private String phoneNumber;
    @NotNull
    private String emailAddress;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Address residenceOrRegisteredOfficeAddress;
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Address mailingAddress;
    @Column(length = 10000)
    private String comments;

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
