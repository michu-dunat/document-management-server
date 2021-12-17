package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "t_case")
public class Case {

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "documentCase")
    @ToString.Exclude
    List<Document> caseDocuments;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Client client;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private AdverseParty adverseParty;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Proceeding proceeding;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Court court;
    @NotNull
    private String status;

    public Case(Client client, AdverseParty adverseParty, Proceeding proceeding, Court court) {
        this.client = client;
        this.adverseParty = adverseParty;
        this.proceeding = proceeding;
        this.court = court;
        this.status = "W toku";
    }

    public boolean isCaseFinished() {
        return Objects.equals(this.status, "Zakończona");
    }
}
