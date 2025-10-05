package com.nmscinemas.ticket_booking_service.dto;

public class BookingResponse {

    private Long bookingId;
    private Long movieId;
    private String movieName;
    private Long auditoriumId;
    private String auditoriumName;
    private Integer noOfTickets;
    private String showTime;
    private double totalPrice;
    private String status;
    private String seats;

    public BookingResponse() {
    }

    public BookingResponse(Long movieId, Long auditoriumId, Integer noOfTickets, String showTime, double totalPrice, String status, String seats) {
        this.movieId = movieId;
        this.auditoriumId = auditoriumId;
        this.noOfTickets = noOfTickets;
        this.showTime = showTime;
        this.totalPrice = totalPrice;
        this.status = status;
        this.seats = seats;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getMovieId() { return movieId;}

    public void setMovieId(Long id) {this.movieId = id;}

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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
