package com.finance.costmanager.service;

import com.finance.costmanager.model.User;

public interface UserService {

    User auth(String email, String password);
    User entry(User user);
    void validateEmail(String email);
}
