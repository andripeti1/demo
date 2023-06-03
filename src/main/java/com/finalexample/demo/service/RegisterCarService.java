package com.finalexample.demo.service;

import com.finalexample.demo.model.entity.Car;
import com.finalexample.demo.model.entity.CarListing;
import com.finalexample.demo.model.request.RegisterCarRequest;
import com.finalexample.demo.repository.CarListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RegisterCarService {

    private final ImageService imageService;

    private final CarListingRepository carListingRepository;
    public void registerCar(RegisterCarRequest registerCarRequest) throws IOException {
        CarListing carListing=new CarListing();

        carListing.setTitle(registerCarRequest.getTitle());
        carListing.setDescription(registerCarRequest.getDescription());
        carListing.setPrice(registerCarRequest.getPrice());

        //Create car obj

        Car car =new Car();
        car.setBrand(registerCarRequest.getBrand());
        car.setFuelType(registerCarRequest.getFuelType());
        car.setModel(registerCarRequest.getModel());
        car.setYear(registerCarRequest.getYear());
        car.setPower(registerCarRequest.getPower());

        carListing.setCar(car);

        CarListing savedCarListing = carListingRepository.save(carListing);

        Long id= savedCarListing.getId();

        String imagePath=imageService.saveImage(registerCarRequest.getImage(),id);

        savedCarListing.setImageName(imagePath);

        carListingRepository.save(savedCarListing);

    }
}
