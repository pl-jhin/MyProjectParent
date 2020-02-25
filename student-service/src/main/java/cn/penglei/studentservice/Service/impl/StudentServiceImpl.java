package cn.penglei.studentservice.Service.impl;

import cn.penglei.mapper.Mapper.SelectCourseMapper;
import cn.penglei.mapper.Mapper.UserMapper;
import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.SelectCourse;
import cn.penglei.pojo.pojo.User;
import cn.penglei.studentservice.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    SelectCourseMapper selectCourseMapper;

    /**
     * @ Description   :  获得学生信息，根据id
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:16
     */
    @Override
    public User findStudent(String id) {
        User student = userMapper.findStudent(id);
        student.setPassword("");
        return student;
    }
    /**
     * @ Description   :  获得我的课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:17
     */
    @Override
    public List<Course> findMyCourse(String id) {
        List<Course> myCourse = selectCourseMapper.findMyCourse(id);
        myCourse.stream()
                .forEach(course -> {
                    course.setUserName(userMapper.findStudent(String.valueOf(course.getTeacherId())).getUsername());
                });
        return myCourse;
    }
    /**
     * @ Description   :  修改密码
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:17
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
     * @ Description   :  删除已选课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 19:17
     */
    @Override
    public String deleteSelect(String sid, String id) {
        selectCourseMapper.deleteSelectByCid(sid,id);
        return "1";
    }
}
