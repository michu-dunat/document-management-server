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
public class AdversePartyAttorney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String firstNameLastName;
    @NotNull
    @Column(length = 20)
    private String phoneNumber;
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @NotNull
    private Address residenceOrRegisteredOfficeAddress;
    @NotNull
    private String title;
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Address mailingAddress;
    @NotNull
    private String emailAddress;

    @JsonIgnore
    @OneToOne(mappedBy = "adversePartyAttorney")
    @ToString.Exclude
    private AdverseParty adverseParty;

    public AdversePartyAttorney(String firstNameLastName, String phoneNumber, String emailAddress,
                                Address residenceOrRegisteredOfficeAddress, String title) {
        this.firstNameLastName = firstNameLastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.residenceOrRegisteredOfficeAddress = residenceOrRegisteredOfficeAddress;
        this.title = title;
    }
}
