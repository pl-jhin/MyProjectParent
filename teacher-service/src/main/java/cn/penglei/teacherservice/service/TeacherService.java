package cn.penglei.teacherservice.service;

import cn.penglei.pojo.pojo.*;

import java.util.List;

public interface TeacherService {
    List<Course> findCourse(String id,String page);

    String deleteCourse(String tId, String id);

    String insertNewCourse(Course course);

    List<SelectCourse> findMyStudent(PageHelp pageHelp);


    String updatePassword(String id, String oldpwd ,String newpwd);

    User getTeacher(String id);

    List<Tip> getTip(PageHelp pageHelp);
}
