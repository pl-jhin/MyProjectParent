package cn.penglei.teacherweb.controller;

import cn.penglei.pojo.pojo.*;
import cn.penglei.teacherweb.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    TeacherService teacherService;
    /**
     * @ Description   :  连接redis
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 14:46
     */
    @RequestMapping("/linkRedis")
    public String linkRedis(){
        new Thread(()->redisTemplate.opsForValue().set("linkRedis","1",3, TimeUnit.SECONDS)).start();
        return null;
    }

    /**
     * @ Description   :  获得课程信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:44
     */
    @RequestMapping("/findCourse")
    public List<Course> findCourse(@CookieValue(required = false,value = "TOKEN")Cookie cookie,
                                   @RequestParam(value = "page",required = false,defaultValue = "") String page){
        if (cookie==null){
            return null;
        }else {
            String id = redisTemplate.opsForValue().get(cookie.getValue());
            return teacherService.findCourse(id, page);
        }
    }
    /**
     * @ Description   :  删除课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:49
     */
    @RequestMapping("/deleteCourse")
    public String deleteCourse(@CookieValue(required = false,value = "TOKEN")Cookie cookie,
                               @RequestParam("id") String id){
        String tId = redisTemplate.opsForValue().get(cookie.getValue());

        return teacherService.deleteCourse(tId,id);
    }

    /**
     * @ Description   :  新增课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:50
     */
    @RequestMapping("/insertCourse")
    public String insertCourse(@CookieValue(required = false,value = "TOKEN")Cookie cookie,
                               Course course){
        String submitTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        course.setSubmitTime(submitTime);
        String tId = redisTemplate.opsForValue().get(cookie.getValue());
        course.setTeacherId(Integer.valueOf(tId));
        return teacherService.insertNewCourse(course);
    }
    /**
     * @ Description   :  获得我的学生
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:50
     */
    @RequestMapping("/findMyStudent")
    public Json findMyStudent(@CookieValue(required = false,value = "TOKEN")Cookie cookie,
                              PageHelp pageHelp){
        String id = redisTemplate.opsForValue().get(cookie.getValue());
        pageHelp.setId(id);
        List<SelectCourse> myStudent = teacherService.findMyStudent(pageHelp);
        // 获得全部学生的数量
        int count = teacherService.findCountStudent(pageHelp);
        return new Json(0,count,"",myStudent);
    }
    /**
     * @ Description   :  获得教师信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:50
     */
    @RequestMapping("/getTeacher")
    public User getTeacher(@CookieValue(required = false,value = "TOKEN")Cookie cookie,
                           @CookieValue(required = false,value = "Type")Cookie cookieType){
        User user = new User();
        if (cookie==null){
            user.setId("0");
            return user;
        }else if (!redisTemplate.opsForValue().get(cookieType.getValue()).equals("1")){
            user.setId("-1");
            return user;

        }
        else {
            String tId = redisTemplate.opsForValue().get(cookie.getValue());
            User teacher = teacherService.getTeacher(tId);
            return teacher;
        }
    }
    /**
     * @ Description   :  修改密码
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:50
     */
    @RequestMapping("/updatePassword")
    public String updatePassword(@CookieValue(required = false,value = "TOKEN")Cookie cookie,
                               @RequestParam("oldpwd") String oldpwd,
                               @RequestParam("newpwd") String newpwd){
        String tId = redisTemplate.opsForValue().get(cookie.getValue());
        return teacherService.updatePassword(tId,oldpwd,newpwd);
    }

    /**
     * @ Description   :  获得公告
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 14:46
     */
    @RequestMapping("/getTip")
    public Json getTip(PageHelp pageHelp){
        List<Tip> tips = teacherService.getTip(pageHelp);
        int count = teacherService.findCountTip(pageHelp);
        return new Json(0,count,"",tips);
    }
}
