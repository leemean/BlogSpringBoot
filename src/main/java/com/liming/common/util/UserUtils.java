package com.liming.common.util;

import com.liming.common.constant.Base;
import com.liming.entity.User;
import org.apache.shiro.SecurityUtils;

public class UserUtils {
    public static User getCurrentUser(){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Base.CURRENT_USER);
        return user;
    }
}
