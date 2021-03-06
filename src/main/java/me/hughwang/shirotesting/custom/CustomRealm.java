package me.hughwang.shirotesting.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    Map<String , String> userMap = new HashMap<>();

    {
        userMap.put("wanghuan","123456");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.warn("鉴权处理");

        String username = (String)principals.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("user");

        Set<String> permissions = new HashSet<>();
        permissions.add("user:delete");
        permissions.add("user:update");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.warn("认证处理");
        log.warn("认证信息-> {}" , token.toString());
        String username = (String)token.getPrincipal();
        String passwordAuth = userMap.get(username);
        if(passwordAuth == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username , passwordAuth , "CustomRealm");
//        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("uwjx")); //对加盐解析
        return authenticationInfo;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456" , "uwjx"); //对字符串md5处理 加盐
        log.warn("加密后密码: {}" , md5Hash.toString());
    }
}
