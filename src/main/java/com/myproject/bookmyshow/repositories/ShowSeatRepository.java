package com.myproject.bookmyshow.repositories;

import com.myproject.bookmyshow.models.Seat;
import com.myproject.bookmyshow.models.Show;
import com.myproject.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long>
{
    //("select '*' from show_seats where Id IN (C10,C11,C12)")
    List<ShowSeat> findAllBySeatInAndShow(List<Long> showsSeatId);

    ShowSeat save(ShowSeat showSeat);
}
