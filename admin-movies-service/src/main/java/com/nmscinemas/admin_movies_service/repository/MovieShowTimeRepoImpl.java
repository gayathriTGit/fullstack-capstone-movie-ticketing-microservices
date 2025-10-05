package com.nmscinemas.admin_movies_service.repository;

import com.nmscinemas.admin_movies_service.dto.MovieShowTime;
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
public class MovieShowTimeRepoImpl implements MovieShowTimeRepo{

    @Autowired
    public JdbcTemplate jdbcTemplate;

    private static final RowMapper<MovieShowTime> MOVIE_SHOW_TIME_ROW_MAPPER = (rs, rowNum) -> {

        MovieShowTime showTimes = new MovieShowTime();
        showTimes.setId(rs.getLong("id"));
        showTimes.setMovieId(rs.getLong("movieid"));
        showTimes.setMovieName(rs.getString("MovieName"));
        showTimes.setTime(rs.getString("time"));
        showTimes.setAvailableSeats(rs.getInt("availableSeats"));
        showTimes.setPrice(rs.getDouble("price"));
        showTimes.setDate(rs.getString("date"));
        showTimes.setAuditoriumId(rs.getLong("auditoriumId"));
        showTimes.setAuditoriumName(rs.getString("AuditoriumName"));
        showTimes.setSeats(rs.getString("seats"));

        return showTimes;
    };

    @Override
    public List<MovieShowTime> findAll(){
        String sql = "SELECT a.id, a.movieid, b.name AS MovieName, time, availableSeats, price, date, " +
                     "     auditoriumId, c.name AuditoriumName, a.seats FROM (MovieShowTimes a" +
                     "   INNER JOIN Movies b ON a.movieid = b.id) INNER JOIN Auditoriums c ON a.auditoriumId = c.id " +
                     "   WHERE a.id = ?";
        return jdbcTemplate.query(sql, MOVIE_SHOW_TIME_ROW_MAPPER);
    }

    @Override
    public MovieShowTime findById(Long id){
        String sql = "SELECT a.id, a.movieid, b.name AS MovieName, time, availableSeats, price, date, " +
                     "     auditoriumId, c.name AuditoriumName , a.seats FROM (MovieShowTimes a" +
                     "   INNER JOIN Movies b ON a.movieid = b.id) INNER JOIN Auditoriums c ON a.auditoriumId = c.id " +
                     "   WHERE a.id = ?";
        List<MovieShowTime> showtimes = jdbcTemplate.query(sql, MOVIE_SHOW_TIME_ROW_MAPPER, id);
        return  showtimes.isEmpty() ? null : showtimes.get(0);
    }

    @Override
    public List<MovieShowTime> findByMovieId(Long movieId){
        String sql = "SELECT a.id, a.movieid, b.name AS MovieName, time, availableSeats, price, date, " +
                     "     auditoriumId, c.name AuditoriumName , a.seats FROM (MovieShowTimes a" +
                     "   INNER JOIN Movies b ON a.movieid = b.id) INNER JOIN Auditoriums c ON a.auditoriumId = c.id " +
                     "   WHERE a.movieid = ?";
        return  jdbcTemplate.query(sql, MOVIE_SHOW_TIME_ROW_MAPPER, movieId);
    }

//    @Override
//    public List<String> findShowTimesByMovieId(Long movieId) {
//        String sql = "SELECT distinct time  FROM MovieShowTimes a " +
//                "    INNER JOIN Movies b ON a.movieid = b.id WHERE a.movieid = ?";
//        RowMapper<String> wrapper = (rs, rowNum) -> {
//            String returnValue = rs.getString("time");
//            return returnValue;
//        };
//        return jdbcTemplate.query(sql, wrapper, movieId);
//    }

    @Override
    public List<MovieShowTime> findByAuditoriumId(Long theaterId){
        String sql = "SELECT a.id, a.movieid, b.name AS MovieName, time, availableSeats, price, date, " +
                     "     auditoriumId, c.name AuditoriumName , a.seats FROM (MovieShowTimes a" +
                     "   INNER JOIN Movies b ON a.movieid = b.id) INNER JOIN Auditoriums c ON a.auditoriumId = c.id " +
                     "   WHERE a.auditoriumId = ?";
        return  jdbcTemplate.query(sql, MOVIE_SHOW_TIME_ROW_MAPPER, theaterId);
    }

    @Override
    public List<MovieShowTime> findByAuditoriumAndMovieId(Long theaterId, Long movieId){
        String sql = "SELECT a.id, a.movieid, b.name AS MovieName, time, availableSeats, price, date, " +
                     "     auditoriumId, c.name AuditoriumName, a.seats FROM (MovieShowTimes a" +
                     "   INNER JOIN Movies b ON a.movieid = b.id) INNER JOIN Auditoriums c ON a.auditoriumId = c.id " +
                     "   WHERE a.auditoriumId = ? AND a.movieid = ?";
        return  jdbcTemplate.query(sql, MOVIE_SHOW_TIME_ROW_MAPPER, theaterId, movieId);
    }

    @Override
    public MovieShowTime add(MovieShowTime showTime) {
        String sql = "INSERT INTO MovieShowTimes(movieid, time, availableSeats, price, date, auditoriumId, seats) " +
                     "     VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, showTime.getMovieId());
            ps.setString(2, showTime.getTime());
            ps.setInt(3, showTime.getAvailableSeats());
            ps.setDouble(4, showTime.getPrice());
            ps.setString(5, showTime.getDate());
            ps.setLong(6, showTime.getAuditoriumId());
            ps.setString(7, showTime.getSeats());

            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            System.out.println(keyHolder.getKey().longValue());
            showTime.setId(keyHolder.getKey().longValue());
            return findById(showTime.getId());
        }

        return showTime;
    }

    @Override
    public MovieShowTime update(MovieShowTime showTime){
        String sql = "UPDATE MovieShowTimes SET movieid = ?, time = ?, availableSeats = ?, price = ?, date = ?, " +
                     "  auditoriumId = ?, seats = ? WHERE id = ?";
        jdbcTemplate.update(sql, showTime.getMovieId(), showTime.getTime(), showTime.getAvailableSeats(), showTime.getPrice(),
                showTime.getDate(), showTime.getAuditoriumId(), showTime.getSeats(), showTime.getId());
        return showTime;
    }

    @Override
    public void deleteById(Long id){
        String sql = "DELETE FROM MovieShowTimes WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

}
