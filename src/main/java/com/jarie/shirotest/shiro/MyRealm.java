package com.jarie.shirotest.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return "MyRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.iterator().next();
        String[] role = {"角色1","角色2"};//根据用户名查询角色
        Set roles = new HashSet(Arrays.asList(role));
        String[] permissions = {"qx1","qx2"};//根据用户名查询权限
        Set ps = (HashSet<String>) new HashSet<>(Arrays.asList(permissions));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setObjectPermissions(ps);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        String password = "123456";//根据用户查询密码
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password,getName());
        return info;
    }
}
