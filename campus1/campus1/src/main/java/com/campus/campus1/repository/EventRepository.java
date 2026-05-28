package com.campus.campus1.repository;
import com.campus.campus1.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EventRepository extends JpaRepository<Event, Long> {}