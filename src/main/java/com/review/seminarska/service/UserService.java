package com.review.seminarska.service;

import com.review.seminarska.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    User register(String username, String first_name, String last_name, String password, String repeatPassword, String email);

    UserDetails loadUserByUsername(String username);
}
