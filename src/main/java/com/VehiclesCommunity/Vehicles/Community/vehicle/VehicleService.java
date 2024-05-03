package com.VehiclesCommunity.Vehicles.Community.vehicle;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicleRepository.findAll());
    }

    public Optional<Vehicle> getVehicle(Integer id) {
        return vehicleRepository.findById(id);
    }

    public void addNewVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public boolean deleteCar(Integer id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isEmpty()) {
            return false;
        }
        vehicleRepository.deleteById(id);
        return true;
    }

    public List<Optional<Vehicle>> compareVehicles(Integer car1Id, Integer car2Id) {
        Optional<Vehicle> car1 = vehicleRepository.findById(car1Id);
        Optional<Vehicle> car2 = vehicleRepository.findById(car2Id);
        return List.of(car1, car2);
    }
}
