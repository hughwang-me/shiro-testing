package me.hughwang.shirotesting.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


//@Service
@Slf4j
public class CustomRealmTesting {

    @PostConstruct
    public void run(){
        log.warn("测试JDBC验证");
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        CustomRealm customRealm = new CustomRealm();

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(); //对密码加密
        matcher.setHashAlgorithmName("md5"); //加密算法
        matcher.setHashIterations(1); //加密次数
        customRealm.setCredentialsMatcher(matcher); //realm增加加密处理

        securityManager.setRealm(customRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("wanghuan","123456");
        subject.login(token);
        log.warn("是否登录成功:{}" , subject.isAuthenticated());

        subject.checkRoles("admin" , "user");
        subject.checkPermissions("user:delete","user:update");
    }
}
