package com.liming.controller;

import com.liming.common.constant.Base;
import com.liming.common.constant.ResultCode;
import com.liming.common.result.Result;
import com.liming.entity.User;
import com.liming.oauth.OAuthSessionManager;
import com.liming.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        Result r = new Result();
        executeLogin(user.getAccount(),user.getPassword(),r);
        return r;
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        Result r = new Result();
        User temp = userService.getUserByAccount(user.getAccount());
        if(null != temp){
            r.setResultCode(ResultCode.SUCCESS);
            return r;
        }

        String account = user.getAccount();
        String password = user.getPassword();

        Long userId = userService.saveUser(user);

        if(userId > 0){
            executeLogin(account,password,r);
        }else{
            r.setResultCode(ResultCode.USER_Register_ERROR);
        }
        return r;
    }

    @PostMapping("/logout")
    public Result logout(){
        Result r = new Result();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }

    private void executeLogin(String account,String password,Result r){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account,password);
        try{
            subject.login(token);
            User currentUser = userService.getUserByAccount(account);
            //System.out.println(subject.hasRole(Base.ROLE_ADMIN));
            subject.getSession().setAttribute(Base.CURRENT_USER,currentUser);
            r.setResultCode(ResultCode.SUCCESS);
            r.simple().put(OAuthSessionManager.OAUTH_TOKEN,subject.getSession().getId());
        } catch (UnknownAccountException e) {
            r.setResultCode(ResultCode.USER_NOT_EXIST);
        } catch (LockedAccountException e) {
            r.setResultCode(ResultCode.USER_ACCOUNT_FORBIDDEN);
        } catch (AuthenticationException e) {
            r.setResultCode(ResultCode.USER_LOGIN_ERROR);
        } catch (Exception e) {
            r.setResultCode(ResultCode.ERROR);
        }
    }
}
