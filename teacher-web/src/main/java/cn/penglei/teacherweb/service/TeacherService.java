package cn.penglei.teacherweb.service;

import cn.penglei.pojo.pojo.*;
import cn.penglei.teacherweb.hystrix.MyHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "TeacherServer",fallback = MyHystrix.class)
public interface TeacherService {
    /**
     * @ Description   :  获得课程信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:44
     */
    @RequestMapping("/teacher/findCourse")
    List<Course> findCourse(@RequestParam("id") String id,
                                   @RequestParam("page")String page);
    /**
     * @ Description   :  删除课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:52
     */
    @RequestMapping("/teacher/deleteCourse")
    String deleteCourse(@RequestParam("tId") String tId, @RequestParam("id") String id);

    /**
     * @ Description   :  新增课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:52
     */
    @RequestMapping("/teacher/insertNewCourse")
    String insertNewCourse(@RequestBody Course course);
    /**
     * @ Description   :  找到我的学生
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:52
     */
    @RequestMapping("/teacher/findMyStudent")
    List<SelectCourse> findMyStudent(@RequestBody PageHelp pageHelp);
    /**
     * @ Description   :  获得教师信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:52
     */
    @RequestMapping("/teacher/getTeacher")
    User getTeacher(@RequestParam("id")String tId);
    /**
     * @ Description   :  获得公告
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:53
     */
    @RequestMapping("/teacher/getTip")
    List<Tip> getTip(@RequestBody PageHelp pageHelp);

    /**
     * @ Description   :  获得全部学生数量
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 16:23
     */
    @RequestMapping("/teacher/findCountStudent")
    int findCountStudent(@RequestBody PageHelp pageHelp);
    /**
     * @ Description   :  获得全部公告数量
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:00
     */
    @RequestMapping("/teacher/findCountTip")
    int findCountTip(PageHelp pageHelp);
    /**
     * @ Description   :  修改密码
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:23
     */
    @RequestMapping("/teacher/updatePassword")
    String updatePassword(@RequestParam("id") String tId,
                          @RequestParam("oldpwd") String oldpwd,
                          @RequestParam("newpwd") String newpwd);
}
