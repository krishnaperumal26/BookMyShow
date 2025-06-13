package com.myproject.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Seat")
public class Seat extends BaseModel{
    private String seatNumber;
    @Column(name = "rowNum")
    private int rowNum;
    @Column(name="colNum")
    private int colNum;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}

