package com.liming.service.impl;

import com.liming.entity.User;
import com.liming.repository.UserRepository;
import com.liming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByAccount(String account) {
        return userRepository.findUserByAccount(account);
    }
}
