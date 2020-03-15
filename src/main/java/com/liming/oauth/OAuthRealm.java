package com.liming.oauth;

import com.liming.common.constant.Base;
import com.liming.entity.User;
import com.liming.entity.UserStatus;
import com.liming.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


/**
 * 自定义ShiroRealm
 */
public class OAuthRealm extends AuthorizingRealm {


    @Autowired
    UserService userService;

    /**
     * 鉴权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String account = (String)principalCollection.getPrimaryPrincipal();
        User user = userService.getUserByAccount(account);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();
        if(user.getAdmin()){
            roles.add(Base.ROLE_ADMIN);
        }

        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = (String)(token.getPrincipal());
        User user = userService.getUserByAccount(account);

        if(user == null){
            throw new UnknownAccountException();//没找到账号
        }

        if(UserStatus.blocked.equals(user.getStatus())){
            throw new LockedAccountException();//账户被禁用
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user.getAccount(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );
        return simpleAuthenticationInfo;
    }
}
