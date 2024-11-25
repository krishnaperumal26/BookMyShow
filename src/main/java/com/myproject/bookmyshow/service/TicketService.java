package com.myproject.bookmyshow.service;

import com.myproject.bookmyshow.exceptions.InvalidArgumentsExceptions;
import com.myproject.bookmyshow.exceptions.SeatNotAvailableException;
import com.myproject.bookmyshow.models.*;
import com.myproject.bookmyshow.repositories.SeatRepository;
import com.myproject.bookmyshow.repositories.ShowRepository;
import com.myproject.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;

    @Autowired
    TicketService(SeatRepository seatRepository,
                  ShowSeatRepository showSeatRepository,
                  ShowRepository showRepository) {

        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
    }

    public Ticket bookTicket(List<Long> seatId, Long showId, Long userId) throws InvalidArgumentsExceptions, SeatNotAvailableException {
        //1. for these seatIds, get corresponding showSeats - getSeatsForIds(ids)
        //2. Check the status of all showSeats. - getShowSeatsForSeats(seats)
        //2.a. Every seat is available
            //Lock everyseat (Set the status to be locked)
            //Create Ticket object and return
        //2.b. Some of the seats are not available
            //throw Exception


        //1. for these seatIds, get corresponding showSeats - getSeatsForIds(ids)
        List<Seat> seats = seatRepository.findAllByIdIn(seatId);

        //Get show
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty())
        {
            throw new InvalidArgumentsExceptions("Show by :"+showId+" doesn't exist");
        }
        Show show = showOptional.get();
        //2. Check the status of all showSeats. - getShowSeatsForSeats(seats)
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow(seats, show);

        //2.a. Every seat is available
        for(ShowSeat showSeat : showSeats )
        {
            //2.b. Some of the seats are not available
            //throw Exception
            if(!showSeat.getSeatStatus().equals(ShowSeatStatus.AVAILABLE))
            {
                throw new SeatNotAvailableException();
            }

            //2.a. Every seat is available

        }


        return null;
    }
}
