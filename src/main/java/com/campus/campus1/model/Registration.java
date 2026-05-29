package com.campus.campus1.model;
import jakarta.persistence.*;
@Entity @Table(name = "registrations")
public class Registration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne private User user; 
    @ManyToOne private Event event;
    private String status,cancelReason;
    private String studentName,rollNo,branch,college,phone,email;
    
    public Long getId() {return id;} public void setId(Long id) {this.id = id;}
    public User getUser() {return user;} public void setUser(User user) {this.user = user;}
    public Event getEvent() {return event;} public void setEvent(Event event) {this.event = event;}
    public String getStatus() {return status;} public void setStatus(String status) {this.status = status;}
    public String getCancelReason() {return cancelReason;} public void setCancelReason(String cancelReason) {this.cancelReason = cancelReason;}
    public String getStudentName() {return studentName;} public void setStudentName(String studentName) {this.studentName = studentName;}
    public String getRollNo() {return rollNo;} public void setRollNo(String rollNo) {this.rollNo = rollNo;}
    public String getBranch() {return branch;} public void setBranch(String branch) {this.branch = branch;}
    public String getCollege() {return college;} public void setCollege(String college) {this.college = college;}
    public String getPhone() {return phone;} public void setPhone(String phone) {this.phone = phone;}
    public String getEmail() {return email;} public void setEmail(String email) {this.email = email;}
}