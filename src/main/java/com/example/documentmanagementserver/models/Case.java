package com.example.documentmanagementserver.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "t_case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Client client;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Court court;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private ProceedingsSubject proceedingsSubject;
    @OneToOne(cascade = {CascadeType.ALL})
    private AdverseParty adverseParty;

    public Case(Client client, Court court, ProceedingsSubject proceedingsSubject) {
        this.client = client;
        this.court = court;
        this.proceedingsSubject = proceedingsSubject;
    }
}
