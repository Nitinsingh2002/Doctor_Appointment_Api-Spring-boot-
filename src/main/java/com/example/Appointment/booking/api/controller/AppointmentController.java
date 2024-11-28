package com.example.Appointment.booking.api.controller;


import com.example.Appointment.booking.api.dto.AppointmentDto;
import com.example.Appointment.booking.api.service.AppointmentService;
import com.example.Appointment.booking.api.service.PatientAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;



    // patient can create  schedule an appointment only login user can schedule their appointment
    @PostMapping("/appointment")
    private String addAppointment(@RequestBody AppointmentDto appointmentDetails) {
          try {
              return appointmentService.addAppointment(appointmentDetails);
          } catch (Exception e) {
              return e.getMessage();
          }
    }


    //patient can can cancel an appointment
    @DeleteMapping("/appointment")
    private  String cancelAppointment(@RequestBody AppointmentDto appointmentDto){
        try{
            return appointmentService.cancelAppontment(appointmentDto);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
