package com.example.Appointment.booking.api.service;

import com.example.Appointment.booking.api.dto.AppointmentDto;
import com.example.Appointment.booking.api.dto.TokenDto;
import com.example.Appointment.booking.api.entity.Appointement;
import com.example.Appointment.booking.api.entity.Doctor;
import com.example.Appointment.booking.api.entity.Patient;
import com.example.Appointment.booking.api.repo.IAppointment;
import com.example.Appointment.booking.api.repo.IDoctor;
import com.example.Appointment.booking.api.repo.IPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    IAppointment iAppointment;


    @Autowired
    PatientAuthService patientAuthService;

    @Autowired
   IPatient iPatient;

    @Autowired
    IDoctor iDoctor;

    public String addAppointment(AppointmentDto appointmentDetails) {

       //checking user login or not this method take token and email and tokenDto contain both of them
       if(patientAuthService.checkAuthentication(appointmentDetails.getTokenDto())){


           //finding user as per token  means user can only book appointment for themselves
           String patientEmail = appointmentDetails.getTokenDto().getEmail();
           Patient patient = iPatient.findFirstByEmail(patientEmail);
           if(patient == null){
               return "patient not found";
           }

           //finding doctor by doctor id that is percent in appointment details
           Integer doctorId = appointmentDetails.getAppointement().getDoctor().getId();
           Doctor doctor = iDoctor.findFirstById(doctorId);

           if(doctor == null){
               return "doctor not found";
           }

           Appointement  appointement = appointmentDetails.getAppointement();

           appointement.setPatient(patient);
           appointement.setDoctor(doctor);

           iAppointment.save(appointement);
           return "appointment created successfully";
       }else {
          return "You are not authorized";
       }
    }


    public String cancelAppontment(AppointmentDto appointmentDto) {
        //checking authorization means only login user can delete
      TokenDto tokenDto = appointmentDto.getTokenDto();
        if (!patientAuthService.checkAuthentication(tokenDto)) {
            return "You are not authorized";
        }

        // Validate appointment and patient association
        Appointement appointment = appointmentDto.getAppointement();
        if (appointment == null || appointment.getPatient() == null) {
            return "Invalid appointment details";
        }


        String patientEmail = appointment.getPatient().getEmail();
        String authEmail = tokenDto.getEmail();

        // Ensure the appointment belongs to the user making the request
        if (!patientEmail.equals(authEmail)) {
            return "You can't delete this appointment";
        }

       // Fetch the appointment from the database
        Integer appointmentId = appointment.getId();
        Appointement existingAppointment = iAppointment.findFirstById(appointmentId);

        if (existingAppointment == null) {
            return "Appointment not found";
        }

        // Delete the appointment
        iAppointment.delete(existingAppointment);
        return "Appointment deleted successfully";
    }
}
