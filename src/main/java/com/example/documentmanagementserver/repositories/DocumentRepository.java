package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
