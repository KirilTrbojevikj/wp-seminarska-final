package com.review.seminarska.service.Impl;

import com.review.seminarska.model.User;
import com.review.seminarska.model.exceptions.InvalidArgumentsException;
import com.review.seminarska.model.exceptions.InvalidUserCredentialsException;
import com.review.seminarska.repository.UserRepository;
import com.review.seminarska.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
