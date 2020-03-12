package com.liming.controller;

import com.liming.common.constant.Base;
import com.liming.common.result.Result;
import com.liming.entity.User;
import com.liming.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
//    @RequiresRoles(Base.ROLE_ADMIN)
    public Result listUsers(){
        List<User> user = userService.findAll();
        return Result.success(user);
    }
}
