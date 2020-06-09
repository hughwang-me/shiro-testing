package me.hughwang.shirotesting.controller;

import lombok.extern.slf4j.Slf4j;
import me.hughwang.shirotesting.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :  Hugh Wang https://hughwang.me
 * @date : 2020/6/9 11:46
 */
@RestController
@Slf4j
@RequestMapping("auth")
public class LoginController {

    @PostMapping(value = "login")
    public String login(@RequestBody UserVO userVO){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getUsername(),userVO.getPassword());
        try {
            subject.login(token);
        }catch (Exception e){
            return e.getMessage();
        }
        if(subject.isAuthenticated()){
            return "登录成功";
        }
        return "失败！";
    }
}
