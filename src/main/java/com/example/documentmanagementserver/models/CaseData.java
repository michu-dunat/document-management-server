package com.example.documentmanagementserver.models;

import javax.persistence.*;

@Entity
public class CaseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private AdversePartyData adversePartyData;

    @OneToOne
    private ClientData clientData;

    @OneToOne
    private CourtData courtData;

    @OneToOne
    private ProceedingsSubject proceedingsSubject;

    public CaseData(int id, AdversePartyData adversePartyData, ClientData clientData, CourtData courtData, ProceedingsSubject proceedingsSubject) {
        this.id = id;
        this.adversePartyData = adversePartyData;
        this.clientData = clientData;
        this.courtData = courtData;
        this.proceedingsSubject = proceedingsSubject;
    }

    public CaseData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdversePartyData getAdversePartyData() {
        return adversePartyData;
    }

    public void setAdversePartyData(AdversePartyData adversePartyData) {
        this.adversePartyData = adversePartyData;
    }

    public ClientData getClientData() {
        return clientData;
    }

    public void setClientData(ClientData clientData) {
        this.clientData = clientData;
    }

    public CourtData getCourtData() {
        return courtData;
    }

    public void setCourtData(CourtData courtData) {
        this.courtData = courtData;
    }

    public ProceedingsSubject getProceedingsSubject() {
        return proceedingsSubject;
    }

    public void setProceedingsSubject(ProceedingsSubject proceedingsSubject) {
        this.proceedingsSubject = proceedingsSubject;
    }
}
