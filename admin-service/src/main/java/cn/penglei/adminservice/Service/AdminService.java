package cn.penglei.adminservice.Service;

import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;
import cn.penglei.pojo.pojo.Tip;
import cn.penglei.pojo.pojo.User;

import java.util.List;

public interface AdminService {
    List<User> findAllStudent(PageHelp pageHelp);

    List<User> findAllTeacher(PageHelp pageHelp);

    List<Course> findAllCourse(PageHelp pageHelp);

    List<Tip> findAllTip(PageHelp pageHelp);

    void insertNewTip(Tip tip);

    String insertNewUser(User user);

    String resetPassword(String id);

    void updateUserName(String id, String username);

    String updatePassword(String tId, String oldpwd, String newpwd);
}
