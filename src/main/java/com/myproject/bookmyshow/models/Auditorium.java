package com.myproject.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class Auditorium extends BaseModel{
    private String name;
    @OneToMany
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
//    @OneToMany
//    private List<Show> shows; // Shows AVAILABLE FOR BOOKING
//    @OneToOne
//    private Show show; //current running show


}
