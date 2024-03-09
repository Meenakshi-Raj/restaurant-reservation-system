package com.meen.reservation.controller;

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
    public String bookTicket(Model theModel){
        return "reserve-seat";
    }
    @GetMapping("/confirmSeat")
    public String confirmSeat(Model theModel){
        return "confirm-seat";
    }

    @GetMapping("/checkConfirmation")
    public String checkBookingStatus(@RequestParam("userName") String name, Model model){
        boolean status = restaurantServiceImpl.checkReservationStatus(name);
        model.addAttribute("ticketStatus",status);
        return "confirmation-status";
    }
}
