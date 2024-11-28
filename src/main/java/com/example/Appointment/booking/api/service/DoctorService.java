package com.example.Appointment.booking.api.service;


import com.example.Appointment.booking.api.entity.Doctor;
import com.example.Appointment.booking.api.repo.IDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    IDoctor iDoctor;

    public String addDoctor(Doctor doctor) {
        Integer doctorId =doctor.getId();

        if(doctorId == null){
            return "Doctor can't be null";
        } else if (iDoctor.existsById(doctorId)) {
            return "Doctor already exist";
        }else {
             //we have only register doctor so we make appointment null if anyone pose
             // appointment it automatic null ate the time of posting doctor we are just doing for safety purpose
            doctor.setAppointements(null);
            iDoctor.save(doctor);
            return "Doctor added successfully";
        }
    }

    public List<Doctor> getDoctor() {
        System.out.println("Line executed");
       return iDoctor.findAll();
    }
}
