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
public class AdversePartyAttorney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String firstnameAndLastName;
    @NotNull
    private String phoneNumber;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Address residenceOrRegisteredOfficeAddress;
    @NotNull
    private Boolean isAttorneyProfessional;
    @NotNull
    private String jobTitle;
    @OneToOne(cascade = {CascadeType.ALL})
    private Address mailingAddress;

    @OneToOne(mappedBy = "adversePartyAttorney")
    @ToString.Exclude
    private AdverseParty adverseParty;

    public AdversePartyAttorney(String firstnameAndLastName, String phoneNumber, Address residenceOrRegisteredOfficeAddress, Boolean isAttorneyProfessional, String jobTitle) {
        this.firstnameAndLastName = firstnameAndLastName;
        this.phoneNumber = phoneNumber;
        this.residenceOrRegisteredOfficeAddress = residenceOrRegisteredOfficeAddress;
        this.isAttorneyProfessional = isAttorneyProfessional;
        this.jobTitle = jobTitle;
    }
}
