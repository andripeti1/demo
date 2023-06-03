package com.finalexample.demo.model.entity;

import lombok.Data;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "RESERVATIONS")
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "ID")
    private Long id;

    @Column(name="START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name= "USERNAME")
    private String username;

    @Column(name = "LISTING_ID")
    private Long listingId;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;



}
