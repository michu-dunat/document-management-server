package com.example.documentmanagementserver.services;

import com.example.documentmanagementserver.dtos.CaseForTable;
import com.example.documentmanagementserver.models.*;
import com.example.documentmanagementserver.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CaseService {
    private final CaseRepository caseRepository;
    private final AddressRepository addressRepository;
    private final ProceedingRepository proceedingRepository;
    private final EntityRepository entityRepository;
    private final CourtRepository courtRepository;
    private final AdversePartyAttorneyRepository adversePartyAttorneyRepository;
    private final ClientRepository clientRepository;
    private final AdversePartyRepository adversePartyRepository;

    public void removeEntitiesFromDatabase(Case aCase) {
        entityRepository.deleteAllByCourt(aCase.getCourt());
        entityRepository.deleteAllByProceeding(aCase.getProceeding());

    }


    public void setCourtOrProceedingToEntities(Case aCase) {
        aCase.getCourt().addCourtToAllEntities();
        aCase.getProceeding().addProceedingToAllEntities();
    }


    private List<CaseForTable> prepareCasesForTable(Collection<Case> cases) {
        ArrayList<CaseForTable> casesForTable = new ArrayList<>();
        for (Case aCase : cases
        ) {
            String label = aCase.getClient().getFirstNameLastNameCompanyName() + " kontra "
                    + aCase.getAdverseParty().getFirstNameLastNameCompanyName();
            casesForTable.add(new CaseForTable(aCase.getId(), label, aCase.getStatus()));
        }
        return casesForTable;
    }

    public List<CaseForTable> getAllCasesForTable() {
        ArrayList<Case> cases = (ArrayList<Case>) caseRepository.findAll();
        return prepareCasesForTable(cases);
    }

    public List<CaseForTable> getAllCasesForSearch(String searchInput) {
        Set<Case> caseSet = new HashSet<>();

        List<Address> addresses = addressRepository.findAllByAnything(searchInput);
        addresses.forEach(address -> {
            if (address.getAdversePartyAttorneyMailingAddress() != null) {
                caseSet.add(address.getAdversePartyAttorneyMailingAddress().getAdverseParty().getBCase());
            }
            if (address.getAdversePartyAttorneyResidenceOrRegisteredOfficeAddress() != null) {
                caseSet.add(address.getAdversePartyAttorneyResidenceOrRegisteredOfficeAddress().getAdverseParty().getBCase());
            }
            if (address.getClientMailingAddress() != null) {
                caseSet.add(address.getClientMailingAddress().getACase());
            }
            if (address.getClientResidenceOrRegisteredOfficeAddress() != null) {
                caseSet.add(address.getClientResidenceOrRegisteredOfficeAddress().getACase());
            }
            if (address.getCourt() != null) {
                caseSet.add(address.getCourt().getACase());
            }
        });

        List<Proceeding> proceedings = new ArrayList<>();
        try {
            proceedings.addAll(proceedingRepository.findAllByAnything(searchInput));
        } catch (Exception e) {
            proceedings.addAll(proceedingRepository.findAllByAnything(searchInput));
        }
        proceedings.forEach(proceeding -> caseSet.add(proceeding.getACase()));

        List<Entity> entities = entityRepository.findAllByAnything(searchInput);
        entities.forEach(entity -> caseSet.add(entity.getCourt().getACase()));

        List<Court> courts = courtRepository.findAllByAnything(searchInput);
        courts.forEach(court -> caseSet.add(court.getACase()));

        List<AdversePartyAttorney> adversePartyAttorneys = adversePartyAttorneyRepository.findAllByAnything(searchInput);
        adversePartyAttorneys.forEach(adversePartyAttorney -> caseSet.add(adversePartyAttorney.getAdverseParty().getBCase()));

        List<Client> clients = clientRepository.findAllByAnything(searchInput);
        clients.forEach(client -> caseSet.add(client.getACase()));

        List<AdverseParty> adverseParties = adversePartyRepository.findAllByAnything(searchInput);
        adverseParties.forEach(adverseParty -> caseSet.add(adverseParty.getBCase()));

        return prepareCasesForTable(caseSet);
    }
}
