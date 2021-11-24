package com.example.documentmanagementserver.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
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
    private String firstnameAndLastNameOrCompanyName;
    @NotNull
    private String pesel;
    @NotNull
    private String nip;
    @NotNull
    private String regon;
    @NotNull
    private String krs;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String emailAddress;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Address residenceOrRegisteredOfficeAddress;
    @OneToOne(cascade = {CascadeType.ALL})
    private Address mailingAddress;

    @OneToOne(mappedBy = "client")
    @ToString.Exclude
    private Case aCase;

    public Client(String firstnameAndLastNameOrCompanyName, String pesel, String nip, String regon, String krs, String phoneNumber, String emailAddress, Address residenceOrRegisteredOfficeAddress) {
        this.firstnameAndLastNameOrCompanyName = firstnameAndLastNameOrCompanyName;
        this.pesel = pesel;
        this.nip = nip;
        this.regon = regon;
        this.krs = krs;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.residenceOrRegisteredOfficeAddress = residenceOrRegisteredOfficeAddress;
    }
}
