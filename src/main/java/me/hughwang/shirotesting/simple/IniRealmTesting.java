package me.hughwang.shirotesting.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//@Service
@Slf4j
public class IniRealmTesting {

    @PostConstruct
    public void run(){
        log.warn("测试 IniRealm ");
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:iniRealm.ini");
//        iniRealm.setResourcePath("classpath:iniRealm.ini");
        securityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("wanghuan","123456");
        subject.login(token);
        log.warn("是否登录成功:{}" , subject.isAuthenticated());

        subject.checkRoles("admin");
        subject.checkPermissions("delete","update");
    }
}
