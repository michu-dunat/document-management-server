package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Proceeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String value;
    @NotNull
    private Boolean isMediationPossible;
    private String basisForMediation;
    private String comments;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "proceeding")
    private List<Entity> otherEntities;

    @JsonIgnore
    @OneToOne(mappedBy = "proceeding")
    @ToString.Exclude
    private Case aCase;

    public Proceeding(String value, Boolean isMediationPossible) {
        this.value = value;
        this.isMediationPossible = isMediationPossible;
    }

    public void addProceedingToAllSubjects() {
        for (Entity entity : this.otherEntities
        ) {
            entity.setProceeding(this);
        }
    }
}
