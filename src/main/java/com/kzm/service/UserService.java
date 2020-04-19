package com.kzm.service;

import com.kzm.entity.User;

public interface UserService {
    User checkUser(String username, String password);
}
