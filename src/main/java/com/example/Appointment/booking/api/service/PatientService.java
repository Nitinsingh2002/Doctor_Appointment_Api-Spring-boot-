package com.example.Appointment.booking.api.service;


import com.example.Appointment.booking.api.dto.SignInDto;
import com.example.Appointment.booking.api.dto.TokenDto;
import com.example.Appointment.booking.api.entity.Patient;
import com.example.Appointment.booking.api.entity.PatientAuthToken;
import com.example.Appointment.booking.api.repo.IPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    IPatient iPatient;

    @Autowired
    PatientAuthService patientAuthService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String addPatient(Patient patient) {

        String email = patient.getEmail();

        Optional<Patient> existingPatient = iPatient.findByEmail(email);

        if (existingPatient.isPresent()) {
            return "patient already registered";
        } else {
            patient.setPassword(encoder.encode(patient.getPassword()));
            iPatient.save(patient);
            return "patient registered";
        }
    }


    public String signIn(SignInDto details) {

        Patient patient = iPatient.findFirstByEmail(details.getEmail());

        if (patient != null) {
            if (encoder.matches(details.getPassword(), patient.getPassword())) {
                //to create token we have to create instance of auth class of entity
                PatientAuthToken token = new PatientAuthToken(patient);
                patientAuthService.addToken(token);
                return token.getValue();
            } else {
                return "incorrect credentials";
            }
        } else {
            return "User not found";
        }
    }


}
