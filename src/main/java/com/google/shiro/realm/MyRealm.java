package com.google.shiro.realm;

import com.google.domain.User;
import com.google.service.UserService;
import com.google.util.Cryptographyutil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       String username= (String) authenticationToken.getPrincipal();    //用这个用户名进行查询

            User user = userService.selectUserByUsername(username);    //把用户查出来了
                                                                       //查出来的用户 用户名变成了空值


        if (user!=null){
           user.setUserName(username);
            return new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"code");
             }


        return null;
    }
}
