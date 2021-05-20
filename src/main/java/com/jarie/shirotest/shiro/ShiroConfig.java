package com.jarie.shirotest.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroConfig {
    //    @Bean
//    public Realm getRealm(){
//        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
//        return iniRealm;
//    }
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(2);//hash排序2次
        return matcher;
    }
    @Bean
    public Realm getRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(Realm Realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(Realm);
        return securityManager;
    }

    /**
     * anon:匿名用户可访问
     * authc：认证用户可访问
     * user：使用remember me 的用户可访问
     * perms：对应权限可访问
     * role：对应角色才可访问
     * shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
     * <p>
     * <p>
     * shiro默认将未授权的的请求转发给login.jsp
     * 可以通过设置修改默认 配置
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        HashMap<String, String> map = new HashMap<>();
//        map.put("/user/login", "anon");
        map.put("/index.html", "authc");
        map.put("/", "anon");
        map.put("/exit", "logout");//logout，请求路径/exit，就会将登录状态设置为无状态登录
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/login.html");//没有验证时，访问跳转的页面
        return shiroFilterFactoryBean;
    }
}
