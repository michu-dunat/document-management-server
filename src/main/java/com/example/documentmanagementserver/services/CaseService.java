package com.example.documentmanagementserver.services;

import com.example.documentmanagementserver.dtos.CaseForTable;
import com.example.documentmanagementserver.models.*;
import com.example.documentmanagementserver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProceedingRepository proceedingRepository;

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private AdversePartyAttorneyRepository adversePartyAttorneyRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdversePartyRepository adversePartyRepository;

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
//            java.sql.Date parsedDate = java.sql.Date.valueOf(searchInput);
//            proceedingsSubjects.addAll(proceedingsSubjectRepository.findAllByAnyDate(searchInput));
            proceedings.addAll(proceedingRepository.findAllByValue(searchInput));
        } catch (Exception e) {
            proceedings.addAll(proceedingRepository.findAllByValue(searchInput));
        }
        proceedings.forEach(proceeding -> caseSet.add(proceeding.getACase()));

        List<Entity> entities = entityRepository.findAllByFirstNameLastName(searchInput);
        entities.forEach(entity -> caseSet.add(entity.getCourt().getACase()));

        List<Court> courts = courtRepository.findAllByAnything(searchInput);
        courts.forEach(court -> caseSet.add(court.getACase()));

        List<AdversePartyAttorney> adversePartyAttorneys = adversePartyAttorneyRepository.findAllByAnything(searchInput);
        adversePartyAttorneys.forEach(adversePartyAttorney -> caseSet.add(adversePartyAttorney.getAdverseParty().getBCase()));

        List<Client> clients = clientRepository.findAllByAnything(searchInput);
        clients.forEach(client -> caseSet.add(client.getACase()));

        List<AdverseParty> adverseParties = adversePartyRepository.findAllByAnything(searchInput);
        adverseParties.forEach(adverseParty -> caseSet.add(adverseParty.getBCase()));

        caseSet.forEach(System.out::println);

        return prepareCasesForTable(caseSet);
    }
}
