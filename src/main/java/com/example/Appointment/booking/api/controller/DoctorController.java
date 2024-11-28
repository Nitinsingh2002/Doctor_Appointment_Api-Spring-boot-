package com.example.Appointment.booking.api.controller;


import com.example.Appointment.booking.api.dto.TokenDto;
import com.example.Appointment.booking.api.entity.Doctor;
import com.example.Appointment.booking.api.service.DoctorService;
import com.example.Appointment.booking.api.service.PatientAuthService;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    private    DoctorService doctorService;

    @Autowired
    private PatientAuthService patientAuthService;


    @PostMapping("/doctor/signUp")
    private String addDoctor(@RequestBody Doctor doctor){
        return  doctorService.addDoctor(doctor);
    }


    @PostMapping("/doctor")
    //only signUp patient can see list of doctor
    private List<Doctor> getDoctor(@RequestBody TokenDto tokenDetails){

        boolean result = patientAuthService.checkAuthentication(tokenDetails);
       if( result){
             return  doctorService.getDoctor();
       }else
       {
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
       }
    }
}
