package com.example.Appointment.booking.api.dto;


import com.example.Appointment.booking.api.entity.Appointement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDto {
  private   Appointement appointement;
  private TokenDto tokenDto;
}
