package com.nmscinemas.admin_movies_service.service;

import com.nmscinemas.admin_movies_service.dto.MovieShowTime;

import java.util.List;

public interface MovieShowTimeService {

    List<MovieShowTime> getAllMovieShowTimes();
    List<MovieShowTime> getShowTimeByMovieId(Long id);
    MovieShowTime getMovieShowTimeById(Long id);
    //List<String> getShowTimesByMovieId(Long movieId);
    List<MovieShowTime> getShowTimesByAuditoriumId(Long theaterId);
    List<MovieShowTime> getShowTimesByAuditoriumAndMovieId(Long theaterId, Long movieId);
    MovieShowTime createMovieShowTime(MovieShowTime movie);
    MovieShowTime updateMovieShowTime(MovieShowTime movie);
    void deleteMovieShowTime(Long id);

}
