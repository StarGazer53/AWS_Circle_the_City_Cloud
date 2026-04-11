package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Nurse;
import com.example.demo.service.NurseService;

@Controller
public class HomeController
{
    private final NurseService nurseService;

    public HomeController(NurseService nurseService)
    {
        this.nurseService = nurseService;
    }

    @GetMapping("/")
    public String home()
    {
        return "home";
    }

    @GetMapping("/specific-nurse")
    public String specificNursePage()
    {
        return "specific-nurse";
    }

    @PostMapping("/connect-specific-nurse")
    public String connectSpecificNurse(
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName,
        @RequestParam("dob") String dob,
        Model model)
    {
        Nurse nurse = nurseService.findSpecificNurse(firstName, lastName, dob);

        model.addAttribute("pageTitle", "Specific Nurse");
        model.addAttribute("subtitle", "Here is the nurse connected to the patient request.");
        model.addAttribute("patientFirstName", firstName);
        model.addAttribute("patientLastName", lastName);
        model.addAttribute("dob", dob);
        model.addAttribute("nurse", nurse);

        return "immediate-help";
    }

    @GetMapping("/immediate-help")
    public String immediateHelp(Model model)
    {
        Nurse nurse = nurseService.getFirstAvailableNurse();

        model.addAttribute("pageTitle", "Immediate Help");
        model.addAttribute("subtitle", "Here is the first nurse available:");
        model.addAttribute("nurse", nurse);

        return "immediate-help";
    }
}