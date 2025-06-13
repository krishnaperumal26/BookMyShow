package com.myproject.bookmyshow.controllers;

import com.myproject.bookmyshow.dtos.CreateBookingRequestDto;
import com.myproject.bookmyshow.dtos.CreateBookingResponseDto;
import com.myproject.bookmyshow.service.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto requestDto)
            throws Exception
    {




        return null;
    }
}
