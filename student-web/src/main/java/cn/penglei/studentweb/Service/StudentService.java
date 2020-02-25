package cn.penglei.studentweb.Service;

import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.SelectCourse;
import cn.penglei.pojo.pojo.User;
import cn.penglei.studentweb.Hystrix.MyHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "StudentServer",fallback = MyHystrix.class)
public interface StudentService {

    /**
     * @ Description   :  获得我的课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:55
     */
    @RequestMapping("/student/findMyCourse")
    List<Course> findMyCourse(@RequestParam("id") String id);
    /**
     * @ Description   :  获得学生
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:55
     */
    @RequestMapping("/student/getStudent")
    User getStudent(@RequestParam("id") String id);

    /**
     * @ Description   :  修改密码
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:55
     */
    @RequestMapping("/student/updatePassword")
    String updatePassword(@RequestParam("id") String tId,
                          @RequestParam("oldpwd") String oldpwd,
                          @RequestParam("newpwd") String newpwd);
    /**
     * @ Description   :  退选课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:10
     */
    @RequestMapping("/student/deleteSelect")
    String deleteSelect(@RequestParam("sId") String sId,
                        @RequestParam("id") String id);
}
