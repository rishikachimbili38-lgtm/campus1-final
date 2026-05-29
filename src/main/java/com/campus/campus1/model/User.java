package com.campus.campus1.model;
import jakarta.persistence.*;
@Entity @Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String username,password,fullName,college,year,phone,role;
    public Long getId() {return id;} public void setId(Long id) {this.id = id;}
    public String getUsername() {return username;} public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;} public void setPassword(String password) {this.password = password;}
    public String getFullName() {return fullName;} public void setFullName(String fullName) {this.fullName = fullName;}
    public String getCollege() {return college;} public void setCollege(String college) {this.college = college;}
    public String getYear() {return year;} public void setYear(String year) {this.year = year;}
    public String getPhone() {return phone;} public void setPhone(String phone) {this.phone = phone;}
    public String getRole() {return role;} public void setRole(String role) {this.role = role;}
}