package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(
            value = "SELECT *, 0 AS clazz_ FROM CLIENT co WHERE co.phone_number = ?1 OR co.krs = ?1 OR co.nip = ?1 OR co.pesel = ?1" +
                    " OR co.regon = ?1 OR co.email_address = ?1 OR co.first_name_last_name_company_name = ?1",
            nativeQuery = true)
    List<Client> findAllByAnything(String input);
}
