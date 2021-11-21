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
