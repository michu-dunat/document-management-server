package com.example.documentmanagementserver.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProceedingsSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String value;
    private java.sql.Date fillingDate;
    private java.sql.Date claimReceiptDate;
    @NotNull
    private Boolean isMediationPossible;

    @OneToOne(mappedBy = "proceedingsSubject")
    @ToString.Exclude
    private Case aCase;

    public ProceedingsSubject(String value, Boolean isMediationPossible) {
        this.value = value;
        this.isMediationPossible = isMediationPossible;
    }
}
