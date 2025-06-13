package com.myproject.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    private String bookingNumber;
    @ManyToOne
    private User user;
    @ManyToOne
    private Show show;
    @ManyToMany
    private List<ShowSeat> showSeats;
    private int amount;
    @OneToMany
    private List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    /*
    1           1
    Booking ---- User    --> M:1
    M            1

    1           1
    Booking ---- Show    --> M:1
    M            1

    1           M
    Booking ---- ShowSeat --> M:M
    M            1 (If the seat is canceled and rebooked, it will be a new ShowSeat entry)

    1           M
    Booking ----- Payment --> 1:M
    1           1
     */
}
