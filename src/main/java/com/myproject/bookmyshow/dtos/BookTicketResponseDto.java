package com.myproject.bookmyshow.dtos;

import com.myproject.bookmyshow.models.TicketStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketResponseDto {
    private Long ticketId;
    private TicketStatus ticketStatus;
    private int amount;
    private List<String> seatNumber;
    private String auditoriumName;


}
