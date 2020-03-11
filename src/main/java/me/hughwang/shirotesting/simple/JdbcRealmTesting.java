package me.hughwang.shirotesting.simple;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//@Service
@Slf4j
public class JdbcRealmTesting {

    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/uwjx?serverTimezone=UTC&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("Uwjx@2020");
    }

    @PostConstruct
    public void run(){
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        JdbcRealm realm = new JdbcRealm();

        realm.setDataSource(dataSource);

        String sql = "select password from users where user_name = ?";
        realm.setAuthenticationQuery(sql);

        String roleSql = "select role_name from roles where user_name = ?";
        realm.setUserRolesQuery(roleSql);

        String permissionSql = "select permission from permissions where role_name = ?";
        realm.setPermissionsQuery(permissionSql);

        securityManager.setRealm(realm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("wanghuan","123456");
        subject.login(token);
        log.warn("是否登录成功:{}" , subject.isAuthenticated());

        subject.checkRole("admin");
        log.warn("检查角色成功");
        subject.checkPermissions("delete","update");
        log.warn("检查权限成功");
    }
}
