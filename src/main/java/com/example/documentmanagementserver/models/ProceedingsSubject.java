package com.example.documentmanagementserver.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProceedingsSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @EqualsAndHashCode.Include
    @NotNull
    private String value;
    @EqualsAndHashCode.Include
    private java.sql.Date fillingDate;
    @EqualsAndHashCode.Include
    private java.sql.Date claimReceiptDate;
    @EqualsAndHashCode.Include
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
