package com.nmscinemas.admin_movies_service.service;

import com.nmscinemas.admin_movies_service.dto.Movie;
import com.nmscinemas.admin_movies_service.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepo movieRepo;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepo.findById(id);
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepo.add(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepo.update(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepo.deleteById(id);
    }

}
