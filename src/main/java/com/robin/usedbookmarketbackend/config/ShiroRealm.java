package com.robin.usedbookmarketbackend.config;

import com.robin.reactmarket.model.User;
import com.robin.reactmarket.service.UserRoleService;
import com.robin.reactmarket.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    //授权(用户认证)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //角色设置
        simpleAuthorizationInfo.setRoles(userRoleService.getUserRoleByUserId(user.getUserID()));
        //权限设置
        //simpleAuthorizationInfo.setStringPermissions();
        return simpleAuthorizationInfo;
    }
    //认证(身份认证)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken authToken = (UsernamePasswordToken) token;
        String userName = authToken.getUsername();
        User user = userService.getUserByUserName(userName);
        if(user == null){//找不到用户
            throw new UnknownAccountException();
        }else if(user.getUserState() == 0){//用户状态0,表示禁用
            throw new DisabledAccountException();
        }else {//Shiro认证
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user,
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),
                    getName()
            );
            System.out.println(ByteSource.Util.bytes(user.getSalt()));
            return authenticationInfo;
        }
    }

}
