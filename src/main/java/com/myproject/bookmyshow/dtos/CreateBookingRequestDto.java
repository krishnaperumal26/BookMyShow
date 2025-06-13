package com.myproject.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDto {
    private List<Long> showSeatId;
    private Long userId;
    private Long showId;


}
