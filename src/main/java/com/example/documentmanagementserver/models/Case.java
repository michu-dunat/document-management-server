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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @NotNull
    @EqualsAndHashCode.Include
    private Client client;
    @ManyToOne(cascade = {CascadeType.ALL})
    @NotNull
    @EqualsAndHashCode.Include
    private Court court;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    @EqualsAndHashCode.Include
    private ProceedingsSubject proceedingsSubject;
    @ManyToOne(cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Include
    private AdverseParty adverseParty;

    public Case(Client client, Court court, ProceedingsSubject proceedingsSubject) {
        this.client = client;
        this.court = court;
        this.proceedingsSubject = proceedingsSubject;
    }
}
