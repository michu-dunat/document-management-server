package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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

    private String firstnameAndLastNameOrCompanyName;
    private String pesel;
    private String nip;
    private String regon;
    private String krs;
    private String phoneNumber;
    private String emailAddress;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Address residenceOrRegisteredOfficeAddress;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Address mailingAddress;

    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    private List<Case> cases;

}
