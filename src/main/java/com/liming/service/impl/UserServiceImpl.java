package com.liming.service.impl;

import com.liming.common.util.PasswordHelper;
import com.liming.entity.User;
import com.liming.repository.UserRepository;
import com.liming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

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

    @Override
    @Transactional
    public Long saveUser(User user){
        PasswordHelper.encryptPassword(user);
        int index = new Random().nextInt(6) + 1;
        String avatar = "/static/user/user_" + index + ".png";
        user.setAvatar(avatar);
        return userRepository.save(user).getId();
    }
}
