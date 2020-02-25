package cn.penglei.adminservice.Service.impl;

import cn.penglei.adminservice.Service.AdminService;
import cn.penglei.mapper.Mapper.CourseMapper;
import cn.penglei.mapper.Mapper.SelectCourseMapper;
import cn.penglei.mapper.Mapper.TipMapper;
import cn.penglei.mapper.Mapper.UserMapper;
import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;
import cn.penglei.pojo.pojo.Tip;
import cn.penglei.pojo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SelectCourseMapper selectCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private TipMapper tipMapper;
    @Override
    /**
     * @ Description   :  学生管理
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:57
     */
    public List<User> findAllStudent(PageHelp pageHelp) {
        List<User> students = userMapper.findAllUserByType(pageHelp);
        students.stream().forEach(student->{
            student.setPassword("");
            student.setNum(String.valueOf(selectCourseMapper.findMyCourse(student.getId()).size()));
        });
        return students;
    }
    /**
     * @ Description   :  教师管理
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:58
     */
    @Override
    public List<User> findAllTeacher(PageHelp pageHelp) {
        List<User> teachers = userMapper.findAllUserByType(pageHelp);
        teachers.stream().forEach(teacher->{
            teacher.setPassword("");
            teacher.setNum(String.valueOf(courseMapper.findCountCourseByTeacherId(teacher.getId())));
        });
        return teachers;
    }
    /**
     * @ Description   :  课程管理
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:58
     */
    @Override
    public List<Course> findAllCourse(PageHelp pageHelp) {
        List<Course> courses = courseMapper.findAllCourse(pageHelp);
        courses.stream()
                .forEach(course -> {
                    course.setUserName(userMapper.findStudent(String.valueOf(course.getTeacherId())).getUsername());
                    course.setSeat(String.valueOf(selectCourseMapper.findCountNum(String.valueOf(course.getId()))));
                });
        return courses;
    }
    /**
     * @ Description   :  公告管理
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:59
     */
    @Override
    public List<Tip> findAllTip(PageHelp pageHelp) {
        return tipMapper.findAllTip(pageHelp);
    }
    /**
     * @ Description   :  新增公告
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:59
     */
    @Override
    public void insertNewTip(Tip tip) {
        tip.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        tipMapper.insertNewTip(tip);
    }

    /**
     * @ Description   :  新增用户
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 22:33
     */
    @Override
    public String insertNewUser(User user) {
        int userCheck = userMapper.findUserCheck(user);
        if (userCheck>0){
            return "0";
        }else {
            userMapper.insertUser(user);
            return "1";
        }
    }
    /**
     * @ Description   :  重置密码
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 22:47
     */
    @Override
    public String resetPassword(String id) {
        userMapper.updatePassword(id,"123456");
        return "1";
    }
    /**
     * @ Description   :  修改姓名
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-24 15:15
     */
    @Override
    public void updateUserName(String id, String username) {
        userMapper.updateUserName(id,username);
    }

    @Override
    public String updatePassword(String tId, String oldpwd, String newpwd) {
        User user = userMapper.findStudent(tId);
        System.out.println(user);
        if (user.getPassword().equals(oldpwd)){
            userMapper.updatePassword(tId,newpwd);
            return "1";
        }else {
            return "0";
        }
    }
}
