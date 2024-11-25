package com.myproject.bookmyshow.repositories;

import com.myproject.bookmyshow.models.Seat;
import com.myproject.bookmyshow.models.Show;
import com.myproject.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findAllBySeatInAndShow(List<Seat> seats, Show show);

}
