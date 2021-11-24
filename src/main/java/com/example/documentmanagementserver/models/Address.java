package com.example.documentmanagementserver.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private String city;
    @NotNull
    private String postcode;
    @NotNull
    private String street;
    @NotNull
    private String buildingNumber;
    private String apartmentNumber;

    @OneToOne(mappedBy = "residenceOrRegisteredOfficeAddress")
    @ToString.Exclude
    private Client clientResidenceOrRegisteredOfficeAddress;

    @OneToOne(mappedBy = "mailingAddress")
    @ToString.Exclude
    private Client clientMailingAddress;

    @OneToOne(mappedBy = "residenceOrRegisteredOfficeAddress")
    @ToString.Exclude
    private AdversePartyAttorney adversePartyAttorneyResidenceOrRegisteredOfficeAddress;

    @OneToOne(mappedBy = "mailingAddress")
    @ToString.Exclude
    private AdversePartyAttorney adversePartyAttorneyMailingAddress;

    @OneToOne(mappedBy = "address")
    @ToString.Exclude
    private Court court;

    public Address(String city, String postcode, String street, String buildingNumber) {
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }
}
