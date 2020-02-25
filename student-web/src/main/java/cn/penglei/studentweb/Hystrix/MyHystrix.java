package cn.penglei.studentweb.Hystrix;

import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.SelectCourse;
import cn.penglei.pojo.pojo.User;
import cn.penglei.studentweb.Service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyHystrix implements StudentService {

    @Override
    public List<Course> findMyCourse(String id) {
        System.out.println("findMyCourse降级了");
        return null;
    }

    @Override
    public User getStudent(String id) {
        System.out.println("getStudent降级了");
        return null;
    }

    @Override
    public String updatePassword(String tId, String oldpwd, String newpwd) {
        System.out.println("updatePassword降级了");
        return null;
    }

    @Override
    public String deleteSelect(String tId, String id) {
        System.out.println("deleteSelect降级了");
        return null;
    }
}
