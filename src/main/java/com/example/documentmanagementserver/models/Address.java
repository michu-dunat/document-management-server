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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @EqualsAndHashCode.Include
    @NotNull
    private String city;
    @EqualsAndHashCode.Include
    @NotNull
    private String postcode;
    @EqualsAndHashCode.Include
    @NotNull
    private String street;
    @EqualsAndHashCode.Include
    @NotNull
    private String buildingNumber;
    @EqualsAndHashCode.Include
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

    public Address(String city, String postcode, String street, String buildingNumber) {
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }
}
