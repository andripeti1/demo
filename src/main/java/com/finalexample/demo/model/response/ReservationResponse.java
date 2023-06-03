package com.finalexample.demo.model.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReservationResponse {

    private Long reservationId;

    private Long listingId;

    private String startDate;

    private String endDate;

    private BigDecimal totalPrice;

    private String imagePath;

    private String title;

    private String brand;

    private String status;


}
