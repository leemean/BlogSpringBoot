package com.liming.config;

import com.liming.oauth.OAuthRealm;
import com.liming.oauth.OAuthSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 记住我管理器 cookie管理对象;
     *
     * @return
     */
    @Bean(name = "cookieRememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        System.out.println("rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // <!-- 记住我cookie生效时间30天（259200） ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

//    @Bean(name = "sessionManager")
//    public SessionManager sessionManager(OAuthSessionDAO oAuthSessionDAO){
//        OAuthSessionManager oAuthSessionManager = new OAuthSessionManager();
//        oAuthSessionManager.setSessionDAO(oAuthSessionDAO);
//        return oAuthSessionManager;
//    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm")OAuthRealm authRealm,
                                           @Qualifier("cookieRememberMeManager")CookieRememberMeManager cookieRememberMeManager//,
                                           //@Qualifier("sessionManager")SessionManager sessionManager
    ){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(authRealm);
//        securityManager.setSessionManager(sessionManager);
        //设置rememberMe管理器
        securityManager.setRememberMeManager(cookieRememberMeManager);
        return securityManager;
    }

    /**
     * 密码匹配凭证管理器
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

//    /**
//     * 缓存管理器
//     * @return
//     */
//    @Bean(value="ehCacheManager")
//    public EhCacheManager ehCacheManager(@Qualifier("ehCacheManagerFactoryBean") EhCacheManagerFactoryBean bean) {
//        log.info("ehCacheManager()");
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
//        return cacheManager;
//    }


    @Bean(name = "authRealm")
    public OAuthRealm oAuthRealm(
            @Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher hashedCredentialsMatcher){
        OAuthRealm shiroRealm = new OAuthRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return shiroRealm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String,String> map = new LinkedHashMap<String,String>();
//        map.put("/logout","logout");
//        map.put("/login","login");
//        map.put("/logon","logon");
//        map.put("/**","authc");
        map.put("/**","anon");
        //如果不设置默认会自动寻找web工程目录下的"/login.jsp"页面
        factoryBean.setLoginUrl("/login");
        factoryBean.setSuccessUrl("/index");
        factoryBean.setUnauthorizedUrl("/unauthorized");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }


}
