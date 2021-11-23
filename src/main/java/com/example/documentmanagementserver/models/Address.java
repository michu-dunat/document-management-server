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
    @ToString.Exclude
    private List<Client> clientsResidenceOrRegisteredOfficeAddress;

    @OneToMany(mappedBy = "mailingAddress")
    @ToString.Exclude
    private List<Client> clientsMailingAddress;

    @OneToMany(mappedBy = "residenceOrRegisteredOfficeAddress")
    @ToString.Exclude
    private List<AdversePartyAttorney> adversePartyAttorneys;

    @OneToMany(mappedBy = "mailingAddress")
    @ToString.Exclude
    private List<AdversePartyAttorney> adversePartyAttorneysMailingAddress;

    @OneToMany(mappedBy = "address")
    @ToString.Exclude
    private List<Court> courts;
}
