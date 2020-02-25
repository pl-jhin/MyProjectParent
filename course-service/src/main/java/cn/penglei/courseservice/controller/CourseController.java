package cn.penglei.courseservice.controller;

import cn.penglei.courseservice.Serivce.CourseService;
import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/findAllCourse")
    public List<Course> findAllCourse(@RequestBody PageHelp pageHelp){
        return courseService.findAllCourse(pageHelp);
    }

    @RequestMapping("/findCourseBySort")
    public List<Course> findCourseBySort(@RequestParam("sort") String sort){
        return courseService.findCourseBySort(sort);
    }
    @RequestMapping("/findCountCourse")
    public int findCountCourse(@RequestBody PageHelp pageHelp){
        pageHelp.setPage(0);
        pageHelp.setLimit(0);
        return courseService.findAllCourse(pageHelp).size();
    }

    @RequestMapping("/findRecommendCourse")
    public List<Course> findRecommendCourse(){
        return courseService.findRecommendCourse();
    }

    @RequestMapping("/findCourseById")
    public Course findCourseById(@RequestParam("id") String id){
        return courseService.findCourseById(id);
    }

    @RequestMapping("/selectCourse")
    public String selectCourse(@RequestParam("sid") String sid, @RequestParam("id") String id){
        return courseService.selectCourse(sid,id);
    }

}
