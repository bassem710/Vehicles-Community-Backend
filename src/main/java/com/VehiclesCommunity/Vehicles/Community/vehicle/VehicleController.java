package com.VehiclesCommunity.Vehicles.Community.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService, VehicleRepository vehicleRepository) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, List<Vehicle>>> getVehicles() {
        return ResponseEntity.ok(Map.of("data", vehicleService.getVehicles()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Optional<Vehicle>>> getVehicle(@PathVariable Integer id) {
        return ResponseEntity.ok(Map.of("data", vehicleService.getVehicle(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addNewVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addNewVehicle(vehicle);
        return ResponseEntity.ok(Map.of("message", "Vehicle added successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteVehicle(@PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();
        boolean exists = vehicleService.deleteCar(id);
        if (!exists) {
            response.put("message", "Vehicle not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.put("message", "Vehicle deleted successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/compare/{car1Id}/{car2Id}")
    public ResponseEntity<Map<String, List<Optional<Vehicle>>>> compareVehicles(@PathVariable Integer car1Id, @PathVariable Integer car2Id) {
        List<Optional<Vehicle>> vehicles = vehicleService.compareVehicles(car1Id, car2Id);
        return ResponseEntity.ok(Map.of("car1", Collections.singletonList(vehicles.get(0)), "car2", Collections.singletonList(vehicles.get(1))));
    }
}
