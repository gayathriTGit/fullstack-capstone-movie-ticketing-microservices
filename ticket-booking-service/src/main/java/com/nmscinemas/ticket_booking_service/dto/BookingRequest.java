package com.nmscinemas.ticket_booking_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.DefaultValue;

public class BookingRequest {

    @NotNull
    private Long movieId;

    @NotBlank(message = "Movie name is required")
    private String movieName;

    @NotNull
    private Long auditoriumId;

    @NotBlank(message = "Auditorium name is required")
    private String auditoriumName;

    @NotNull
    @DefaultValue(value="1")
    private Integer noOfTickets;

    @NotBlank(message = "Movie show time is required")
    @DefaultValue(value="10:30am")
    private String showTime;

    @NotNull
    @DefaultValue(value="0")
    private Integer noOfDrinks;

    @NotNull
    @DefaultValue(value="0")
    private Integer noOfPopcorns;

    @NotNull
    @DefaultValue(value="0")
    private Integer noOfIceCream;

    @NotNull
    @DefaultValue(value="0")
    private Integer noOfCandy;

    @Min(1)
    private Double baseTicketPrice;

    @NotNull
    private String seats;

    public BookingRequest() {
    }

    public BookingRequest(Long movieId, Long auditoriumId, String movieName, Integer noOfTickets, String showTime,
                          Integer noOfDrinks, Integer noOfPopcorns, Integer noOfIceCream,
                          Integer noOfCandy, Double baseTicketPrice, String seats) {
        this.movieId = movieId;
        this.auditoriumId = auditoriumId;
        this.movieName = movieName;
        this.noOfTickets = noOfTickets;
        this.showTime = showTime;
        this.noOfDrinks = noOfDrinks;
        this.noOfPopcorns = noOfPopcorns;
        this.noOfIceCream = noOfIceCream;
        this.noOfCandy = noOfCandy;
        this.baseTicketPrice = baseTicketPrice;
        this.seats = seats;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long id){
        this.movieId = id;
    }

    public Long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(Long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
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

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

}
