package com.example.demo.controller;

import com.example.demo.model.Nurse;
import com.example.demo.model.Patient;
import com.example.demo.service.NurseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        Patient patient = nurseService.findPatient(firstName, lastName, dob);

        if (patient == null)
        {
            model.addAttribute("pageTitle", "Patient Not Found");
            model.addAttribute("subtitle", "No assigned nurse record was found for this patient.");
            model.addAttribute("nurse", new Nurse(
                "No Nurse Found",
                "unavailable@circlethecity.org",
                "N/A",
                "N/A",
                "/images/default-nurse.png",
                false
            ));
            model.addAttribute("busy", false);

            return "immediate-help";
        }

        Nurse assignedNurse = patient.getAssignedNurse();
        boolean busy = nurseService.isAssignedNurseBusy();

        if (!busy)
        {
            model.addAttribute("pageTitle", "Specific Nurse");
            model.addAttribute("subtitle", "Assigned nurse found and available.");
            model.addAttribute("nurse", assignedNurse);
            model.addAttribute("busy", false);

            return "immediate-help";
        }

        model.addAttribute("pageTitle", "Assigned Nurse Busy");
        model.addAttribute("subtitle", "The assigned nurse is currently busy.");
        model.addAttribute("nurse", assignedNurse);
        model.addAttribute("busy", true);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("dob", dob);

        return "immediate-help";
    }

    @PostMapping("/next-available-nurse")
    public String nextAvailableNurse(
        @RequestParam(value = "firstName", required = false) String firstName,
        @RequestParam(value = "lastName", required = false) String lastName,
        @RequestParam(value = "dob", required = false) String dob,
        Model model)
    {
        Nurse nurse = nurseService.getRandomAvailableNurse();

        model.addAttribute("pageTitle", "Next Available Nurse");
        model.addAttribute("subtitle", "Connected to the next available nurse.");
        model.addAttribute("nurse", nurse);
        model.addAttribute("busy", false);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("dob", dob);

        return "immediate-help";
    }

    @GetMapping("/immediate-help")
    public String immediateHelp(Model model)
    {
        Nurse nurse = nurseService.getRandomAvailableNurse();

        model.addAttribute("pageTitle", "Immediate Help");
        model.addAttribute("subtitle", "Here is a randomly available nurse:");
        model.addAttribute("nurse", nurse);
        model.addAttribute("busy", false);

        return "immediate-help";
    }

    @PostMapping("/leave-message")
    public String leaveMessagePage(
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName,
        @RequestParam("dob") String dob,
        @RequestParam("nurseId") Long nurseId,
        Model model)
    {
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("dob", dob);
        model.addAttribute("nurseId", nurseId);

        return "leave-message";
    }

    @PostMapping("/submit-message")
    public String submitMessage(
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName,
        @RequestParam("dob") String dob,
        @RequestParam("messageText") String messageText,
        @RequestParam("nurseId") Long nurseId,
        Model model)
    {
        Nurse nurse = new Nurse();
        nurse.setId(nurseId);

        nurseService.saveMessageRequest(firstName, lastName, dob, messageText, nurse);

        model.addAttribute("pageTitle", "Message Sent");
        model.addAttribute("message", "Your message has been saved. A nurse will follow up soon.");

        return "message-confirmation";
    }
}