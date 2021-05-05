package com.cping.project.config;

import com.cping.project.bean.User;
import com.cping.project.bean.vo.LoginVo;
import com.cping.project.dao.UserDao;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm  {

    @Autowired
    UserDao userDao;
    /*@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername(username);
        loginVo.setPassword(password);
        User login = userDao.login(loginVo);
        //User login = userDao.getUserByMassage(username);
        //System.out.println(username+"------"+password);
        if (login == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("execute==>doGetAuthenticationInfo");
        String username = authenticationToken.getPrincipal().toString();
        String password = userDao.getPasswordByUsername(username);

        if (password != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    username,
                    password,
                    getName());
            return authenticationInfo;
        }
        return null;
    }
}
