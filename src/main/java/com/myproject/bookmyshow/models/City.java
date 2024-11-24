package com.myproject.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class City extends BaseModel{
    @OneToMany(mappedBy = "city") //put name of the attribute in the other class, that is represent the relationship
    private List<Theater> theaters;
    private String name;


}
