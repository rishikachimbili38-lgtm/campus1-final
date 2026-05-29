package com.campus.campus1.model;
import jakarta.persistence.*;
import java.time.LocalDate; 
import java.time.LocalTime;

@Entity 
@Table(name = "events")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String name; 
    private LocalDate date; 
    private LocalTime time;
    private String venue, description, category, organizer; 
    @Column(length = 1000) private String instruction;
    private String customerCare;
    private int totalSeats, availableSeats;
    
    public Long getId() {return id;} 
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;} 
    public void setName(String name) {this.name = name;}
    public LocalDate getDate() {return date;} 
    public void setDate(LocalDate date) {this.date = date;}
    public LocalTime getTime() {return time;} 
    public void setTime(LocalTime time) {this.time = time;}
    public String getVenue() {return venue;} 
    public void setVenue(String venue) {this.venue = venue;}
    public String getDescription() {return description;} 
    public void setDescription(String description) {this.description = description;}
    public String getCategory() {return category;} 
    public void setCategory(String category) {this.category = category;}
    public String getInstruction() {return instruction;} 
    public void setInstruction(String instruction) {this.instruction = instruction;}
    public String getCustomerCare() {return customerCare;} 
    public void setCustomerCare(String customerCare) {this.customerCare = customerCare;}
    public int getTotalSeats() {return totalSeats;} 
    public void setTotalSeats(int totalSeats) {this.totalSeats = totalSeats;}
    public int getAvailableSeats() {return availableSeats;} 
    public void setAvailableSeats(int availableSeats) {this.availableSeats = availableSeats;}
    public String getOrganizer() {return organizer;} 
    public void setOrganizer(String organizer) {this.organizer = organizer;}
}