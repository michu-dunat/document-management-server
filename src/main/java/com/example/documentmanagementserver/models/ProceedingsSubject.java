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
public class ProceedingsSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String value;
    private String fillingDate;
    private String claimReceiptDate;
    private Boolean isMediationPossible;

    @OneToOne(mappedBy = "proceedingsSubject")
    private CaseData caseData;

}
