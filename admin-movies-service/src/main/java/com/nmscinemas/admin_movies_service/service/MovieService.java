package com.nmscinemas.admin_movies_service.service;

import com.nmscinemas.admin_movies_service.dto.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie createMovie(Movie movie);
    Movie updateMovie(Movie movie);
    void deleteMovie(Long id);
}
