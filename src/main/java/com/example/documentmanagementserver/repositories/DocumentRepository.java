package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findAllByDocumentCase_Id(int caseId);
}
