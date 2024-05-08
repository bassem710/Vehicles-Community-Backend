package com.VehiclesCommunity.Vehicles.Community.appointment;

import com.VehiclesCommunity.Vehicles.Community.user.Role;
import com.VehiclesCommunity.Vehicles.Community.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<Appointment>>> getMyAppointments(HttpServletRequest request) {
        User userDetails = (User) request.getAttribute("userDetails");
        if(userDetails.getRole() == Role.ADMIN) {
            return ResponseEntity.ok(Map.of("data", appointmentService.getAppointments()));
        }
        return ResponseEntity.ok(Map.of("data", appointmentService.getMyAppointments(userDetails.getId())));
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addNewAppointment(@RequestBody AppointmentRequestDto requestDto, HttpServletRequest request) {
        User userDetails = (User) request.getAttribute("userDetails");
        appointmentService.addNewAppointment(userDetails.getId(), requestDto.getDate());
        return ResponseEntity.ok(Map.of("message", "Appointment added successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteVehicle(@PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();
        boolean exists = appointmentService.deleteAppointment(id);
        if (!exists) {
            response.put("message", "Appointment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.put("message", "Appointment deleted successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
