package com.myproject.bookmyshow.controllers;

import com.myproject.bookmyshow.dtos.BookTicketRequestDto;
import com.myproject.bookmyshow.dtos.BookTicketResponseDto;
import com.myproject.bookmyshow.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//This is not the ticket object after payment for generating ticket.
//This Object is created when Pay button is clicked after selecting ticket. So Dtos haveing attributes bsed on this Not after payment.
@Controller
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    BookTicketResponseDto bookTicket(BookTicketRequestDto request)
    {


        return null;
    }
}
