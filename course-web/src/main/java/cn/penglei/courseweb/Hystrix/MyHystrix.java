package cn.penglei.courseweb.Hystrix;

import cn.penglei.courseweb.service.CourseService;
import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MyHystrix implements CourseService {
    @Override
    public List<Course> findAllCourse(PageHelp pageHelp) {
        System.out.println("findAllCourse降级了");
        return null;
    }

    @Override
    public List<Course> findCourseBySort(String sort) {
        System.out.println("findCourseBySort降级了");
        return null;
    }

    @Override
    public Integer findCountCourse(PageHelp pageHelp) {
        System.out.println("findCountCourse被降级了");
        return null;
    }

    @Override
    public List<Course> findRecommendCourse() {
        System.out.println("findRecommendCourse降级了");
        return null;
    }

    @Override
    public Course findCourseById(String id) {
        System.out.println("findCourseById降级了");
        return null;
    }

    @Override
    public String selectCourse(String cookieValue, String id) {
        System.out.println("selectCourse降级了");
        return null;
    }
}
