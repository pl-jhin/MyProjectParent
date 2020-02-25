package cn.penglei.courseweb.controller;

import cn.penglei.courseweb.service.CourseService;
import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.Json;
import cn.penglei.pojo.pojo.PageHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    StringRedisTemplate redisTemplate;
    /**
     * @ Description   :  连接redis
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:13
     */
    @RequestMapping("/linkRedis")
    public String linkRedis(){
        new Thread(()->redisTemplate.opsForValue().set("linkRedis","1",3, TimeUnit.SECONDS)).start();
        return null;
    }
    /**
     * @ Description   :  分页获得课程信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 20:41
     */
    @RequestMapping("/findAllCourse")
    public Json findAllCourse(PageHelp pageHelp){
        List<Course> Courses = courseService.findAllCourse(pageHelp);
        int count = courseService.findCountCourse(pageHelp);
        return new Json(0,count,"",Courses);
    }

    /**
     * @ Description   :  根据分类找到课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:13
     */
    @RequestMapping("/findCourseBySort")
    public List<Course> findCourseBySort(@RequestParam("sort") String sort){
        return courseService.findCourseBySort(sort);
    }
    /**
     * @ Description   :  判断用户是否登录
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:14
     */
    @RequestMapping("/checkLogin")
    public String checkLogin(@CookieValue(value = "TOKEN",required = false) Cookie cookie){
        if (cookie!=null){
            return "1";
        }else return "0";
    }
    /**
     * @ Description   :  选课
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:14
     */
    @RequestMapping("/selectCourse")
    public String selectCourse(@CookieValue(value = "TOKEN",required = false) Cookie cookieToken,
                               @CookieValue(value = "Type",required = false) Cookie cookieType,
                               @RequestParam("id") String id){
        // 未登录
        if (cookieToken==null){
            return "1";
        }
        // 不是学生
        if (!"0".equals(redisTemplate.opsForValue().get(cookieType.getValue()))){
            return "2";
        }else {
            String sid = redisTemplate.opsForValue().get(cookieToken.getValue());
            return courseService.selectCourse(sid,id);
        }
    }
    /**
     * @ Description   :  随机推荐课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:15
     */
    @RequestMapping("/findRecommendCourse")
    public List<Course> findRecommendCourse(){
        return courseService.findRecommendCourse();
    }

    /**
     * @ Description   :  根据课程id查找详细课程信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:15
     */
    @RequestMapping("/findCourseById")
    public Course findCourseById(@RequestParam("id")String id){
        return courseService.findCourseById(id);
    }

    /**
     * @ Description   :  进入个人中心
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:15
     */
    @RequestMapping("/myInfo")
    public String myInfo(@CookieValue(value = "Type",required = false) Cookie cookie){
        String idType = redisTemplate.opsForValue().get(cookie.getValue());
        if ("1".equals(idType)){
            return "http://teacher.penglei.com:9051";
        }else if ("0".equals(idType)){
            return "http://student.penglei.com:9001";
        }else {
            return "http://admin.penglei.com:6001";
        }
    }
}
