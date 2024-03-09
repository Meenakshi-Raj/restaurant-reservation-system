package com.meen.reservation.service;

import com.meen.reservation.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    UserRepository userRepository;

    public RestaurantServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean checkReservationStatus(String name) {
        boolean status = false;
        if(userRepository.findByName(name)!=null){
            status = true;
        }
        return status;
    }
}
