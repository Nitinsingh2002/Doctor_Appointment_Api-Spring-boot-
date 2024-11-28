package com.example.Appointment.booking.api.repo;

import com.example.Appointment.booking.api.entity.PatientAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPauth extends JpaRepository<PatientAuthToken,Integer> {
    PatientAuthToken findByValue(String tokenValue);
}
