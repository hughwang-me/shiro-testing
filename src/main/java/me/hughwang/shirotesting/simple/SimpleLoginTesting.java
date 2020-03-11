package me.hughwang.shirotesting.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//@Service
@Slf4j
public class SimpleLoginTesting {

    private DefaultSecurityManager securityManager = new DefaultSecurityManager();

    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @PostConstruct
    public void run(){
        log.warn("测试 Shiro 简单认证");

        simpleAccountRealm.addAccount("wanghuan" , "123456");
        securityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("wanghuan1","123456");

        subject.login(token);

        log.warn("认证是否成功:{}" , subject.isAuthenticated());

        subject.logout();
        log.warn("退出后 认证是否成功:{}" , subject.isAuthenticated());
    }

}
