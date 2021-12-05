package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(
            value = "SELECT * FROM ADDRESS a WHERE a.city = ?1 OR a.postcode = ?1 OR a.street = ?1",
            nativeQuery = true)
    List<Address> findAllByAnything(String input);
}
