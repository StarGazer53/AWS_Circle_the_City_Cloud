package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Long>
{
    List<Nurse> findByAvailableTrue();
}