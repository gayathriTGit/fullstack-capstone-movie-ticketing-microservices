package com.nmscinemas.admin_movies_service.repository;

import com.nmscinemas.admin_movies_service.dto.Movie;

import java.util.List;

public interface MovieRepo {
    List<Movie> findAll();
    Movie findById(Long id);
    Movie add(Movie movie);
    Movie update(Long id, Movie movie);
    void deleteById(Long id);
}
