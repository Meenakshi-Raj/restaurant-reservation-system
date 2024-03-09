package com.meen.reservation.controller;

import com.meen.reservation.entity.User;
import com.meen.reservation.entity.UserDTO;
import com.meen.reservation.service.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RestaurantReservationController {
    @Autowired
    RestaurantServiceImpl restaurantServiceImpl;

    public RestaurantReservationController(RestaurantServiceImpl restaurantServiceImpl) {
        this.restaurantServiceImpl = restaurantServiceImpl;
    }

    @GetMapping("/home")
    public String showHome(Model theModel){
        return "home-page";
    }

    @PostMapping("/reservation")
    public String handleAction(@RequestParam("action") String action){
        if(action.equals("reserve")){
            return "redirect:/reserveSeat";
        }else if(action.equals("confirm")){
            return "redirect:/confirmSeat";
        }else{
            return "error";
        }
    }
    @GetMapping("/reserveSeat")
    public String reserveSeat(){
        return "reserve-seat";
    }
    @GetMapping("reserveUser")
    public String reserveUserSeat(UserDTO userDTO, Model model){
        User user = restaurantServiceImpl.reserve(userDTO);
        model.addAttribute("bookingId",user.getBookingId());
        return "reserved";
    }


    @GetMapping("/confirmSeat")
    public String confirmSeat(Model theModel){
        return "confirm-seat";
    }

    @GetMapping("/checkConfirmation")
    public String checkBookingStatus(@RequestParam("userName") String name, Model model){
        String message;
        User user = restaurantServiceImpl.checkReservationStatus(name);
        if(user != null){
            message = "Reservation Confirmed with Booking ID: " + user.getBookingId();
        }else{
            message = "No Reservation under given name!";
        }
        model.addAttribute("message",message);
        return "confirmation-status";
    }
}
