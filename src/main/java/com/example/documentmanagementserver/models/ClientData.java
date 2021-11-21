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
public class ClientData {

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
    @ManyToOne
    private Address residenceOrRegisteredOfficeAddress;
    @ManyToOne
    private Address mailingAddress;


    @OneToOne(mappedBy = "clientData")
    private CaseData caseData;

}
