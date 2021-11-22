package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AdverseParty extends Client {

    @ManyToOne
    private AdversePartyAttorney adversePartyAttorney;

    @OneToMany(mappedBy = "adverseParty")
    private List<Case> cases;
}
