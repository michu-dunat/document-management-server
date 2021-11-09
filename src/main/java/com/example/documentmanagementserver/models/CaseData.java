package com.example.documentmanagementserver.models;

import javax.persistence.*;

@Entity
public class CaseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private AdversePartyData adversePartyData;

    @OneToOne
    private ClientData clientData;

    @OneToOne
    private CourtData courtData;

    @OneToOne
    private ProceedingsSubject proceedingsSubject;

}
