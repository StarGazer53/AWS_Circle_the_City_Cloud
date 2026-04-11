package com.example.demo.repository;

import com.example.demo.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NurseRepository extends JpaRepository<Nurse, Long>
{
    List<Nurse> findByAvailableTrue();
}