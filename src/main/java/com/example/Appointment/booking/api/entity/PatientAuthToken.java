package com.example.Appointment.booking.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientAuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String value;

    private LocalDateTime creationTime;

    // each token should be linked with a patient
    @OneToOne
    @JoinColumn(name = "fk_patientId")
    Patient patient;

    public PatientAuthToken(Patient patient) {
        this.patient = patient;
        this.creationTime = LocalDateTime.now();
        this.value = UUID.randomUUID().toString();
    }
}
