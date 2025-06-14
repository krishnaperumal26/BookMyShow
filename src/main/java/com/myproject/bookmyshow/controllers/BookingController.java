package com.myproject.bookmyshow.controllers;

import com.myproject.bookmyshow.dtos.CreateBookingRequestDto;
import com.myproject.bookmyshow.dtos.CreateBookingResponseDto;
import com.myproject.bookmyshow.dtos.ResponseStatus;
import com.myproject.bookmyshow.models.Booking;
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
        CreateBookingResponseDto responseDto = new CreateBookingResponseDto();
        try {
            Booking booking = bookingService.createBooking(requestDto.getUserId(), requestDto.getShowSeatId(), requestDto.getShowId());
            responseDto.setBookingId(booking.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }


        return responseDto;
    }
}
