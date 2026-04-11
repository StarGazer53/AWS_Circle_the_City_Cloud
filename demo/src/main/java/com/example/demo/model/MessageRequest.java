package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "message_request")
public class MessageRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_first_name")
    private String patientFirstName;

    @Column(name = "patient_last_name")
    private String patientLastName;

    private LocalDate dob;

    @Column(name = "message_text")
    private String messageText;

    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public MessageRequest()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPatientFirstName()
    {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName)
    {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName()
    {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName)
    {
        this.patientLastName = patientLastName;
    }

    public LocalDate getDob()
    {
        return dob;
    }

    public void setDob(LocalDate dob)
    {
        this.dob = dob;
    }

    public String getMessageText()
    {
        return messageText;
    }

    public void setMessageText(String messageText)
    {
        this.messageText = messageText;
    }

    public Nurse getNurse()
    {
        return nurse;
    }

    public void setNurse(Nurse nurse)
    {
        this.nurse = nurse;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }
}