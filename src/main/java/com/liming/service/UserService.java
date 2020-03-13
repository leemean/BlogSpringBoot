package com.liming.service;

import com.liming.entity.User;
import java.util.List;

public interface UserService {
    /**
     * 查找所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 通过account查找用户
    * @param account
     * @return
     */
    User getUserByAccount(String account);

    Long saveUser(User user);
}
