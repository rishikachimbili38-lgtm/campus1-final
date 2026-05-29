package com.campus.campus1;

import com.campus.campus1.model.User;
import com.campus.campus1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Campus1Application {

    public static void main(String[] args) {
        SpringApplication.run(Campus1Application.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if(userRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setFullName("Admin");
                admin.setRole("ROLE_ADMIN");
                userRepo.save(admin);
            }

            if(userRepo.findByUsername("student").isEmpty()) {
                User student = new User();
                student.setUsername("student");
                student.setPassword(encoder.encode("admin123"));
                student.setFullName("Student");
                student.setRole("ROLE_STUDENT");
                userRepo.save(student);
            }
        };
    }
}