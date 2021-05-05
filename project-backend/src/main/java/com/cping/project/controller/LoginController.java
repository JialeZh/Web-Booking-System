package com.cping.project.controller;

import com.alibaba.fastjson.JSON;
import com.cping.project.bean.User;
import com.cping.project.bean.vo.LoginVo;
import com.cping.project.common.Result;
import com.cping.project.dao.UserDao;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

    @Autowired
    UserDao userDao;

    @PostMapping("/login")
    /*public Result login(@RequestBody LoginVo loginVo){
        //origin
        User login = userDao.login(loginVo);
        return Result.succ(login);
    }*/
    //public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
    public Result login(@RequestBody LoginVo loginVo) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword());
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return Result.fail("error");
            }
        }
        return Result.succ(loginVo);
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "Unauthorized";
    }
}
