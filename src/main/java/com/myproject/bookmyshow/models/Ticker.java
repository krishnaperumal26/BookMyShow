package com.myproject.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private List<Seat> seats;
    @ManyToOne
    private User bookedBy;
    @ManyToOne
    private Show show;
    @OneToMany
    private List<Payment> payments;
    private TicketStatus ticketStatus;
}
