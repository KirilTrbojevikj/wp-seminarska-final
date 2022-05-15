package com.review.seminarska.service.Impl;

import com.review.seminarska.model.User;
import com.review.seminarska.model.exceptions.InvalidUsernameOrPasswordException;
import com.review.seminarska.model.exceptions.PasswordsDoNotMatchException;
import com.review.seminarska.model.exceptions.UsernameAlreadyExistsException;
import com.review.seminarska.repository.UserRepository;
import com.review.seminarska.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String first_name, String last_name, String password, String repeatPassword, String email) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, passwordEncoder.encode(password), first_name, last_name, email);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }


}
