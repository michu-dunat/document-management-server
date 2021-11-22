package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;
    private String postcode;
    private String street;
    private String buildingNumber;
    private String apartmentNumber;

    @OneToMany(mappedBy = "residenceOrRegisteredOfficeAddress")
    private List<AdversePartyAttorney> adversePartyAttorneys;

    @OneToMany(mappedBy = "residenceOrRegisteredOfficeAddress")
    private List<Client> clientsResidenceOrRegisteredOfficeAddress;

    @OneToMany(mappedBy = "mailingAddress")
    private List<Client> clientsMailingAddress;

    @OneToMany(mappedBy = "address")
    private List<Court> courts;
}
