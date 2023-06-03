package com.finalexample.demo.model.response;


import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class MyReservationsResponse {

    private List<ReservationResponse> myReservations;

    private int currentPage;

    private int totalPage;
}
