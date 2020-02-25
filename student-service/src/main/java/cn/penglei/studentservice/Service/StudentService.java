package cn.penglei.studentservice.Service;

import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.SelectCourse;
import cn.penglei.pojo.pojo.User;

import java.util.List;

public interface StudentService {
    User findStudent(String id);


    List<Course> findMyCourse(String id);

    String updatePassword(String id, String oldpwd, String newpwd);

    String deleteSelect(String sId, String id);
}
