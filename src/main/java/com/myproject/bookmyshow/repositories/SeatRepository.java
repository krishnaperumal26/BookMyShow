package com.myproject.bookmyshow.repositories;

import com.myproject.bookmyshow.models.Seat;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByIdIn(List<Long> seatIds);

    //Below method findAllByIdAndAndId is sample
    //List<Seat> findAllByIdAndAndId(Long id, int col); //Spring JPA automatically convert the Method name into Query and get data from data
    //Behind- Automatically JPA create Query : Select * from seats where id = {} and Col = {};
}
