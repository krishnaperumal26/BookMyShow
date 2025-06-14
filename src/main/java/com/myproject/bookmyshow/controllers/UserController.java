package com.myproject.bookmyshow.controllers;

import com.myproject.bookmyshow.dtos.*;
import com.myproject.bookmyshow.dtos.ResponseStatus;
import com.myproject.bookmyshow.exceptions.PasswordNotMatchException;
import com.myproject.bookmyshow.models.User;
import com.myproject.bookmyshow.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) {

        User user = userService.signUp(signUpRequestDto.getName(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        if (user != null) {
            signUpResponseDto.setUserId(user.getId());
            signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        else {
            signUpResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return signUpResponseDto;
    }
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) throws PasswordNotMatchException {
        User user = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        if (user != null) {
            loginResponseDto.setUserId(user.getId());
            loginResponseDto.setName(user.getName());
            loginResponseDto.setEmail(user.getEmail());
            loginResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } else {
            loginResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return loginResponseDto;
    }
}
