package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String home(Model model)
    {
        List<CareRequest> requests = List.of(
            new CareRequest("Maria Lopez", "602-555-1111", "Medication Refill"),
            new CareRequest("James Carter", "602-555-2222", "Appointment"),
            new CareRequest("Ana Torres", "602-555-3333", "Follow-Up")
        );

        model.addAttribute("title", "Circle the City Callback Queue");
        model.addAttribute("subtitle", "Cloud-based patient communication demo");
        model.addAttribute("requests", requests);

        return "home";
    }
}