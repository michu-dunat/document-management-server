package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.models.Case;
import com.example.documentmanagementserver.models.Document;
import com.example.documentmanagementserver.repositories.CaseRepository;
import com.example.documentmanagementserver.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    CaseRepository caseRepository;

    @PostMapping("/document/add/{caseId}")
    public ResponseEntity<Integer> addDocument(@PathVariable int caseId, @RequestBody Document document) {
        Optional<Case> aCase = caseRepository.findById(caseId);
        if (aCase.isPresent()) {
            document.setDocumentCase(aCase.get());
        }

        try {
            documentRepository.save(document);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping("/document/list/{caseId}")
    @ResponseBody
    public List<Document> getAllDocumentForCase(@PathVariable int caseId) {
        return documentRepository.findAllByDocumentCase_Id(caseId);
    }
}
