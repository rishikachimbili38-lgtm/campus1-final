package com.campus.campus1.controller;

import com.campus.campus1.model.*; 
import com.campus.campus1.repository.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class StudentController {
    private final EventRepository eventRepo; 
    private final RegistrationRepository regRepo; 
    private final UserRepository userRepo;
    
    public StudentController(EventRepository eventRepo, RegistrationRepository regRepo, UserRepository userRepo) {
        this.eventRepo = eventRepo; 
        this.regRepo = regRepo; 
        this.userRepo = userRepo;
    }

    @GetMapping("/student") 
    public String student(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("events", eventRepo.findAll());
        model.addAttribute("myRegs", regRepo.findByUserId(user.getId()));
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("currentTime", LocalTime.now());
        return "student";
    }

    @GetMapping("/student/register-form/{eventId}")
    public String showRegisterForm(@PathVariable Long eventId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Event event = eventRepo.findById(eventId).orElseThrow();

        // Block completed events
        if(event.getDate().isBefore(LocalDate.now()) || 
           (event.getDate().isEqual(LocalDate.now()) && event.getTime().isBefore(LocalTime.now()))) {
            return "redirect:/student?error=event_completed";
        }

        Registration existing = regRepo.findByUserIdAndEventId(user.getId(), eventId);
        if(existing != null && "REGISTERED".equals(existing.getStatus())) {
            return "redirect:/student?error=already_registered";
        }
        if(event.getAvailableSeats() <= 0) {
            return "redirect:/student?error=no_seats";
        }

        model.addAttribute("event", event);
        model.addAttribute("registration", new Registration());
        return "register-form";
    }

    @PostMapping("/student/register/{eventId}") 
    @Transactional
    public String register(
            @PathVariable Long eventId,
            @AuthenticationPrincipal UserDetails userDetails,
            @ModelAttribute Registration registration) {

        User user = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Event event = eventRepo.findById(eventId).orElseThrow();

        // Block completed events
        if(event.getDate().isBefore(LocalDate.now()) || 
           (event.getDate().isEqual(LocalDate.now()) && event.getTime().isBefore(LocalTime.now()))) {
            return "redirect:/student?error=event_completed";
        }

        Registration existing = regRepo.findByUserIdAndEventId(user.getId(), eventId);
        if(existing != null && "CANCELLED".equals(existing.getStatus())) {
            regRepo.delete(existing);
        }

        if(event.getAvailableSeats() > 0) {
            registration.setUser(user);
            registration.setEvent(event);
            registration.setStatus("REGISTERED");
            regRepo.save(registration);

            event.setAvailableSeats(event.getAvailableSeats() - 1);
            eventRepo.save(event);
        }
        return "redirect:/student?success=registered";
    }

    @PostMapping("/student/cancel/{regId}") 
    @Transactional
    public String cancel(@PathVariable Long regId, @RequestParam String reason) {
        Registration reg = regRepo.findById(regId).orElseThrow();
        if("REGISTERED".equals(reg.getStatus())) {
            reg.setStatus("CANCELLED"); 
            reg.setCancelReason(reason); 
            regRepo.save(reg);
            Event event = reg.getEvent(); 
            event.setAvailableSeats(event.getAvailableSeats() + 1); 
            eventRepo.save(event);
        }
        return "redirect:/student?success=cancelled";
    }
}