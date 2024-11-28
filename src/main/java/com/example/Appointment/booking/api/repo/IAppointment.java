package com.example.Appointment.booking.api.repo;

import com.example.Appointment.booking.api.entity.Appointement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointment extends JpaRepository<Appointement,Integer> {
    Appointement findFirstById(Integer appointmentId);
}
