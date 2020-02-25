package cn.penglei.adminweb.service;

import cn.penglei.adminweb.hystrix.MyHystrix;
import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;
import cn.penglei.pojo.pojo.Tip;
import cn.penglei.pojo.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "AdminServer",fallback = MyHystrix.class)
public interface AdminService {
    @RequestMapping("/admin/findAllStudent")
    List<User> findAllStudent(@RequestBody PageHelp pageHelp);

    @RequestMapping("/admin/findAllTeacher")
    List<User> findAllTeacher(PageHelp pageHelp);

    @RequestMapping("/admin/findAllCourse")
    List<Course> findAllCourse(@RequestBody PageHelp pageHelp);

    @RequestMapping("/admin/findAllTip")
    List<Tip> findAllTip(@RequestBody PageHelp pageHelp);

    @RequestMapping("/admin/insertNewTip")
    void insertNewTip(@RequestBody Tip tip);

    @RequestMapping("/admin/findCountTip")
    int findCountTip(PageHelp pageHelp);
    @RequestMapping("/admin/findCountStudent")
    int findCountStudent(@RequestBody PageHelp pageHelp);
    @RequestMapping("/admin/findCountTeacher")
    int findCountTeacher(@RequestBody PageHelp pageHelp);
    @RequestMapping("/admin/findCountCourse")
    int findCountCourse(PageHelp pageHelp);
    @RequestMapping("/admin/insertNewUser")
    String insertNewUser(@RequestBody User user);

    @RequestMapping("/admin/resetPassword")
    String resetPassword(@RequestParam("id") String id);
    @RequestMapping("/admin/updateUserName")
    String updateUserName(@RequestParam("id") String id, @RequestParam("username") String username);
    @RequestMapping("/admin/updatePassword")
    String updatePassword(@RequestParam("id") String tId,
                          @RequestParam("oldpwd") String oldpwd,
                          @RequestParam("newpwd") String newpwd);
}
