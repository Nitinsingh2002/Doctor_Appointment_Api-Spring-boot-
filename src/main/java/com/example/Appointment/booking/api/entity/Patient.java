package com.example.Appointment.booking.api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope =Patient.class ,property = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private  Gender gender;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodgroup;

    private String contactNumber;

    private LocalDateTime dateOfBirth;

    @OneToMany(mappedBy = "patient")
    List<Appointement>appointements;
}
