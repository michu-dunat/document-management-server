package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AdversePartyAttorneyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstnameAndLastname;
    private String phoneNumber;
    private String residenceOrHeadquartersNumber;
    private Boolean isAttorneyProfessional;
    private String jobTitle;

    @OneToOne
    @JoinColumn(name = "ADVERSE_PARTY_DATA_ID", referencedColumnName = "ID")
    private AdversePartyData adversePartyData;

}
