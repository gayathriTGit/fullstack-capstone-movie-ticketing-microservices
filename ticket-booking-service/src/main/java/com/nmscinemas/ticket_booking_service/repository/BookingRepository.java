package com.nmscinemas.ticket_booking_service.repository;

import com.nmscinemas.ticket_booking_service.dto.BookingResponse;
import java.util.List;

public interface BookingRepository {

    List<BookingResponse> findAll();
    BookingResponse findById(Long id);
    BookingResponse add(BookingResponse booking);
    BookingResponse update(BookingResponse booking);
    void deleteById(Long id);

}
