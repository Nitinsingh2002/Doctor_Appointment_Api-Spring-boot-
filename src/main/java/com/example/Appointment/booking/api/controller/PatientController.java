package com.example.Appointment.booking.api.controller;

import com.example.Appointment.booking.api.dto.SignInDto;
import com.example.Appointment.booking.api.dto.TokenDto;
import com.example.Appointment.booking.api.entity.Patient;
import com.example.Appointment.booking.api.service.PatientAuthService;
import com.example.Appointment.booking.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
    PatientAuthService patientAuthService;

    // signUp
    @PostMapping("patient/signUp")
    private  String  addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    //signIn
    @PostMapping("patient/signIn")
    private String signIn(@RequestBody SignInDto details){
      return patientService.signIn(details);
    }

    //signOut
    @DeleteMapping("/patient/signOut")
    private String signOut(@RequestBody TokenDto tokenDetails){
        return patientAuthService.singOut(tokenDetails);
    }


    @PostMapping("/authenticate")
    private Boolean Authenticated(@RequestBody TokenDto tokenDetails){
        return patientAuthService.checkAuthentication(tokenDetails);
    }


}
