package com.example.Appointment.booking.api.service;


import com.example.Appointment.booking.api.dto.TokenDto;
import com.example.Appointment.booking.api.entity.PatientAuthToken;
import com.example.Appointment.booking.api.repo.IPauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientAuthService {
    @Autowired
    IPauth iPauth;


    public void addToken(PatientAuthToken patientAuthToken) {
        iPauth.save(patientAuthToken);
    }

    public String singOut(TokenDto tokenDetails) {
        String email = tokenDetails.getEmail();
        String tokenValue = tokenDetails.getToken();

        PatientAuthToken patientTokenEntity = iPauth.findByValue(tokenValue);
        if (patientTokenEntity != null) {
            if (patientTokenEntity.getPatient().getEmail().equals(email)) {
                iPauth.delete(patientTokenEntity);
                return "Logout successfully";
            } else {
                return "You are not authorized";
            }
        } else {
            return "You are not authorized";
        }
    }


    public Boolean checkAuthentication(TokenDto tokenDetails) {
        String email = tokenDetails.getEmail();
        String token = tokenDetails.getToken();

       PatientAuthToken patientAuthToken = iPauth.findByValue(token);

       if(patientAuthToken != null && patientAuthToken.getPatient() != null
               && patientAuthToken.getPatient().getEmail().equals(email) ){
           return true;
       }
       return  false;
    }
}
