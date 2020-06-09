package me.hughwang.shirotesting.controller;

import lombok.extern.slf4j.Slf4j;
import me.hughwang.shirotesting.servvice.UserService;
import me.hughwang.shirotesting.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author :  Hugh Wang https://hughwang.me
 * @date : 2020/6/9 11:46
 */
@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping(value = "list")
    public String list(@RequestBody UserVO userVO){
        userService.findUserByUsername(userVO);
        return "list 成功！";
    }

    @RequiresPermissions("user:add")
    @PostMapping(value = "add")
    public String add(@RequestBody UserVO userVO){

        return " add 成功！";
    }

    @RequiresPermissions("user:delete")
    @PostMapping(value = "delete")
    public String delete(@RequestBody UserVO userVO){

        return " delete 成功！";
    }
}
