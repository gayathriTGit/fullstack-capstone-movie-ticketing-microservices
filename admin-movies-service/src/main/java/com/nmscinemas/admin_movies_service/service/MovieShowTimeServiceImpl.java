package com.nmscinemas.admin_movies_service.service;

import com.nmscinemas.admin_movies_service.dto.MovieShowTime;
import com.nmscinemas.admin_movies_service.repository.MovieShowTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieShowTimeServiceImpl implements MovieShowTimeService{

    @Autowired
    private MovieShowTimeRepo showTimeRepo;

    @Override
    public List<MovieShowTime> getAllMovieShowTimes() {
        return showTimeRepo.findAll();
    }

    @Override
    public MovieShowTime getMovieShowTimeById(Long id) {
        return showTimeRepo.findById(id);
    }

    @Override
    public List<MovieShowTime> getShowTimeByMovieId(Long id) {
        return showTimeRepo.findByMovieId(id);
    }

//    @Override
//    public List<String> getShowTimesByMovieId(Long movieId){
//        return showTimeRepo.findShowTimesByMovieId(movieId);
//    }

    @Override
    public List<MovieShowTime> getShowTimesByAuditoriumId(Long theaterId) {
        return showTimeRepo.findByAuditoriumId(theaterId);
    }

    @Override
    public List<MovieShowTime> getShowTimesByAuditoriumAndMovieId(Long theaterId, Long movieId) {
        return showTimeRepo.findByAuditoriumAndMovieId(theaterId, movieId);
    }

    @Override
    public MovieShowTime createMovieShowTime(MovieShowTime showTime) {
        return showTimeRepo.add(showTime);
    }

    @Override
    public MovieShowTime updateMovieShowTime(MovieShowTime showTime) {
        return showTimeRepo.update(showTime);
    }

    @Override
    public void deleteMovieShowTime(Long id) {
        showTimeRepo.deleteById(id);
    }

}
