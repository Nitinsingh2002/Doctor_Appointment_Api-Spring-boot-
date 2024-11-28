package com.example.Appointment.booking.api.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope =Doctor.class ,property = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Integer id;
    private  String name;
    private  double fee;
    @Enumerated(EnumType.STRING)
    private Specilization specilization;
    @Enumerated(EnumType.STRING)
    private Qualification qualification;
    private String contactNumber;

    @OneToMany(mappedBy = "doctor")
    List<Appointement> appointements;

}
