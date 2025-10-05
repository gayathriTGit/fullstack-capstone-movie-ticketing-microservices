package com.nmscinemas.admin_movies_service.repository;

import com.nmscinemas.admin_movies_service.dto.MovieShowTime;

import java.util.List;

public interface MovieShowTimeRepo {
    List<MovieShowTime> findAll();
    MovieShowTime findById(Long id);
    List<MovieShowTime> findByAuditoriumId(Long theaterId);
    List<MovieShowTime> findByAuditoriumAndMovieId(Long theaterId, Long movieId);
    List<MovieShowTime> findByMovieId(Long movieId);
   // List<String> findShowTimesByMovieId(Long movieId);
    MovieShowTime add(MovieShowTime booking);
    MovieShowTime update(MovieShowTime booking);
    void deleteById(Long id);
}
