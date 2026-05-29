package com.campus.campus1.repository;
import com.campus.campus1.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUserId(Long userId);
    Registration findByUserIdAndEventId(Long userId, Long eventId);
    List<Registration> findByEventId(Long eventId);
}