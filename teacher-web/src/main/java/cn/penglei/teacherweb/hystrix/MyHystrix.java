package cn.penglei.teacherweb.hystrix;

import cn.penglei.pojo.pojo.*;
import cn.penglei.teacherweb.service.TeacherService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyHystrix implements TeacherService {
    @Override
    public List<Course> findCourse(String id, String page) {
        System.out.println("findCourse降级了");
        return null;
    }


    @Override
    public String deleteCourse(String tId, String id) {
        System.out.println("deleteCourse降级了");
        return null;
    }

    @Override
    public String insertNewCourse(Course course) {
        System.out.println("insertNewCourse降级了");
        return null;
    }

    @Override
    public List<SelectCourse> findMyStudent(PageHelp pageHelp) {
        System.out.println("findMyStudent降级了");
        return null;
    }

    @Override
    public void updateUserName(String id,String username) {
        System.out.println("updateUserName降级了");
    }

    @Override
    public User getTeacher(String tId) {
        System.out.println("getTeacher降级了");
        return null;
    }

    @Override
    public List<Tip> getTip(PageHelp pageHelp) {
        System.out.println("getTip降级了");
        return null;
    }

    @Override
    public int findCountStudent(PageHelp pageHelp) {
        System.out.println("findCountTip降级了");
        return 0;
    }

    @Override
    public int findCountTip(PageHelp pageHelp) {
        return 0;
    }
}
