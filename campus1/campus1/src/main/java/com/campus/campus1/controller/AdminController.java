package com.campus.campus1.controller;
import com.campus.campus1.model.Event;
import com.campus.campus1.repository.EventRepository;
import com.campus.campus1.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired private EventRepository eventRepo;
    @Autowired private RegistrationRepository regRepo;
    
    @GetMapping("/admin")
    public String admin(Model m) {
        m.addAttribute("events", eventRepo.findAll());
        m.addAttribute("allRegs", regRepo.findAll());
        m.addAttribute("newEvent", new Event());
        return "admin";
    }
    
    @PostMapping("/admin/create")
    public String createEvent(@ModelAttribute Event e) {
        e.setAvailableSeats(e.getTotalSeats());
        eventRepo.save(e);
        return "redirect:/admin";
    }
    
    @PostMapping("/admin/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        regRepo.deleteAll(regRepo.findByEventId(id));
        eventRepo.deleteById(id);
        return "redirect:/admin";
    }
    
    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Event event = eventRepo.findById(id).orElseThrow();
        model.addAttribute("event", event);
        return "edit-event";
    }
    
    @PostMapping("/admin/update/{id}")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Event event) {
        Event existing = eventRepo.findById(id).orElseThrow();
        int seatsBooked = existing.getTotalSeats() - existing.getAvailableSeats();
        
        existing.setName(event.getName());
        existing.setCategory(event.getCategory());
        existing.setDate(event.getDate());
        existing.setTime(event.getTime());
        existing.setVenue(event.getVenue());
        existing.setOrganizer(event.getOrganizer());
        existing.setDescription(event.getDescription());
        existing.setInstruction(event.getInstruction());
        existing.setCustomerCare(event.getCustomerCare());
        existing.setTotalSeats(event.getTotalSeats());
        existing.setAvailableSeats(event.getTotalSeats() - seatsBooked);
        
        eventRepo.save(existing);
        return "redirect:/admin?success=updated";
    }
}