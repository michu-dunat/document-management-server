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
public class AdversePartyAttorney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @EqualsAndHashCode.Include
    @NotNull
    private String firstnameAndLastName;
    @EqualsAndHashCode.Include
    @NotNull
    private String phoneNumber;
    @ManyToOne(cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Include
    @NotNull
    private Address residenceOrRegisteredOfficeAddress;
    @EqualsAndHashCode.Include
    @NotNull
    private Boolean isAttorneyProfessional;
    @EqualsAndHashCode.Include
    @NotNull
    private String jobTitle;
    @ManyToOne(cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Include
    private Address mailingAddress;

    @OneToMany(mappedBy = "adversePartyAttorney")
    @ToString.Exclude
    private List<AdverseParty> adverseParties;

    public AdversePartyAttorney(String firstnameAndLastName, String phoneNumber, Address residenceOrRegisteredOfficeAddress, Boolean isAttorneyProfessional, String jobTitle) {
        this.firstnameAndLastName = firstnameAndLastName;
        this.phoneNumber = phoneNumber;
        this.residenceOrRegisteredOfficeAddress = residenceOrRegisteredOfficeAddress;
        this.isAttorneyProfessional = isAttorneyProfessional;
        this.jobTitle = jobTitle;
    }
}
