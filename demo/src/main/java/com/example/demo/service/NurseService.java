package com.example.demo.service;

import com.example.demo.model.Nurse;
import com.example.demo.repository.NurseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService
{
    private final NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository)
    {
        this.nurseRepository = nurseRepository;
    }

    @PostConstruct
    public void seedData()
    {
        if (nurseRepository.count() == 0)
        {
            nurseRepository.save(new Nurse(
                "Maria Gonzalez",
                "maria.gonzalez@circlethecity.org",
                "(602) 555-2048",
                "Primary Care",
                "/images/default-nurse.png",
                true
            ));

            nurseRepository.save(new Nurse(
                "Jessica Ramirez",
                "jessica.ramirez@circlethecity.org",
                "(602) 555-1024",
                "Care Coordination",
                "/images/default-nurse.png",
                true
            ));

            nurseRepository.save(new Nurse(
                "Ashley Turner",
                "ashley.turner@circlethecity.org",
                "(602) 555-3388",
                "Mobile Health Team",
                "/images/default-nurse.png",
                false
            ));
        }
    }

    public Nurse getFirstAvailableNurse()
    {
        List<Nurse> availableNurses = nurseRepository.findByAvailableTrue();

        if (!availableNurses.isEmpty())
        {
            return availableNurses.get(0);
        }

        return new Nurse(
            "No Nurse Available",
            "unavailable@circlethecity.org",
            "N/A",
            "N/A",
            "/images/default-nurse.png",
            false
        );
    }

    public Nurse findSpecificNurse(String firstName, String lastName, String dob)
    {
        List<Nurse> allNurses = nurseRepository.findAll();

        if (!allNurses.isEmpty())
        {
            return allNurses.get(0);
        }

        return new Nurse(
            "No Nurse Found",
            "unavailable@circlethecity.org",
            "N/A",
            "N/A",
            "/images/default-nurse.png",
            false
        );
    }
}