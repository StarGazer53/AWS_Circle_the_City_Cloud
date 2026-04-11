package com.example.demo.repository;

import com.example.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long>
{
    Optional<Patient> findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDob(
        String firstName,
        String lastName,
        LocalDate dob
    );
}