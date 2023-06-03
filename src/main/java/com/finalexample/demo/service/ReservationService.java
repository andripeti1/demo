package com.finalexample.demo.service;

import com.finalexample.demo.model.entity.Reservation;
import com.finalexample.demo.model.request.AddReservationRequest;
import com.finalexample.demo.model.response.CarListingResponse;
import com.finalexample.demo.model.response.MyReservationsResponse;
import com.finalexample.demo.model.response.ReservationResponse;
import com.finalexample.demo.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReservationService {


    private final CarListingService carListingService;

    private final ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public MyReservationsResponse getReservationsByUsername(String username, Pageable pageable){
        Page<Reservation> reservationPage= reservationRepository.findAllByUsername(username,pageable);

        List<ReservationResponse> reservationResponses = reservationPage.get()
                .map(this::buildReservationResponse)
                .collect(Collectors.toList());

        int currentPage = pageable.getPageNumber();
        int totalPage = reservationPage.getTotalPages();

        MyReservationsResponse myReservationsResponse = new MyReservationsResponse();
        myReservationsResponse.setMyReservations(reservationResponses);
        myReservationsResponse.setTotalPage(totalPage);
        myReservationsResponse.setCurrentPage(currentPage);

        return myReservationsResponse;

    }

    private ReservationResponse buildReservationResponse(Reservation reservation) {
        ReservationResponse reservationResponse = new ReservationResponse();

        reservationResponse.setReservationId(reservation.getId());
        reservationResponse.setStartDate(getStringFromDate(reservation.getStartDate()));
        reservationResponse.setEndDate(getStringFromDate(reservation.getEndDate()));
        reservationResponse.setTotalPrice(reservation.getTotalPrice());
        reservationResponse.setListingId(reservation.getListingId());

        CarListingResponse carListing = carListingService.retrieveById(reservation.getListingId());

        reservationResponse.setTitle(carListing.getTitle());
        reservationResponse.setBrand(carListing.getBrand());
        reservationResponse.setImagePath(carListing.getImagePath());
        reservationResponse.setStatus(getReservationStatus(reservation));
        return reservationResponse;
    }

    private String getReservationStatus(Reservation reservation) {
        LocalDate startDate = reservation.getStartDate();
        LocalDate endDate = reservation.getEndDate();

        if (startDate.isAfter(LocalDate.now())) {
            return "NOT YET CONSUMED";
        }

        if (endDate.isBefore(LocalDate.now())) {
            return "CONSUMED";
        }

        return "ACTIVE";
    }

    private String getStringFromDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    public boolean addReservation(AddReservationRequest addReservationRequest){

        LocalDate startDate= getDateFromString(addReservationRequest.getStartDate());
        LocalDate endDate = getDateFromString(addReservationRequest.getEndDate());

        boolean isOverlapping = isOverlapReservation(startDate,endDate,addReservationRequest.getListingId());

        if (isOverlapping){
            return false;
        }

        Reservation reservation=new Reservation();
        reservation.setUsername(addReservationRequest.getUsername());
        reservation.setListingId(addReservationRequest.getListingId());


        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

        //Gets carListing from database to calculate totalPrice
        CarListingResponse carListingResponse =
        carListingService.retrieveCarListingById(addReservationRequest.getListingId());

        BigDecimal pricePerDay= carListingResponse.getPrice();
        long dateDifference =findDateDifference(startDate,endDate);

        BigDecimal dateDifferenceB = new BigDecimal(dateDifference);

        BigDecimal totalPrice= pricePerDay.multiply(dateDifferenceB);

        reservation.setTotalPrice(totalPrice);

        reservationRepository.save(reservation);

        return true;
    }

    private boolean isOverlapReservation(LocalDate startDateB,LocalDate endDateB, Long listingId){

        return reservationRepository
                .findOverlappingReservation(listingId,startDateB,endDateB).isPresent();

    }

    private long findDateDifference(LocalDate d2,LocalDate d1){
        return ChronoUnit.DAYS.between(d2,d1)+1;
    }

    private LocalDate getDateFromString(String date){
        return LocalDate.parse(date);
    }
}
