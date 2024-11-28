package com.example.Appointment.booking.api.service;

import com.example.Appointment.booking.api.repo.IAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    IAdmin iAdmin;

}
