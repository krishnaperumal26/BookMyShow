package com.myproject.bookmyshow.service;

import com.myproject.bookmyshow.config.BCryptPasswordEncoderConfig;
import com.myproject.bookmyshow.exceptions.PasswordNotMatchException;
import com.myproject.bookmyshow.models.User;
import com.myproject.bookmyshow.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder encoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }
    public User signUp(String name, String email, String password)
    {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));

        return userRepository.save(user);
    }
    public User login(String email, String password) throws PasswordNotMatchException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty())
        {
            throw new UsernameNotFoundException("User not found");
        }
        if (encoder.matches(password, optionalUser.get().getPassword())) {
            return optionalUser.get();
        }
        else {
            throw new PasswordNotMatchException("Invalid credentials");
        }
    }
}
