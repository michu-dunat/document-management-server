package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private String PESEL;
    private String NIP;
    private String REGON;
    private String KRS;
    private String phoneNumber;
    private String emailAddress;
    @OneToOne
    private Address residenceOrRegisteredOfficeAddress;
    @OneToOne
    private Address mailingAddress;

    @OneToOne(mappedBy = "client")
    private Case aCase;

}
