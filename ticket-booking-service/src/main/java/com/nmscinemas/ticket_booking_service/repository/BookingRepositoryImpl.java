package com.nmscinemas.ticket_booking_service.repository;

import com.nmscinemas.ticket_booking_service.dto.BookingResponse;

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
public class BookingRepositoryImpl implements BookingRepository{

    @Autowired
    public JdbcTemplate jdbcTemplate;

    private static final RowMapper<BookingResponse> STUDENT_ROW_MAPPER = (rs, rowNum) -> {

        BookingResponse booking= new BookingResponse();

        booking.setBookingId(rs.getLong("id"));
        booking.setMovieId(rs.getLong("movieid"));
        booking.setMovieName(rs.getString("movieName"));
        booking.setAuditoriumId(rs.getLong("auditoriumId"));
        booking.setAuditoriumName(rs.getString("AuditoriumName"));
        booking.setNoOfTickets(rs.getInt("noOfTickets"));
        booking.setShowTime(rs.getString("showTime"));
        booking.setTotalPrice(rs.getDouble("totalCost"));
        booking.setStatus(rs.getString("status"));
        booking.setSeats(rs.getString("seats"));

        return booking;
    };

    @Override
    public List<BookingResponse> findAll() {
        String sql = "SELECT b.id, movieid, a.name AS movieName, auditoriumId, c.name AS AuditoriumName, noOfTickets, " +
                     "       showTime, totalCost, status, seats FROM (Bookings b INNER JOIN Movies a ON b.movieid = a.id)" +
                     " INNER JOIN Auditoriums c ON b.auditoriumId = c.id ORDER BY b.id";
        return jdbcTemplate.query(sql, STUDENT_ROW_MAPPER);
    }

    @Override
    public BookingResponse findById(Long id) {
        String sql = "SELECT b.id, movieid, a.name AS movieName, auditoriumId, c.name AS AuditoriumName, noOfTickets, " +
                "       showTime, totalCost, status, seats FROM (Bookings b INNER JOIN Movies a ON b.movieid = a.id)" +
                " INNER JOIN Auditoriums c ON b.auditoriumId = c.id WHERE b.id = ?";
        List<BookingResponse> bookings = jdbcTemplate.query(sql, STUDENT_ROW_MAPPER, id);
        return  bookings.isEmpty() ? null : bookings.get(0);
    }

    @Override
    public BookingResponse add(BookingResponse booking){

        String sql = "INSERT INTO Bookings(movieid, auditoriumId, noOfTickets, showTime, totalCost, status, seats) " +
                     " VALUES(?, ?, ?, ?, ?, ?, ? )";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, booking.getMovieId());
            ps.setLong(2, booking.getAuditoriumId());
            ps.setInt(3, booking.getNoOfTickets());
            ps.setString(4, booking.getShowTime());
            ps.setDouble(5, booking.getTotalPrice());
            ps.setString(6, booking.getStatus());
            ps.setString(7, booking.getSeats());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            booking.setBookingId(keyHolder.getKey().longValue());
            return findById(booking.getBookingId());
        }

        return booking;
    }

    @Override
    public BookingResponse update(BookingResponse booking){
        String sql = "UPDATE Bookings SET movieid=?, auditoriumId=?, noOfTickets=?, showTime=?, totalCost=?, status=?, seats=? WHERE id=?";
        jdbcTemplate.update(sql, booking.getMovieId(), booking.getAuditoriumId(), booking.getNoOfTickets(), booking.getShowTime(),
                        booking.getTotalPrice(), booking.getStatus(), booking.getSeats(), booking.getBookingId());
        return booking;
    }

    @Override
    public void deleteById(Long id){
        String sql = "DELETE FROM Bookings WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

}
