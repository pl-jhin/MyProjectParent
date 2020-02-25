package com.penglei.ssologin.controller;

import cn.penglei.checkCode.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @RequestMapping("/linkRedis")
    @ResponseBody
    public String linkRedis(){
        new Thread(()->redisTemplate.opsForValue().set("penglei","1231321",3, TimeUnit.SECONDS)).start();
        return null;
    }

    @RequestMapping("/login")
    public String login(@RequestParam(required = false,defaultValue = "") String target,
                        HttpSession session,
                        @CookieValue(required = false,value = "TOKEN") Cookie cookie){
        if ("".equals(target)){
            target = "http://student.penglei.com:8051/index.html";
        }
        if (cookie!= null) {
            return "redirect:"+target;
        }
        System.out.println("target="+target);

        session.setAttribute("target",target);
        return "redirect:../login.html";
    }

    @RequestMapping("/register")
    public String register(@RequestParam(required = false,defaultValue = "") String target,
                         HttpSession session,
                         @CookieValue(required = false,value = "TOKEN") Cookie cookie){
        if ("".equals(target)){
            target = "http://student.penglei.com:8051/index.html";
        }
        if (cookie!= null) {
            return "redirect:"+target;
        }
        System.out.println("target="+target);

        session.setAttribute("target",target);
        return "redirect:../register.html";
    }
    @RequestMapping(value = "/getVerify")
    public void getVerify(@CookieValue(value = "CHECKCODE",required = false)Cookie cookie,HttpServletResponse response) {

            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            if (cookie==null){
                cookie =null;
            }
            randomValidateCode.getRandcode(cookie,redisTemplate ,response);//输出验证码图片方法
    }
}
