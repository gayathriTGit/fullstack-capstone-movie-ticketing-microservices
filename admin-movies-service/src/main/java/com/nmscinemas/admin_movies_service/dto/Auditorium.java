package com.nmscinemas.admin_movies_service.dto;

public class Auditorium {

    private Long id;
    private String name;
    private String features;

    public Auditorium() {
    }

    public Auditorium(Long id, String name, String features) {
        this.id = id;
        this.name = name;
        this.features = features;
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

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}
