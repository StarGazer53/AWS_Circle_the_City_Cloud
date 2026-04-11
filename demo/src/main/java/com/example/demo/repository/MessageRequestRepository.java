package com.example.demo.repository;

import com.example.demo.model.MessageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRequestRepository extends JpaRepository<MessageRequest, Long>
{
}