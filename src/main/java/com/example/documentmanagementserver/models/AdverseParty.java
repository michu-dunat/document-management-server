package com.example.documentmanagementserver.models;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class AdverseParty extends Client {

    @ManyToOne(cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Include
    @NotNull
    private AdversePartyAttorney adversePartyAttorney;

    @OneToMany(mappedBy = "adverseParty")
    @ToString.Exclude
    private List<Case> cases;

    public AdverseParty(String firstnameAndLastNameOrCompanyName, String pesel, String nip, String regon, String krs, String phoneNumber, String emailAddress, Address residenceOrRegisteredOfficeAddress, AdversePartyAttorney adversePartyAttorney) {
        super(firstnameAndLastNameOrCompanyName, pesel, nip, regon, krs, phoneNumber, emailAddress, residenceOrRegisteredOfficeAddress);
        this.adversePartyAttorney = adversePartyAttorney;
    }
}
