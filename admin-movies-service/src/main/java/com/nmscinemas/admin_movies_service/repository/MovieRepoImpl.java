package com.nmscinemas.admin_movies_service.repository;

import com.nmscinemas.admin_movies_service.dto.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MovieRepoImpl implements MovieRepo {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    private static final RowMapper<Movie> MOVIE_ROW_MAPPER = (rs, rowNum) -> {

        Movie movie = new Movie();
        movie.setId(rs.getLong("id"));
        movie.setName(rs.getString("name"));
        movie.setLength(rs.getString("length"));
        movie.setRating(rs.getString("rating"));
        movie.setGenre(rs.getString("genre"));
        movie.setDescription(rs.getString("description"));
        movie.setImage(rs.getString("image"));
        movie.setReleaseDate(rs.getString("releaseDate"));

        return movie;

    };

    @Override
    public List<Movie> findAll(){
        String sql = "SELECT id, name, length, rating, genre, description, image, releaseDate FROM Movies";
        return jdbcTemplate.query(sql, MOVIE_ROW_MAPPER);
    }

    @Override
    public Movie findById(Long id){
        String sql = "SELECT id, name, length, rating, genre, description, image, releaseDate FROM Movies WHERE id = ?";
        List<Movie> movies = jdbcTemplate.query(sql, MOVIE_ROW_MAPPER, id);
        return  movies.isEmpty() ? null : movies.get(0);
    }

    @Override
    public Movie add(Movie newMovie){
        String sql = "INSERT INTO Movies(name, length, rating, genre, description, image, releaseDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newMovie.getName());
            ps.setString(2, newMovie.getLength());
            ps.setString(3, newMovie.getRating());
            ps.setString(4, newMovie.getGenre());
            ps.setString(5, newMovie.getDescription());
            ps.setString(6, newMovie.getImage());
            ps.setString(7, newMovie.getReleaseDate());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            System.out.println(keyHolder.getKey().longValue());
            newMovie.setId(keyHolder.getKey().longValue());
            return findById(newMovie.getId());
        }

        return newMovie;
    }

    @Override
    public Movie update(Movie movie){
        String sql = "UPDATE Movies SET name = ?, length = ?, rating = ?, genre = ?, description = ?, " +
                     "      image = ?, releaseDate = ? WHERE id = ?";
        jdbcTemplate.update(sql, movie.getName(), movie.getLength(), movie.getRating(), movie.getGenre(),
                            movie.getDescription(), movie.getImage(), movie.getReleaseDate(), movie.getId());
        return movie;
    }

    @Override
    public void deleteById(Long id){
        String sql = "DELETE FROM Movies WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

}
