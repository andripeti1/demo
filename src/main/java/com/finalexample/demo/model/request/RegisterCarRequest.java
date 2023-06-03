package com.finalexample.demo.model.request;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@ToString
@Data
public class RegisterCarRequest {


    @NotBlank(message = "Title cannot be empty.")
    private String title;

    @NotBlank(message = "Brand cannot be empty.")
    private String brand;

    @NotBlank(message = "Model cannot be empty.")
    private String model;

    @Min(value = 2000, message = "Cars prior to 2000 are not good for renting.")
    private int year;

    @NotBlank(message = "Fuel type cannot be empty.")
    private String fuelType;

    @Min(value = 1, message = "Please input the power of your car.")
    private double power;

    @Min(value = 1, message = "Price cannot be zero.")
    private BigDecimal price;

    @NotBlank(message = "Description cannot be empty.")
    private String description;

    private MultipartFile image;

    }

