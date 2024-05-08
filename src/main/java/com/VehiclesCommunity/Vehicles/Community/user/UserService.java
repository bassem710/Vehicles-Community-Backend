package com.VehiclesCommunity.Vehicles.Community.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewAdmin(User user) {
        userRepository.save(user);
    }
}
