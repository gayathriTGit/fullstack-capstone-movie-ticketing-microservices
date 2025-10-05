package com.nmscinemas.ticket_booking_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.DefaultValue;

public class BookingPriceCalcRequest {

    private String movieName;
    private Integer noOfTickets;
    private String showTime;
    private Integer noOfDrinks;
    private Integer noOfPopcorns;
    private Integer noOfIceCream;
    private Integer noOfCandy;
    private Double baseTicketPrice;

    public BookingPriceCalcRequest() {
    }

    public BookingPriceCalcRequest(String movieName, Integer noOfTickets, String showTime, Integer noOfDrinks, Integer noOfPopcorns, Integer noOfIceCream, Integer noOfCandy, Double baseTicketPrice) {
        this.movieName = movieName;
        this.noOfTickets = noOfTickets;
        this.showTime = showTime;
        this.noOfDrinks = noOfDrinks;
        this.noOfPopcorns = noOfPopcorns;
        this.noOfIceCream = noOfIceCream;
        this.noOfCandy = noOfCandy;
        this.baseTicketPrice = baseTicketPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(Integer noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Integer getNoOfDrinks() {
        return noOfDrinks;
    }

    public void setNoOfDrinks(Integer noOfDrinks) {
        this.noOfDrinks = noOfDrinks;
    }

    public Integer getNoOfPopcorns() {
        return noOfPopcorns;
    }

    public void setNoOfPopcorns(Integer noOfPopcorns) {
        this.noOfPopcorns = noOfPopcorns;
    }

    public Integer getNoOfIceCream() {
        return noOfIceCream;
    }

    public void setNoOfIceCream(Integer noOfIceCream) {
        this.noOfIceCream = noOfIceCream;
    }

    public Integer getNoOfCandy() {
        return noOfCandy;
    }

    public void setNoOfCandy(Integer noOfCandy) {
        this.noOfCandy = noOfCandy;
    }

    public Double getBaseTicketPrice() {
        return baseTicketPrice;
    }

    public void setBaseTicketPrice(Double baseTicketPrice) {
        this.baseTicketPrice = baseTicketPrice;
    }
}
