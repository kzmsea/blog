package com.kzm.service;

import com.kzm.dao.UserRepository;
import com.kzm.entity.User;
import com.kzm.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.checkUser(username, MD5Utils.getMD5(password));
    }
}
