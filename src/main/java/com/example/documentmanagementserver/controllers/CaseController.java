package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.dtos.CaseForTable;
import com.example.documentmanagementserver.models.Case;
import com.example.documentmanagementserver.repositories.AddressRepository;
import com.example.documentmanagementserver.repositories.CaseRepository;
import com.example.documentmanagementserver.repositories.EntityRepository;
import com.example.documentmanagementserver.services.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class CaseController {

    private final CaseRepository caseRepository;
    private final AddressRepository addressRepository;
    private final EntityRepository entityRepository;
    private final CaseService caseService;

    @PostMapping("/case/add")
    public ResponseEntity<Integer> addCase(@RequestBody Case aCase) {
        aCase.getCourt().addCourtToAllSubjects();
        aCase.getProceeding().addProceedingToAllSubjects();
        try {
            caseRepository.save(aCase);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping("/case/table")
    @ResponseBody
    public List<CaseForTable> getCasesForTable() {
        return caseService.getAllCasesForTable();
    }

    @DeleteMapping("/case/delete/{id}")
    public ResponseEntity<Integer> deleteCase(@PathVariable(value = "id") int id) {
        Optional<Case> aCase = caseRepository.findById(id);
        if(aCase.isPresent()) {
            if (Objects.equals(aCase.get().getStatus(), "Zakończona")) {
                return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        try {
            caseRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PatchMapping("/case/status/{id}")
    public ResponseEntity<?> changeCaseStatus(@PathVariable(value = "id") int id, @RequestBody String status) {
        try {
            Case aCase = caseRepository.getById(id);
            aCase.setStatus(status);
            caseRepository.save(aCase);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping(value = "/case/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Optional<Case> getCase(@PathVariable(value = "id") int id) {
        Optional<Case> aCase = caseRepository.findById(id);
        return aCase;
    }

    @PutMapping("/case/update")
    public ResponseEntity<Integer> updateCase(@RequestBody Case aCase) {
        if (Objects.equals(aCase.getStatus(), "Zakończona")) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entityRepository.deleteAllByCourt(aCase.getCourt());
        entityRepository.deleteAllByProceeding(aCase.getProceeding());
        aCase.getCourt().addCourtToAllSubjects();
        aCase.getProceeding().addProceedingToAllSubjects();
        try {
            caseRepository.save(aCase);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping("/case/search/{input}")
    @ResponseBody
    public List<CaseForTable> getCasesForTableForSearch(@PathVariable String input) {
        return caseService.getAllCasesForSearch(input);
    }
}
