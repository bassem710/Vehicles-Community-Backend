package com.VehiclesCommunity.Vehicles.Community.appointment;

import com.VehiclesCommunity.Vehicles.Community.user.User;
import com.VehiclesCommunity.Vehicles.Community.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointmentRepository.findAllWithUsers());
    }

    public void addNewAppointment(Appointment appointment) {
        // Check if the user associated with the appointment exists
        User user = appointment.getUser();
        if (user != null && user.getId() == null) {
            // Save the user first if it doesn't exist in the database
            user = userRepository.save(user);
            // Set the saved user back to the appointment
            appointment.setUser(user);
        }
        // Save the appointment
        appointmentRepository.save(appointment);
    }

    public boolean deleteAppointment(Integer id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
