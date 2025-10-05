package com.nmscinemas.admin_movies_service.dto;

public class MovieShowTime {

    private Long id;
    private Long movieId;
    private String movieName;
    private String time;
    private Integer availableSeats;
    private Double price;
    private String date;
    private Long auditoriumId;
    private String auditoriumName;
    private String seats;

    public MovieShowTime() {
    }

    public MovieShowTime(Long id, Long movieId, String time, Integer availableSeats, Double price,
                         String date, Long auditoriumId, String seats) {
        this.id = id;
        this.movieId = movieId;
        this.time = time;
        this.availableSeats = availableSeats;
        this.price = price;
        this.date = date;
        this.auditoriumId = auditoriumId;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
