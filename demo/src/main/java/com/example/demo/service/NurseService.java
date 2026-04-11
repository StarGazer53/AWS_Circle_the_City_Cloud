package com.example.demo.service;

import com.example.demo.model.MessageRequest;
import com.example.demo.model.Nurse;
import com.example.demo.model.Patient;
import com.example.demo.repository.MessageRequestRepository;
import com.example.demo.repository.NurseRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class NurseService
{
    private final NurseRepository nurseRepository;
    private final PatientRepository patientRepository;
    private final MessageRequestRepository messageRequestRepository;
    private final Random random = new Random();

    public NurseService(
        NurseRepository nurseRepository,
        PatientRepository patientRepository,
        MessageRequestRepository messageRequestRepository)
    {
        this.nurseRepository = nurseRepository;
        this.patientRepository = patientRepository;
        this.messageRequestRepository = messageRequestRepository;
    }

    public Patient findPatient(String firstName, String lastName, String dob)
    {
        LocalDate parsedDob = LocalDate.parse(dob);

        Optional<Patient> patient = patientRepository
            .findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDob(firstName, lastName, parsedDob);

        return patient.orElse(null);
    }

    public boolean isAssignedNurseBusy()
    {
        int chance = random.nextInt(100);
        return chance < 40;
    }

    public Nurse getRandomAvailableNurse()
    {
        List<Nurse> availableNurses = nurseRepository.findByAvailableTrue();

        if (availableNurses.isEmpty())
        {
            return new Nurse(
                "No Nurse Available",
                "unavailable@circlethecity.org",
                "N/A",
                "N/A",
                "/images/default-nurse.png",
                false
            );
        }

        int randomIndex = random.nextInt(availableNurses.size());
        return availableNurses.get(randomIndex);
    }

    public void saveMessageRequest(
        String firstName,
        String lastName,
        String dob,
        String messageText,
        Nurse nurse)
    {
        MessageRequest request = new MessageRequest();
        request.setPatientFirstName(firstName);
        request.setPatientLastName(lastName);
        request.setDob(LocalDate.parse(dob));
        request.setMessageText(messageText);
        request.setNurse(nurse);
        request.setCreatedAt(LocalDateTime.now());

        messageRequestRepository.save(request);
    }
}