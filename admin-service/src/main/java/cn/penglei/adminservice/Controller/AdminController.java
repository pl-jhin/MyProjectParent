package cn.penglei.adminservice.Controller;

import cn.penglei.adminservice.AdminServiceApplication;
import cn.penglei.adminservice.Service.AdminService;
import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;
import cn.penglei.pojo.pojo.Tip;
import cn.penglei.pojo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    /**
     * @ Description   :  学生管理
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:52
     */
    @RequestMapping("/findAllStudent")
    public List<User> findAllStudent(@RequestBody PageHelp pageHelp){
        pageHelp.setIdType("0");
        return adminService.findAllStudent(pageHelp);
    }
    /**
     * @ Description   :  获得学生总条数
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 20:25
     */
    @RequestMapping("/findCountStudent")
    public int findCountStudent(@RequestBody PageHelp pageHelp){
        pageHelp.setPage(0);
        pageHelp.setLimit(0);
        pageHelp.setIdType("0");
        return adminService.findAllStudent(pageHelp).size();
    }
    /**
     * @ Description   :  获得教师
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:52
     */
    @RequestMapping("/findAllTeacher")
    public List<User> findAllTeacher(@RequestBody PageHelp pageHelp){
        pageHelp.setIdType(("1"));
        return adminService.findAllTeacher(pageHelp);
    }
    /**
     * @ Description   :  获得教师总条数
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 20:26
     */
    @RequestMapping("/findCountTeacher")
    public int findCountTeacher(@RequestBody PageHelp pageHelp){
        pageHelp.setPage(0);
        pageHelp.setLimit(0);
        pageHelp.setIdType(("1"));
        return adminService.findAllStudent(pageHelp).size();
    }
    /**
     * @ Description   :  获得课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:52
     */
    @RequestMapping("/findAllCourse")
    public List<Course> findAllCourse(@RequestBody PageHelp pageHelp){
        return adminService.findAllCourse(pageHelp);
    }
    /**
     * @ Description   :  获得课程条数
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 20:46
     */
    @RequestMapping("/findCountCourse")
    public int findCountCourse(@RequestBody PageHelp pageHelp){
        pageHelp.setPage(0);
        pageHelp.setLimit(0);
        return adminService.findAllCourse(pageHelp).size();
    }

    /**
     * @ Description   :  获得公告
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:52
     */
    @RequestMapping("/findAllTip")
    public List<Tip> findAllTip(@RequestBody PageHelp pageHelp){
        return adminService.findAllTip(pageHelp);
    }
    /**
     * @ Description   :  新增公告
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:53
     */
    @RequestMapping("/insertNewTip")
    public void insertNewTip(@RequestBody Tip tip){
        adminService.insertNewTip(tip);
    }
    /**
     * @ Description   :  获得公告条数
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:53
     */
    @RequestMapping("/findCountTip")
    public int findCountTip(@RequestBody PageHelp pageHelp){
        pageHelp.setPage(0);
        pageHelp.setLimit(0);
        return adminService.findAllTip(pageHelp).size();
    }

    /**
     * @ Description   :  新增用户
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 22:32
     */
    @RequestMapping("/insertNewUser")
    public String insertNewUser(@RequestBody User user){
        return adminService.insertNewUser(user);
    }
    /**
     * @ Description   :  重置密码
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 22:45
     */
    @RequestMapping("/resetPassword")
    public String resetPassword(@RequestParam("id") String id){
        return adminService.resetPassword(id);
    }
    /**
     * @ Description   :  提供者 修改姓名
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-24 15:14
     */
    @RequestMapping("/updateUserName")
    public String updateUserName(@RequestParam("id") String id, @RequestParam("username") String username){
        adminService.updateUserName(id,username);
        return "1";
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
        return adminService.updatePassword(tId,oldpwd,newpwd);
    }

}

