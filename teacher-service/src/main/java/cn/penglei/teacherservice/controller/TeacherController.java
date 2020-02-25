package cn.penglei.teacherservice.controller;

import cn.penglei.pojo.pojo.*;
import cn.penglei.teacherservice.service.TeacherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    /**
     * @ Description   :  提供者 获得我的课程信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:53
     */
    @RequestMapping("/findCourse")
    public List<Course> findCourse(@RequestParam("id") String id,
                                   @RequestParam("page")String page){
        return teacherService.findCourse(id,page);
    }

    /**
     * @ Description   :  提供者 删除课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:53
     */
    @RequestMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("tId") String tId,
                               @RequestParam("id") String id){
        return teacherService.deleteCourse(tId,id);
    }

    /**
     * @ Description   :  提供者 新增课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:54
     */
    @RequestMapping("/insertNewCourse")
    public String insertNewCourse(@RequestBody Course course){
        System.out.println(course);
        return teacherService.insertNewCourse(course);
    }
    /**
     * @ Description   :  提供者 找到我的学生
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:54
     */
    @RequestMapping("/findMyStudent")
    public List<SelectCourse> findMyStudent(@RequestBody PageHelp pageHelp){
        List<SelectCourse> myStudent = teacherService.findMyStudent(pageHelp);
        System.out.println(myStudent);
        return myStudent;
    }
    /**
     * @ Description   :  提供者 获得教师信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:54
     */
    @RequestMapping("/getTeacher")
    public User getTeacher(@RequestParam("id") String id){
        return teacherService.getTeacher(id);
    }
    /**
     * @ Description   :  提供者 获得公告信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:55
     */
    @RequestMapping("/getTip")
    public List<Tip> getTip(@RequestBody PageHelp pageHelp){
        return teacherService.getTip(pageHelp);
    }

    /**
     * @ Description   :  提供者 获得全部学生数量
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 16:24
     */
    @RequestMapping("/findCountStudent")
    public int findCountStudent(@RequestBody PageHelp pageHelp){
        pageHelp.setPage(0);
        pageHelp.setLimit(0);
        return teacherService.findMyStudent(pageHelp).size();
    }
    /**
     * @ Description   :  提供者 获得全部公告数量
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:02
     */
    @RequestMapping("/findCountTip")
    public int findCountTip(@RequestBody PageHelp pageHelp){
        pageHelp.setPage(0);
        pageHelp.setLimit(0);
        return teacherService.getTip(pageHelp).size();
    }

    /**
     * @ Description   :  提供者 修改密码
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:24
     */
    @RequestMapping("/updatePassword")
    public String updatePassword(@RequestParam("id") String tId,
                          @RequestParam("oldpwd") String oldpwd,
                          @RequestParam("newpwd") String newpwd){
        return teacherService.updatePassword(tId,oldpwd,newpwd);
    }
}
