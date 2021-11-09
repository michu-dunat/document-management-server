package com.example.documentmanagementserver.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AdversePartyData extends ClientData {

    @OneToOne(mappedBy="adversePartyData")
    private AdversePartyAttorneyData adversePartyAttorneyData;

    @OneToOne(mappedBy="adversePartyData")
    private CaseData caseData;

    public AdversePartyData(int id, String firstnameAndLastnameOrCompanyName, String PESEL, String NIP, String REGON, String KRS, String phoneNumber, String emailAddress, String residenceOrHeadquartersNumber, String mailingAddress, AdversePartyAttorneyData adversePartyAttorneyData) {
        super(id, firstnameAndLastnameOrCompanyName, PESEL, NIP, REGON, KRS, phoneNumber, emailAddress, residenceOrHeadquartersNumber, mailingAddress);
        this.adversePartyAttorneyData = adversePartyAttorneyData;
    }

    public AdversePartyData() {

    }

    public AdversePartyAttorneyData getAdversePartyAttorneyData() {
        return adversePartyAttorneyData;
    }

    public void setAdversePartyAttorneyData(AdversePartyAttorneyData adversePartyAttorneyData) {
        this.adversePartyAttorneyData = adversePartyAttorneyData;
    }
}
