package cn.penglei.studentweb.Controller;

import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.SelectCourse;
import cn.penglei.pojo.pojo.User;
import cn.penglei.studentweb.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    StringRedisTemplate redisTemplate;
    /**
     * @ Description   :  获得学生信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:09
     */
    @RequestMapping("/getStudent")
    public User getTeacher(@CookieValue(required = false,value = "TOKEN")Cookie cookie,
                           @CookieValue(required = false,value = "Type")Cookie cookieType){
        User user = new User();
        if (cookie==null){
            user.setId("0");
            return user;
        }else if (!redisTemplate.opsForValue().get(cookieType.getValue()).equals("0")){
            user.setId("-1");
            return user;
        }else {
            String sId = redisTemplate.opsForValue().get(cookie.getValue());
            User student = studentService.getStudent(sId);
            return student;
        }
    }
    /**
     * @ Description   :  查找已选课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:09
     */
    @RequestMapping("/findMyCourse")
    public List<Course> findMyCourse(@CookieValue(value = "TOKEN",required = false) Cookie cookie){
        return studentService.findMyCourse(redisTemplate.opsForValue().get(cookie.getValue()));
    }
    /**
     * @ Description   :  修改密码
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:55
     */
    @RequestMapping("/updatePassword")
    public String updatePassword(@CookieValue(required = false,value = "TOKEN")Cookie cookie,
                                 @RequestParam("oldpwd") String oldpwd,
                                 @RequestParam("newpwd") String newpwd){
        String tId = redisTemplate.opsForValue().get(cookie.getValue());
        return studentService.updatePassword(tId,oldpwd,newpwd);
    }

    /**
     * @ Description   :  退选课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:10
     */
    @RequestMapping("/deleteSelect")
    public String deleteSelect(@CookieValue(required = false,value = "TOKEN")Cookie cookie,
                               @RequestParam("id")String id){
        String sId = redisTemplate.opsForValue().get(cookie.getValue());
        return studentService.deleteSelect(sId,id);
    }
}
