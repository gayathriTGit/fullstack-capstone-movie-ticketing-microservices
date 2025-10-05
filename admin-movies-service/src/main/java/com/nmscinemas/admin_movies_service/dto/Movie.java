package com.nmscinemas.admin_movies_service.dto;

public class Movie {

    private Long id;
    private String name;
    private String length;
    private String rating;
    private String genre;
    private String description;
    private String image;
    private String releaseDate;

    public Movie() {
    }

    public Movie(Long id, String name, String length, String rating,
                 String genre, String description, String image, String releaseDate) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.rating = rating;
        this.genre = genre;
        this.description = description;
        this.image = image;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
