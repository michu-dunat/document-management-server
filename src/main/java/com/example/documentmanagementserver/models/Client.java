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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @EqualsAndHashCode.Include
    @NotNull
    private String firstnameAndLastNameOrCompanyName;
    @EqualsAndHashCode.Include
    @NotNull
    private String pesel;
    @NotNull
    @EqualsAndHashCode.Include
    private String nip;
    @EqualsAndHashCode.Include
    @NotNull
    private String regon;
    @EqualsAndHashCode.Include
    @NotNull
    private String krs;
    @NotNull
    @EqualsAndHashCode.Include
    private String phoneNumber;
    @EqualsAndHashCode.Include
    @NotNull
    private String emailAddress;
    @ManyToOne(cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Include
    @NotNull
    private Address residenceOrRegisteredOfficeAddress;
    @ManyToOne(cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Include
    private Address mailingAddress;

    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    private List<Case> cases;

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
