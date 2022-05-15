package com.review.seminarska.service;

import com.review.seminarska.model.User;

public interface AuthService {

    User login(String username, String password);
}
