package com.myproject.bookmyshow.dtos;

import lombok.Data;

@Data
public class LoginResponseDto {
    private Long userId;
    private String name;
    private String email;
    private ResponseStatus responseStatus;
}
