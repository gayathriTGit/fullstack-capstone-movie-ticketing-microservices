package com.nmscinemas.admin_movies_service.repository;

import com.nmscinemas.admin_movies_service.dto.Auditorium;
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
public class AuditoriumRepoImpl implements AuditoriumRepo{

    @Autowired
    public JdbcTemplate jdbcTemplate;

    private static final RowMapper<Auditorium> AUDITORIUM_ROW_MAPPER = (rs, rowNum) -> {

        Auditorium theater = new Auditorium();
        theater.setId(rs.getLong("id"));
        theater.setName(rs.getString("name"));
        theater.setFeatures(rs.getString("features"));

        return theater;
    };

    @Override
    public List<Auditorium> findAll(){
        String sql = "SELECT id, name, features FROM Auditoriums";
        return jdbcTemplate.query(sql, AUDITORIUM_ROW_MAPPER);
    }

    @Override
    public Auditorium findById(Long id){
        String sql = "SELECT id, name, features FROM Auditoriums WHERE id = ?";
        List<Auditorium> theaters = jdbcTemplate.query(sql, AUDITORIUM_ROW_MAPPER, id);
        return  theaters.isEmpty() ? null : theaters.get(0);
    }

    @Override
    public Auditorium add(Auditorium newTheater){
        String sql = "INSERT INTO Auditoriums(name, features) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newTheater.getName());
            ps.setString(2, newTheater.getFeatures());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            System.out.println(keyHolder.getKey().longValue());
            newTheater.setId(keyHolder.getKey().longValue());
            return findById(newTheater.getId());
        }

        return newTheater;
    }

    @Override
    public Auditorium update(Auditorium theater){
        String sql = "UPDATE Auditoriums SET name = ?, features = ? WHERE id = ?";
        jdbcTemplate.update(sql, theater.getName(), theater.getFeatures(),  theater.getId());
        return theater;
    }

    @Override
    public void deleteById(Long id){
        String sql = "DELETE FROM Auditoriums WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}
