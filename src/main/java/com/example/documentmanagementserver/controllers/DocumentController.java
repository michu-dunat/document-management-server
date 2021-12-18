package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.models.Case;
import com.example.documentmanagementserver.models.Document;
import com.example.documentmanagementserver.repositories.CaseRepository;
import com.example.documentmanagementserver.repositories.DocumentRepository;
import com.example.documentmanagementserver.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://document-management-client.herokuapp.com"})
public class DocumentController {
    private final DocumentRepository documentRepository;
    private final CaseRepository caseRepository;
    private final DocumentService documentService;

    @PostMapping("/document/add/{caseId}")
    public ResponseEntity<Integer> addDocument(@PathVariable int caseId, @RequestBody Document document) {
        Optional<Case> aCase = caseRepository.findById(caseId);
        if (aCase.isPresent() && aCase.get().isCaseFinished()) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        aCase.ifPresent(document::setDocumentCase);
        try {
            document = documentRepository.save(document);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(document.getId(), HttpStatus.OK);
    }

    @GetMapping("/document/list/{caseId}")
    public List<Document> getAllDocumentForCase(@PathVariable int caseId) {
        return documentService.getDocumentsForCase(caseId);
    }

    @DeleteMapping("/document/delete/{documentId}")
    public ResponseEntity<Integer> deleteDocument(@PathVariable int documentId) {
        Optional<Document> document = documentRepository.findById(documentId);
        if (document.isPresent() && document.get().getDocumentCase().isCaseFinished()) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            documentRepository.deleteById(documentId);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PutMapping("/document/update")
    public ResponseEntity<Integer> updateDocument(@RequestBody Document document) {
        Optional<Document> documentFromDatabase = documentRepository.findById(document.getId());
        if (documentFromDatabase.isPresent()) {
            if (documentFromDatabase.get().getDocumentCase().isCaseFinished()) {
                return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            documentService.setCaseAndFileOfDocumentToBeUpdated(document, documentFromDatabase.get());
        }
        try {
            documentRepository.save(document);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping("/document/file/{documentId}")
    public Map<String, byte[]> getFile(@PathVariable int documentId) {
        return documentService.getMapWithFileBytes(documentId);
    }
}


