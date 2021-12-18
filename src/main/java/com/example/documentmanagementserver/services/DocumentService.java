package com.example.documentmanagementserver.services;

import com.example.documentmanagementserver.models.Document;
import com.example.documentmanagementserver.repositories.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;

    public Map<String, byte[]> getMapWithFileBytes(int documentId) {
        Map<String, byte[]> map = new HashMap<>();
        Optional<Document> document = documentRepository.findById(documentId);
        document.ifPresent(value -> map.put("file", value.getFile()));
        return map;
    }

    public List<Document> getDocumentsForCase(int caseId) {
        List<Document> documents = documentRepository.findAllByDocumentCase_Id(caseId);
        removeUserSensitiveInformation(documents);
        return documents;
    }

    public void setCaseAndFileOfDocumentToBeUpdated(Document documentToBeUpdated, Document documentFromDatabase) {
        documentToBeUpdated.setDocumentCase(documentFromDatabase.getDocumentCase());
        if (documentToBeUpdated.getFile() == null) {
            documentToBeUpdated.setFile(documentFromDatabase.getFile());
        }
    }

    private void removeUserSensitiveInformation(List<Document> documents) {
        for (Document document : documents) {
            if (document.getSender() != null) {
                document.getSender().setPassword("");
                document.getSender().setEmailAddress("");
                document.getSender().setRole(null);
            }
        }
    }
}
