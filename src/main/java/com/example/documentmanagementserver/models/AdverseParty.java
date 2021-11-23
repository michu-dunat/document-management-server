package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
public class AdverseParty extends Client {

    @ManyToOne(cascade = {CascadeType.ALL})
    private AdversePartyAttorney adversePartyAttorney;

    @OneToMany(mappedBy = "adverseParty")
    @ToString.Exclude
    private List<Case> cases;
}
