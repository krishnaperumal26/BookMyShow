package com.myproject.bookmyshow.repositories;

import com.myproject.bookmyshow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRespository extends JpaRepository<Booking, Long> {

}
