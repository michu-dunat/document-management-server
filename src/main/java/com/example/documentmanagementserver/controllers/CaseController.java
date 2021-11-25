package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.dtos.CaseForTable;
import com.example.documentmanagementserver.models.Case;
import com.example.documentmanagementserver.repositories.AddressRepository;
import com.example.documentmanagementserver.repositories.CaseRepository;
import com.example.documentmanagementserver.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class CaseController {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CaseService caseService;

    @PostMapping("/case/add")
    public ResponseEntity<Integer> addCase(@RequestBody Case aCase) {
        aCase.getCourt().addCourtToAllJudges();
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
            Optional<Case> optCase = caseRepository.findById(id);
            optCase.ifPresent(aCase -> {
                aCase.setStatus(status);
                caseRepository.save(aCase);
            });
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }
}
