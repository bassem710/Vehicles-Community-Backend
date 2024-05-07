package com.VehiclesCommunity.Vehicles.Community.aspect;

import com.VehiclesCommunity.Vehicles.Community.vehicle.Vehicle;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddValidationAspect {
    @Before("execution(* com.VehiclesCommunity.Vehicles.Community.vehicle.VehicleService.addNewVehicle(..)) && args(vehicle)")
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
}
