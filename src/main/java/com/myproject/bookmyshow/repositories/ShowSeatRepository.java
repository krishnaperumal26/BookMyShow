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
    //@Query("select '*' from showz where") can write custom query for this method
    List<ShowSeat> findAllBySeatInAndShow(List<Seat> seats, Show show);

    ShowSeat save(ShowSeat showSeat); //After save, it going to return updated ShowSeat type. So it accept ShowSeat param and return the updated ShowSeat
    //save() - method used for both Create and Update. If the data id already present in DB, then it is update, else it is create.


}
