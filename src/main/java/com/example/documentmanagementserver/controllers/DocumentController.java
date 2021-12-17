package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.models.Case;
import com.example.documentmanagementserver.models.Document;
import com.example.documentmanagementserver.repositories.CaseRepository;
import com.example.documentmanagementserver.repositories.DocumentRepository;
import com.example.documentmanagementserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {
    private final DocumentRepository documentRepository;
    private final CaseRepository caseRepository;
    private final UserRepository userRepository;

    @PostMapping("/document/add/{caseId}")
    public ResponseEntity<Integer> addDocument(@PathVariable int caseId, @RequestBody Document document) {
        Optional<Case> aCase = caseRepository.findById(caseId);
        if (aCase.isPresent() && Objects.equals(aCase.get().getStatus(), "Zakończona")) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        aCase.ifPresent(document::setDocumentCase);
        Document savedDocument;
        try {
            savedDocument = documentRepository.save(document);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedDocument.getId(), HttpStatus.OK);
    }

    @GetMapping("/document/list/{caseId}")
    public List<Document> getAllDocumentForCase(@PathVariable int caseId) {
        List<Document> documents = documentRepository.findAllByDocumentCase_Id(caseId);
        for (Document document : documents
        ) {
            document.getSender().setPassword("");
            document.getSender().setEmailAddress("");
            document.getSender().setRole(null);
        }
        return documents;
    }

    @DeleteMapping("/document/delete/{documentId}")
    public ResponseEntity<Integer> deleteDocument(@PathVariable int documentId) {
        Optional<Document> document = documentRepository.findById(documentId);
        if (document.isPresent()) {
            if (Objects.equals(document.get().getDocumentCase().getStatus(), "Zakończona")) {
                return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        try {
            documentRepository.deleteById(documentId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PutMapping("/document/update")
    public ResponseEntity<Integer> updateDocument(@RequestBody Document document) {
        Optional<Document> documentFromDatabase = documentRepository.findById(document.getId());
        if(documentFromDatabase.isPresent()) {
            if (Objects.equals(documentFromDatabase.get().getDocumentCase().getStatus(), "Zakończona")) {
                return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            document.setDocumentCase(documentFromDatabase.get().getDocumentCase());
            if (document.getFile() == null) {
                document.setFile(documentFromDatabase.get().getFile());
            }
        }
        try {
            documentRepository.save(document);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping("/document/file/{documentId}")
    public Map<String, byte[]> getFile(@PathVariable int documentId) {
        Map<String, byte[]> map = new HashMap<>();
        Optional<Document> document = documentRepository.findById(documentId);
        document.ifPresent(value -> map.put("file", value.getFile()));
        return map;
    }
}


