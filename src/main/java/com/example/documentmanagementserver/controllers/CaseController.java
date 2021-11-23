package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.models.Case;
import com.example.documentmanagementserver.repositories.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class CaseController {

    @Autowired
    private CaseRepository caseRepository;

    @PostMapping("/case/add")
    public ResponseEntity<Integer> addCase(@RequestBody Case aCase) {
        //try {
        //    caseRepository.save(aCase);
        //} catch (Exception e) {
        //    return new ResponseEntity<>(406, HttpStatus.NOT_ACCEPTABLE);
        //}
        System.out.println(aCase);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }
}
