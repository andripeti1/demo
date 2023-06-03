package com.finalexample.demo.model.response;

import lombok.Data;

import java.util.List;

@Data
public class AllCarsResponse {

    private List<CarListingResponse> listingResponses;

    private int currentPage;

    private int totalPage;
}
