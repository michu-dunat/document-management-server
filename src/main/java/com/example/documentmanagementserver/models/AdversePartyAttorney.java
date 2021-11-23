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
public class AdversePartyAttorney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstnameAndLastName;
    private String phoneNumber;
    @ManyToOne
    private Address residenceOrRegisteredOfficeAddress;
    private Boolean isAttorneyProfessional;
    private String jobTitle;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Address mailingAddress;

    @OneToMany(mappedBy = "adversePartyAttorney")
    @ToString.Exclude
    private List<AdverseParty> adverseParties;

}
