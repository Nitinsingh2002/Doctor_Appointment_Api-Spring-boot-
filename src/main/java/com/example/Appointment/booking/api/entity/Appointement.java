package com.example.Appointment.booking.api.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope =Appointement.class ,property = "id")
public class Appointement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private LocalDateTime creationTime;
    private LocalDateTime scheduleTime;

    @ManyToOne   //one represent patient entity Many represent  Appointment entity
    @JoinColumn(name = "fk_patientId")
    Patient patient;

    @ManyToOne   //one represent Doctor entity Many represent  Appointment entity
    @JoinColumn(name = "fk_doctor_Id")
    Doctor doctor;
}


//mapping a patient have multiple appointment and a doctor have multiple appointment
// a doctor have multiple appointment
//a patient have multiple appointment
//we keep bidirectional mapping