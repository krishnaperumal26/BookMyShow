package com.myproject.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticker extends BaseModel{
    private int amount;
    private Date timeOfBooking;
    @ManyToMany
    private List<Chair> seats;
    @ManyToOne
    private User bookedBy;
    @ManyToOne
    private Event event;
    @OneToMany
    private List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus status;
}
