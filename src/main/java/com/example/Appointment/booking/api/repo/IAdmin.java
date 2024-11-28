package com.example.Appointment.booking.api.repo;

import com.example.Appointment.booking.api.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdmin extends JpaRepository<Admin,Integer> {
}
