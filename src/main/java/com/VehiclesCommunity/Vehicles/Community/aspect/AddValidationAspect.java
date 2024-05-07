package com.VehiclesCommunity.Vehicles.Community.aspect;

import com.VehiclesCommunity.Vehicles.Community.appointment.AppointmentRequestDto;
import com.VehiclesCommunity.Vehicles.Community.event.Event;
import com.VehiclesCommunity.Vehicles.Community.news.News;
import com.VehiclesCommunity.Vehicles.Community.vehicle.Vehicle;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Aspect
@Component
public class AddValidationAspect {
    @Before("execution(* com.VehiclesCommunity.Vehicles.Community.vehicle.VehicleController.addNewVehicle(..)) && args(vehicle)")
    public void beforeAddNewVehicle(Vehicle vehicle) {
        if (vehicle == null || !isValidVehicle(vehicle)) {
            throw new IllegalArgumentException("Invalid vehicle data");
        }
    }

    private boolean isValidVehicle(Vehicle vehicle) {
        return vehicle.getTitle() != null &&
                vehicle.getDescription() != null &&
                vehicle.getPrice() > 0 &&
                vehicle.getImage() != null;
    }

    @Before("execution(* com.VehiclesCommunity.Vehicles.Community.appointment.AppointmentController.addNewAppointment(..)) && args(requestDto,request)")
    public void beforeAddNewAppointment(AppointmentRequestDto requestDto ,HttpServletRequest request) {
        if (requestDto == null || !isValidAppointment(requestDto)){
            throw new IllegalArgumentException("Invalid appointment data");
        }
    }
    private boolean isValidAppointment(AppointmentRequestDto appointmnetRequestDto) {
        return appointmnetRequestDto.getDate() != null;
    }

    @Before("execution(* com.VehiclesCommunity.Vehicles.Community.event.EventController.addEvent(..)) && args(event)")
    public void beforeAddNewEvent(Event event) {
        if (event == null || !isValidEvent(event)) {
            throw new IllegalArgumentException("Invalid event data");
        }
    }

    private boolean isValidEvent(Event event) {
        return event.getTitle() != null &&
                event.getDescription() != null&&
                event.getLocation() != null &&
                event.getDate() != null;
    }

    @Before("execution(* com.VehiclesCommunity.Vehicles.Community.news.NewsController.addNewNews(..)) && args(news)")
    public void beforeAddNewNews(News news) {
        if (news == null || !isValidNews(news)) {
            throw new IllegalArgumentException("Invalid news data");
        }
    }

    private boolean isValidNews(News news) {
        return news.getTitle() != null &&
                news.getDescription() != null &&
                news.getImage() != null;
    }
}
