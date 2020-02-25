package cn.penglei.adminweb.controller;

import cn.penglei.pojo.pojo.*;
import cn.penglei.adminweb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    AdminService adminService;
    @RequestMapping("/linkRedis")
    public String linkRedis(){
        new Thread(()->redisTemplate.opsForValue().set("linkRedis","1",3, TimeUnit.SECONDS)).start();
        return null;
    }
    /**
     * @ Description   :  获得管理员信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 20:27
     */
    @RequestMapping("/getAdmin")
    public User getTeacher(@CookieValue(required = false,value = "Type")Cookie cookie){
        User user = new User();
        if (cookie==null){
            user.setId("0");
            return user;
        }else if (!redisTemplate.opsForValue().get(cookie.getValue()).equals("2")){
            user.setId("-1");
        }   return null;
    }
    /**
     * @ Description   :  分页获得学生信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 20:27
     */
    @RequestMapping("/findAllStudent")
    public Json findAllStudent(PageHelp pageHelp){
        List<User> students = adminService.findAllStudent(pageHelp);
        int count = adminService.findCountStudent(pageHelp);
        return new Json(0,count,"",students);
    }
    /**
     * @ Description   :  分页获得教师信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 20:41
     */
    @RequestMapping("/findAllTeacher")
    public Json findAllTeacher(PageHelp pageHelp){
        List<User> students = adminService.findAllTeacher(pageHelp);
        int count = adminService.findCountTeacher(pageHelp);
        return new Json(0,count,"",students);
    }
    /**
     * @ Description   :  分页获得课程信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 20:41
     */
    @RequestMapping("/findAllCourse")
    public Json findAllCourse(PageHelp pageHelp){
        List<Course> Courses = adminService.findAllCourse(pageHelp);
        int count = adminService.findCountCourse(pageHelp);
        return new Json(0,count,"",Courses);
    }
    /**
     * @ Description   :  分页完成获得公告
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 20:24
     */
    @RequestMapping("/findAllTip")
    public Json findAllTip(PageHelp pageHelp){
        List<Tip> tips = adminService.findAllTip(pageHelp);
        int count = adminService.findCountTip(pageHelp);
        return new Json(0,count,"",tips);
    }
    /**
     * @ Description   :  新增公告
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 22:26
     */
    @RequestMapping("/insertNewTip")
    public String insertNewTip(Tip tip){
        adminService.insertNewTip(tip);
        return "0";
    }
    /**
     * @ Description   :  首页数据
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 22:26
     */
    @RequestMapping("/findNumber")
    public List<Integer> findNumber(){
        List<Integer> list = new ArrayList<>();
        PageHelp pageHelp = new PageHelp();
        list.add(adminService.findCountStudent(pageHelp));
        list.add(adminService.findCountTeacher(pageHelp));
        list.add(adminService.findCountCourse(pageHelp));
        list.add(adminService.findCountTip(pageHelp));
        return list;
    }
    /**
     * @ Description   :  新增学生
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 22:26
     */
    @RequestMapping("/insertNewStudent")
    public String insertNewStudent(User user){
        user.setIdType("0");
        user.setPassword("123456");
        return adminService.insertNewUser(user);
    }

    /**
     * @ Description   :  新增教师
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 22:26
     */
    @RequestMapping("/insertNewTeacher")
    public String insertNewTeacher(User user){
        user.setIdType("1");
        user.setPassword("123456");
        return adminService.insertNewUser(user);
    }

    /**
     * @ Description   :  重置密码
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 22:43
     */
    @RequestMapping("/resetPassword")
    public String resetPassword(String id){
        return adminService.resetPassword(id);
    }

    /**
     * @ Description   :  修改用户名
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-24 14:58
     */
    @RequestMapping("/updateUserName")
    public String updateUserName(@RequestParam("id") String id,@RequestParam("username")String username){
        adminService.updateUserName(id,username);
        return "1";
    }
    @RequestMapping("/updatePassword")
    public String updatePassword(@CookieValue(required = false,value = "TOKEN")Cookie cookie,
                                 @RequestParam("oldpwd") String oldpwd,
                                 @RequestParam("newpwd") String newpwd){
        String tId = redisTemplate.opsForValue().get(cookie.getValue());
        return adminService.updatePassword(tId,oldpwd,newpwd);
    }
}
