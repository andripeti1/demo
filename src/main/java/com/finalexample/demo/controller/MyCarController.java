package com.finalexample.demo.controller;

import com.finalexample.demo.model.request.AddReservationRequest;
import com.finalexample.demo.model.response.AllCarsResponse;
import com.finalexample.demo.model.response.CarListingResponse;
import com.finalexample.demo.service.CarListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class MyCarController extends BaseController {

    private final CarListingService carListingService;

    @GetMapping(value = "")
    public String getCarView(@PageableDefault(value = 0,size=5) Pageable pageable, Model model){
        AllCarsResponse allCarsResponse = carListingService.retrieveAllListings(pageable);

        model.addAttribute("allCarsResponse",allCarsResponse);
        return ok("cars",model);
    }

    @GetMapping(value = "/{id}")
    public String getCarDetailsView(@PathVariable Long id, Model model){
        CarListingResponse carListingResponse=carListingService.retrieveCarListingById(id);

        model.addAttribute("addReservationRequest",new AddReservationRequest());
        model.addAttribute("carListing",carListingResponse);

        return ok("listing-details",model);
    }


}
