package com.nmscinemas.admin_movies_service.repository;

import com.nmscinemas.admin_movies_service.dto.Auditorium;

import java.util.List;

public interface AuditoriumRepo {
    List<Auditorium> findAll();
    Auditorium findById(Long id);
    Auditorium add(Auditorium theater);
    Auditorium update(Auditorium theater);
    void deleteById(Long id);
}
