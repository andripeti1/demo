package com.finalexample.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListingResponse {

    private long id;

    private String title;

    private String description;

    private double power;

    private int year;

    private BigDecimal price;

    private String fuelType;

    private String brand;

    private String model;

    private String imagePath;

}
