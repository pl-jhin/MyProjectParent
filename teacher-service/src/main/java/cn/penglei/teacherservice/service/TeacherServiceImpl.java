package cn.penglei.teacherservice.service;

import cn.penglei.mapper.Mapper.CourseMapper;
import cn.penglei.mapper.Mapper.SelectCourseMapper;
import cn.penglei.mapper.Mapper.TipMapper;
import cn.penglei.mapper.Mapper.UserMapper;
import cn.penglei.pojo.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SelectCourseMapper selectCourseMapper;
    @Autowired
    private TipMapper tipMapper;
    /**
     * @ Description   :  找到我的课程信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:55
     */
    @Override
    public List<Course> findCourse(String id,String page) {
        List<Course> courses;
        if (!"".equals(page)) {
            String pageStart = String.valueOf((Integer.valueOf(page) - 1) * 5);
            courses = courseMapper.findCourseByTeacherId(id, pageStart, "5");
        }else courses = courseMapper.findCourseByTeacherId(id,"","");
        courses.stream()
                .forEach(course -> {
                    course.setUserName(userMapper.findStudent(String.valueOf(course.getTeacherId())).getUsername());
                    course.setSeat(String.valueOf(selectCourseMapper.findCountNum(String.valueOf(course.getId()))));
                });
        return courses;
    }

    /**
     * @ Description   :  删除课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:56
     */
    @Override
    public String deleteCourse(String tId, String id) {
        Integer integer = courseMapper.deleteCourseById(tId, id);
        if (integer>0){
            selectCourseMapper.deleteSelectByCid("",id);
            return "1";
        }else return "0";
    }
    /**
     * @ Description   :  新增课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:56
     */
    @Override
    public String insertNewCourse(Course course) {
        String check = courseMapper.checkCourse(course);
        System.out.println(check);
        if (check!=null){
            return "0";
        }else {
            courseMapper.insertNewCourse(course);
        }
        return "1";
    }
    /**
     * @ Description   :  找到我的学生
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:56
     */
    @Override
    public List<SelectCourse> findMyStudent(PageHelp pageHelp) {
        List<SelectCourse> userByCourseId = userMapper.findUserByTeacherId(pageHelp);
        userByCourseId.stream().forEach(user->{
            User student = userMapper.findStudent(user.getStudentId());
            user.setStudentName(student.getUsername());
            user.setStudentId(student.getUserNumber());
            user.setCourseId(courseMapper.findCourseById(user.getCourseId()).getName());
        });
        return userByCourseId;
    }

    /**
     * @ Description   :  修改密码
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 17:26
     */
    @Override
    public String updatePassword(String id, String oldpwd, String newpwd) {
        User user = userMapper.findStudent(id);
        System.out.println(user);
        if (user.getPassword().equals(oldpwd)){
            userMapper.updatePassword(id,newpwd);
            return "1";
        }else {
            return "0";
        }
    }

    /**
     * @ Description   :  获得个人信息
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:56
     */
    @Override
    public User getTeacher(String id) {

        return userMapper.findStudent(id);
    }
    /**
     * @ Description   :  获得公告
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 15:56
     */
    @Override
    public List<Tip> getTip(PageHelp pageHelp) {
        return tipMapper.findAllTip(pageHelp);
    }
}
