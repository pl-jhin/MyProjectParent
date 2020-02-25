package com.penglei.ssologin.controller;

import cn.penglei.pojo.pojo.User;
import com.penglei.ssologin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private LoginService loginService;

    // 登录接口
    @ResponseBody
    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("userNumber") String userNumber,
                          @RequestParam("password") String password,
                          @RequestParam("idType")String idType,
                          HttpServletResponse response,
                          @CookieValue(value = "CHECKCODE") Cookie code,
                          @RequestParam("checkCode") String checkCode,
                          @RequestParam("target")String target) {
        String check = code.getValue();
        // 验证码判断
        if (!checkCode.equals(redisTemplate.opsForValue().get(check))){
            return "1";
        }
        // 创建对象，其实可以直接通过对象来接受参数
        User user = new User();
        user.setUserNumber(userNumber);
        user.setPassword(password);
        user.setIdType(idType);
        // 判断账号密码是否正确
        User checkLogin = loginService.checkLogin(user);
        // 创建token 和 target
        String tokenUserId = UUID.randomUUID().toString().replaceAll("-", "");
        String tokenType = UUID.randomUUID().toString().replaceAll("-", "");
        if (checkLogin!=null) {
            // 将token存入Redis，其中是以token为K，用户id为V
            new Thread(()-> {
                redisTemplate.opsForValue().set(tokenUserId, String.valueOf(checkLogin.getId()));
                redisTemplate.opsForValue().set(tokenType, idType);
            }).start();
            // 创建Cookie
            new Thread(()->{
                // 通过uuid创建token
                // 将token存入Cookie
                Cookie cookieId = new Cookie("TOKEN", tokenUserId);
                cookieId.setDomain("penglei.com");
                cookieId.setPath("/");
                response.addCookie(cookieId);
                Cookie cookieType = new Cookie("Type", tokenType);
                cookieType.setDomain("penglei.com");
                cookieType.setPath("/");
                response.addCookie(cookieType);
            }).start();
            // 删除验证码Cookie
            Cookie newCookie = new Cookie("CHECKCODE","0");
            newCookie.setPath("/");
            newCookie.setMaxAge(0);
            response.addCookie(newCookie);
            return target;
        }else return "2";
    }

    // 注销接口
    @RequestMapping("logout")
    public String logout(@CookieValue(value = "TOKEN") Cookie cookies,
                         @CookieValue(value = "Type") Cookie type,
                         String target,
                         HttpServletResponse response){
        // 设置新的cookie
        Cookie cookieId = new Cookie("TOKEN","0");
        cookieId.setMaxAge(0);
        cookieId.setDomain("penglei.com");
        cookieId.setPath("/");
        // 设置新的cookie
        Cookie cookieType = new Cookie("Type","0");
        cookieType.setMaxAge(0);
        cookieType.setDomain("penglei.com");
        cookieType.setPath("/");
        // 删除key
        redisTemplate.delete(cookies.getValue());
        redisTemplate.delete(type.getValue());
        // 销毁cookie
        response.addCookie(cookieId);
        response.addCookie(cookieType);
        // 回到原始页面
        return "redirect:" + target;
    }
}
