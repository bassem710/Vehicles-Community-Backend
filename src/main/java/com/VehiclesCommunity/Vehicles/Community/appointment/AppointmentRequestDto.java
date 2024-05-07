package com.VehiclesCommunity.Vehicles.Community.appointment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AppointmentRequestDto {
    private Date date;

    @Override
    public String toString() {
        return "AppointmentRequestDto{" +
                "date=" + date +
                '}';
    }
}