package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
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
    private String street;
    @NotNull
    private String buildingNumber;
    private String apartmentNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "residenceOrRegisteredOfficeAddress")
    @ToString.Exclude
    private Client clientResidenceOrRegisteredOfficeAddress;

    @JsonIgnore
    @OneToOne(mappedBy = "mailingAddress")
    @ToString.Exclude
    private Client clientMailingAddress;

    @JsonIgnore
    @OneToOne(mappedBy = "residenceOrRegisteredOfficeAddress")
    @ToString.Exclude
    private AdversePartyAttorney adversePartyAttorneyResidenceOrRegisteredOfficeAddress;

    @JsonIgnore
    @OneToOne(mappedBy = "mailingAddress")
    @ToString.Exclude
    private AdversePartyAttorney adversePartyAttorneyMailingAddress;

    @JsonIgnore
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
