package com.myproject.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Event event;
    @ManyToOne
    private Chair seat;
    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus seatStatus;
}
