package com.myproject.bookmyshow;

import com.myproject.bookmyshow.controllers.UserController;
import com.myproject.bookmyshow.dtos.SignUpRequestDto;
import com.myproject.bookmyshow.dtos.SignUpResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookMyShowApplicationTests {

    @Autowired
    UserController userController;
    @Test
    void contextLoads() {
    }
    @Test
    void testSignUp() {
        SignUpRequestDto requestDto = new SignUpRequestDto();
        requestDto.setName("John Doe");
        requestDto.setEmail("John@gmail.com");
        requestDto.setPassword("password123");
        SignUpResponseDto responseDto = userController.signUp(requestDto);
        System.out.println("User Id is "+responseDto.getUserId());
    }

}
