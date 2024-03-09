package com.meen.reservation.service;

import com.meen.reservation.entity.User;
import com.meen.reservation.entity.UserDTO;
import com.meen.reservation.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    UserRepository userRepository;

    public RestaurantServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User checkReservationStatus(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User reserve(UserDTO userDTO) {
        User user = new User();
        UUID uuid = UUID.randomUUID();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBookingId(uuid.toString());
        return userRepository.save(user);
    }
}
