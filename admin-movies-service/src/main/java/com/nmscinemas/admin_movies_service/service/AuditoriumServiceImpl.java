package com.nmscinemas.admin_movies_service.service;

import com.nmscinemas.admin_movies_service.dto.Auditorium;
import com.nmscinemas.admin_movies_service.repository.AuditoriumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuditoriumServiceImpl implements AuditoriumService{

    @Autowired
    private AuditoriumRepo theaterRepo;

    @Override
    public List<Auditorium> getAllAuditoriums() {
        return theaterRepo.findAll();
    }

    @Override
    public Auditorium getAuditoriumById(Long id) { return theaterRepo.findById(id);}

    @Override
    public Auditorium createAuditorium(Auditorium theater){ return theaterRepo.add(theater); }

    @Override
    public Auditorium updateAuditorium(Auditorium theater){ return theaterRepo.update(theater);}

    @Override
    public void deleteAuditorium(Long id) {theaterRepo.deleteById(id);}

}
