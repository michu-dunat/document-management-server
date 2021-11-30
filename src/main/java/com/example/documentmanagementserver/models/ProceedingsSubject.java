package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @JsonIgnore
    @OneToOne(mappedBy = "proceedingsSubject")
    @ToString.Exclude
    private Case aCase;

    public ProceedingsSubject(String value, Boolean isMediationPossible) {
        this.value = value;
        this.isMediationPossible = isMediationPossible;
    }
}
