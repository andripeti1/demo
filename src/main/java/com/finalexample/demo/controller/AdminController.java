package com.finalexample.demo.controller;

import com.finalexample.demo.model.entity.Reservation;
import com.finalexample.demo.model.request.RegisterCarRequest;
import com.finalexample.demo.service.CarListingService;
import com.finalexample.demo.service.RegisterCarService;
import com.finalexample.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController extends BaseController{

    private final RegisterCarService registerCarService;

    private final CarListingService carListingService;

    private final ReservationService reservationService;

    @GetMapping("")
    public String getAdminPanelView(Model model){
        model.addAttribute("carListings",carListingService.retrieveAllListings());
        return ok("admin-panel",model);
    }

    @GetMapping("/register-car")
    public String getRegisterCarView(Model model){
        model.addAttribute("registerCarRequest",new RegisterCarRequest());
        return ok("register-car",model);
    }

    @PostMapping("/register-car")
    public String registerCar(RegisterCarRequest registerCarRequest, Model model) throws IOException {
        registerCarService.registerCar(registerCarRequest);
        return ok("success-car",model);
    }

    @GetMapping("/delete-car/{id}")
    public String deleteCarListing(@PathVariable("id") Long id, HttpServletResponse response) {
        carListingService.deleteCarListing(id);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        return "redirect:/admin";
    }

    @GetMapping("/reservations")
    public String getAllReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return ok("reservations", model);
    }

}
