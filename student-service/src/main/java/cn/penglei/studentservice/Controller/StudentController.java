package cn.penglei.studentservice.Controller;

import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.SelectCourse;
import cn.penglei.pojo.pojo.User;
import cn.penglei.studentservice.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    /**
     * @ Description   :  提供者 获得学生信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:56
     */
    @RequestMapping("/getStudent")
    public User loginCheck(@RequestParam("id")String id){
        return studentService.findStudent(id);

    }
    /**
     * @ Description   :  提供者 退选课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:15
     */
    @RequestMapping("/deleteSelect")
    public String deleteSelect(@RequestParam("sId") String sId,
                               @RequestParam("id") String id){
        return studentService.deleteSelect(sId,id);
    }
    /**
     * @ Description   :  提供者 获得我的选课信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:57
     */
    @RequestMapping("/findMyCourse")
    public List<Course> findMyCourse(@RequestParam("id")String id){
        return studentService.findMyCourse(id);
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
        return studentService.updatePassword(tId,oldpwd,newpwd);
    }
}

