package cn.penglei.courseservice.Serivce;

import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;

import java.util.List;

public interface CourseService {
    List<Course> findAllCourse(PageHelp pageHelp);

    List<Course> findCourseBySort(String sort);

    Integer findCountCourse();

    List<Course> findRecommendCourse();

    Course findCourseById(String id);

    String selectCourse(String sid, String id);
}
