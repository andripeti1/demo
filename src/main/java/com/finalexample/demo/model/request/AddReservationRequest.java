package com.finalexample.demo.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddReservationRequest {

    private String startDate;

    private String endDate;

    private long listingId;

    private String username;
}
