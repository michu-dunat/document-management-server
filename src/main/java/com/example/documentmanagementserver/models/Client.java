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
    private String PESEL;
    private String NIP;
    private String REGON;
    private String KRS;
    private String phoneNumber;
    private String emailAddress;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Address residenceOrRegisteredOfficeAddress;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Address mailingAddress;

    @OneToMany(mappedBy = "client")
    private List<Case> cases;

}
