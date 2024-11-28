package com.example.Appointment.booking.api.repo;

import com.example.Appointment.booking.api.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctor extends JpaRepository<Doctor,Integer> {
    Doctor findFirstById(Integer doctorId);
}
