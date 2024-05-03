package com.VehiclesCommunity.Vehicles.Community.appointment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AppointmentRequestDto {
    private Integer userId;
    private Date date;

}