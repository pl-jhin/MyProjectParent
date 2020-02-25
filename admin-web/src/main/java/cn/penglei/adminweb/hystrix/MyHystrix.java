package cn.penglei.adminweb.hystrix;

import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;
import cn.penglei.pojo.pojo.Tip;
import cn.penglei.pojo.pojo.User;
import cn.penglei.adminweb.service.AdminService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyHystrix implements AdminService {

    @Override
    public List<User> findAllStudent(PageHelp pageHelp) {
        System.out.println("findAllStudent降级了");
        return null;
    }

    @Override
    public List<User> findAllTeacher(PageHelp pageHelp) {
        System.out.println("findAllTeacher降级了");
        return null;
    }

    @Override
    public List<Course> findAllCourse(PageHelp pageHelp) {
        System.out.println("findAllCourse降级了");
        return null;
    }

    @Override
    public List<Tip> findAllTip(PageHelp pageHelp) {
        System.out.println("findAllTip降级了");
        return null;
    }

    @Override
    public void insertNewTip(Tip tip) {
        System.out.println("insertNewTip降级了");
    }

    @Override
    public int findCountTip(PageHelp pageHelp) {
        System.out.println("findCountTip降级了");
        return 0;
    }

    @Override
    public int findCountStudent(PageHelp pageHelp) {
        System.out.println("findCountStudent降级了");
        return 0;
    }

    @Override
    public int findCountTeacher(PageHelp pageHelp) {
        System.out.println("findCountTeacher降级了");
        return 0;
    }

    @Override
    public int findCountCourse(PageHelp pageHelp) {
        System.out.println("findCountCount降级了");
        return 0;
    }

    @Override
    public String insertNewUser(User user) {
        System.out.println("insertNewUser降级了");
        return null;
    }

    @Override
    public String resetPassword(String id) {
        System.out.println("resetPassword降级了");
        return null;
    }

    @Override
    public String updateUserName(String id, String username) {
        System.out.println("updateUserName降级了");
        return null;
    }

    @Override
    public String updatePassword(String tId, String oldpwd, String newpwd) {
        System.out.println("updatePassword降级了");
        return null;
    }
}
