package com.example.Appointment.booking.api.repo;

import com.example.Appointment.booking.api.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPatient extends JpaRepository<Patient,Integer> {

    Optional<Patient> findByEmail(String email);


    Patient findFirstByEmail(String email);
}
