package com.ivan.resident_evil.repository;

import com.ivan.resident_evil.model.Virus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirusRepository extends JpaRepository<Virus, String> {
}
