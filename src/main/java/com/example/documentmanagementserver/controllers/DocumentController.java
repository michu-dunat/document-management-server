package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.models.Case;
import com.example.documentmanagementserver.models.Document;
import com.example.documentmanagementserver.repositories.CaseRepository;
import com.example.documentmanagementserver.repositories.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {
    private final DocumentRepository documentRepository;
    private final CaseRepository caseRepository;

    @PostMapping("/document/add/{caseId}")
    public ResponseEntity<Integer> addDocument(@PathVariable int caseId, @RequestBody Document document) {
        Optional<Case> aCase = caseRepository.findById(caseId);
        Document savedDocument;
        if (aCase.isPresent()) {
            document.setDocumentCase(aCase.get());
        }

        try {
            savedDocument = documentRepository.save(document);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedDocument.getId(), HttpStatus.OK);
    }

    @GetMapping("/document/list/{caseId}")
    @ResponseBody
    public List<Document> getAllDocumentForCase(@PathVariable int caseId) {
        return documentRepository.findAllByDocumentCase_Id(caseId);
    }

    @DeleteMapping("/document/delete/{documentId}")
    public ResponseEntity<Integer> deleteDocument(@PathVariable int documentId) {
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
        try {
            Document documentInDatabase = documentRepository.findById(document.getId()).get();
            document.setDocumentCase(documentInDatabase.getDocumentCase());
            if (document.getFile() == null) {
                document.setFile(documentInDatabase.getFile());
            }
            documentRepository.save(document);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping("/document/file/{documentId}")
    @ResponseBody
    public HashMap<String, byte[]> getFile(@PathVariable int documentId) {
        HashMap<String, byte[]> map = new HashMap<>();
        map.put("file", documentRepository.findById(documentId).get().getFile());
        return map;
    }
}


