package com.meen.reservation.service;

import com.meen.reservation.entity.User;
import com.meen.reservation.entity.UserDTO;

public interface RestaurantService {
    User checkReservationStatus(String name);

    User reserve(UserDTO userDTO);
}
