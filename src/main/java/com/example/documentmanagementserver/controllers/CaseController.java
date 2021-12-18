package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.dtos.CaseForTable;
import com.example.documentmanagementserver.models.Case;
import com.example.documentmanagementserver.repositories.CaseRepository;
import com.example.documentmanagementserver.services.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://document-management-client.herokuapp.com"})
public class CaseController {
    private final CaseRepository caseRepository;
    private final CaseService caseService;

    @PostMapping("/case/add")
    public ResponseEntity<Integer> addCase(@RequestBody Case aCase) {
        caseService.setCourtOrProceedingToEntities(aCase);
        aCase.getProceeding().setBasisForMediationToNullIfMediationIsNotPossible();
        try {
            caseRepository.save(aCase);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @DeleteMapping("/case/delete/{id}")
    public ResponseEntity<Integer> deleteCase(@PathVariable(value = "id") int id) {
        Case aCase = caseRepository.getById(id);
        if (aCase.isCaseFinished()) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            caseRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PutMapping("/case/update")
    public ResponseEntity<Integer> updateCase(@RequestBody Case aCase) {
        if (aCase.isCaseFinished()) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        aCase.getProceeding().setBasisForMediationToNullIfMediationIsNotPossible();
        caseService.removeEntitiesFromDatabase(aCase);
        caseService.setCourtOrProceedingToEntities(aCase);
        try {
            caseRepository.save(aCase);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PatchMapping("/case/status/{id}")
    public ResponseEntity<Integer> changeCaseStatus(@PathVariable(value = "id") int id, @RequestBody String status) {
        Case aCase = caseRepository.getById(id);
        aCase.setStatus(status);
        try {
            caseRepository.save(aCase);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping(value = "/case/{id}")
    public Optional<Case> getCase(@PathVariable(value = "id") int id) {
        return caseRepository.findById(id);
    }

    @GetMapping("/case/table")
    public List<CaseForTable> getCasesForTable() {
        return caseService.getAllCasesForTable();
    }

    @GetMapping("/case/search/{input}")
    public List<CaseForTable> getCasesForTableForSearch(@PathVariable String input) {
        return caseService.getAllCasesForSearch(input);
    }
}
