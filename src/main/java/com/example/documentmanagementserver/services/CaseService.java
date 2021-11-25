package com.example.documentmanagementserver.services;

import com.example.documentmanagementserver.dtos.CaseForTable;
import com.example.documentmanagementserver.models.Case;
import com.example.documentmanagementserver.repositories.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CaseService {

    @Autowired
    private CaseRepository caseRepository;

    public List<CaseForTable> getAllCasesForTable() {
        ArrayList<Case> cases = (ArrayList<Case>) caseRepository.findAll();
        ArrayList<CaseForTable> casesForTable = new ArrayList<>();
        for (Case aCase : cases
             ) {
            String label = aCase.getClient().getFirstnameAndLastNameOrCompanyName() + " kontra "
                    + aCase.getAdverseParty().getFirstnameAndLastNameOrCompanyName();
            Date fillingOrClaimingDate = null;
            if (aCase.getProceedingsSubject().getFillingDate() == null) {
                fillingOrClaimingDate = aCase.getProceedingsSubject().getClaimReceiptDate();
            } else {
                fillingOrClaimingDate = aCase.getProceedingsSubject().getFillingDate();
            }
            casesForTable.add(new CaseForTable(aCase.getId(), label, fillingOrClaimingDate, aCase.getStatus()));
        }
        return casesForTable;
    }
}
