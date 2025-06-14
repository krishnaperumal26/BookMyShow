package com.myproject.bookmyshow.service;

import com.myproject.bookmyshow.exceptions.SeatNotAvailableException;
import com.myproject.bookmyshow.exceptions.ShowNotFoundException;
import com.myproject.bookmyshow.exceptions.UserNotFoundException;
import com.myproject.bookmyshow.models.*;
import com.myproject.bookmyshow.repositories.BookingRespository;
import com.myproject.bookmyshow.repositories.ShowRepository;
import com.myproject.bookmyshow.repositories.ShowSeatRepository;
import com.myproject.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookingService {

    UserRepository userRepository;
    ShowRepository showRepository;
    ShowSeatRepository showSeatRepository;
    PriceCalculator priceCalculator;
    BookingRespository bookingRespository;
    public BookingService(UserRepository userRepository, ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository, PriceCalculator priceCalculator,
                          BookingRespository bookingRespository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculator = priceCalculator;
        this.bookingRespository = bookingRespository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Long userId, List<Long> showSeatIds, Long showId) throws UserNotFoundException, ShowNotFoundException, SeatNotAvailableException {
         /*
        1. Get the user from the given userId
        2. Get the show from the given showId
        3. Get the ShowSeats from the given seatIds
        4. Check if the seats are available or not
        5. If not, throw an exception
        6. If available, mark the status as seats as Blocked
        7. save the changes in the DB as well
        8. Create the booking with pending status. [Save booking]
        9. Return the booking object.
         */

        //1. Get the user from the given userId
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())
        {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        User user = userOptional.get();
        //2. Get the show from the given showId
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty())
        {
            throw new ShowNotFoundException("Show not found with id: " + showId);
        }
        Show show = showOptional.get();
        //3. Get the seats from the given seatIds
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        //4. Check if the seats are available or not

        for(ShowSeat showSeat : showSeats)
        {
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE))
            {
                //5. If not, throw an exception
                throw new SeatNotAvailableException("Seat not available with id: " + showSeat.getId());
            }
        }

        //6. If available, mark the status as seats as Blocked
        List<ShowSeat> updatedShowSeat = null;
        for(ShowSeat showSeat : showSeats)
        {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            //7. save the changes in the DB as well
            updatedShowSeat.add(showSeatRepository.save(showSeat));
        }

        //8. Create the booking with pending status. [Save booking]
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreatedDate(new Date());
        booking.setUser(user);
        booking.setShow(show);
        booking.setPayments(new ArrayList<>());
        booking.setShowSeats(updatedShowSeat);
        booking.setAmount(priceCalculator.calculatePrice(show, showSeats));
        return bookingRespository.save(booking);
    }
}
