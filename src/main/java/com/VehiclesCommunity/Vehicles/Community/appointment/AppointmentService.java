package com.VehiclesCommunity.Vehicles.Community.appointment;

import com.VehiclesCommunity.Vehicles.Community.config.JwtService;
import com.VehiclesCommunity.Vehicles.Community.user.User;
import com.VehiclesCommunity.Vehicles.Community.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, JwtService jwtService) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointmentRepository.findAll());
    }

    public List<Appointment> getMyAppointments(Integer id) {
        return new ArrayList<>(appointmentRepository.findByUserId(id));
    }

    public void addNewAppointment(Integer userId, Date date) {
        Appointment appointment = new Appointment();
        appointment.setUser(new User(userId));
        appointment.setDate(date);
        User user = appointment.getUser();
        if (user != null && user.getId() == null) {
            user = userRepository.save(user);
            appointment.setUser(user);
        }
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
