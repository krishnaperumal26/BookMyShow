package com.myproject.bookmyshow.models;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass //Don't create separate table instead put its attribute to every child class
public class BaseModel {
    @Id
    private Long id;
    private Date createdDate;
    private Date lastModifiedAt;
}
