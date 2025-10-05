package com.nmscinemas.admin_movies_service.service;

import com.nmscinemas.admin_movies_service.dto.Auditorium;

import java.util.List;

public interface AuditoriumService {
    List<Auditorium> getAllAuditoriums();
    Auditorium getAuditoriumById(Long id);
    Auditorium createAuditorium(Auditorium theater);
    Auditorium updateAuditorium(Auditorium theater);
    void deleteAuditorium(Long id);

}
