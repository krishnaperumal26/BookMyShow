package com.myproject.bookmyshow.service;

import com.myproject.bookmyshow.exceptions.InvalidArgumentsExceptions;
import com.myproject.bookmyshow.exceptions.SeatNotAvailableException;
import com.myproject.bookmyshow.models.*;
import com.myproject.bookmyshow.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    @Autowired
    TicketService(SeatRepository seatRepository,
                  ShowSeatRepository showSeatRepository,
                  ShowRepository showRepository,
                  UserRepository userRepository,
                  TicketRepository ticketRepository
                  ) {

        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(List<Long> seatId, Long showId, Long userId) throws Exception {
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
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();
        //2.a. Every seat is available
        for(ShowSeat showSeat: showSeats)
        {
            //Lock everyseat (Set the status to be locked)
            showSeat.setSeatStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        //Create Ticket object and return
        Ticket ticket = new Ticket();
        //create user object for the param of ticket.setBookedBy(user);
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())
        {
            throw new InvalidArgumentsExceptions("User with id : "+userId+" doesn't exist");
        }
        User user = userOptional.get();

        ticket.setBookedBy(user);
        ticket.setStatus(TicketStatus.PROCESSING);
        ticket.setShow(show);
        ticket.setSeats(seats);
        ticket.setAmount(0);
        ticket.setTimeOfBooking(new Date());

        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket;
    }
}
