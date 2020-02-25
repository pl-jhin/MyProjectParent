package cn.penglei.courseweb.service;

import cn.penglei.courseweb.Hystrix.MyHystrix;
import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "CourseService",fallback = MyHystrix.class)
public interface CourseService {
    /**
     * @ Description   :  找到全部课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:13
     */
    @RequestMapping("/course/findAllCourse")
    List<Course> findAllCourse(@RequestBody PageHelp pageHelp);

    /**
     * @ Description   :  根据分类找到课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:13
     */
    @RequestMapping("/course/findCourseBySort")
    List<Course> findCourseBySort(@RequestParam("sort") String sort);

    /**
     * @ Description   :  获得课程总条数
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:14
     */
    @RequestMapping("/course/findCountCourse")
    Integer findCountCourse(@RequestBody PageHelp pageHelp);

    /**
     * @ Description   :  随机推荐课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:15
     */
    @RequestMapping("/course/findRecommendCourse")
    List<Course> findRecommendCourse();

    /**
     * @ Description   :  根据课程id查找详细课程信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:15
     */
    @RequestMapping("/course/findCourseById")
    Course findCourseById(@RequestParam("id") String id);

    /**
     * @ Description   :  选课
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:18
     */
    @RequestMapping("/course/selectCourse")
    String selectCourse(@RequestParam("sid") String sid, @RequestParam("id") String id);
}
