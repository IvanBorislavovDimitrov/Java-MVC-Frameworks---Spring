package com.ivan.resident_evil.repository;

import com.ivan.resident_evil.model.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, String> {

    List<Capital> findByNameIn(List<String> names);
}
