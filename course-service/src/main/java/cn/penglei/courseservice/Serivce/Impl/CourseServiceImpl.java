package cn.penglei.courseservice.Serivce.Impl;

import cn.penglei.courseservice.Serivce.CourseService;
import cn.penglei.lock.ZookeeperLock;
import cn.penglei.mapper.Mapper.CourseMapper;
import cn.penglei.mapper.Mapper.SelectCourseMapper;
import cn.penglei.mapper.Mapper.UserMapper;
import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;
import cn.penglei.pojo.pojo.SelectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SelectCourseMapper selectCourseMapper;

    // 分布式锁
    private ZookeeperLock lock = new ZookeeperLock();
    @Override
    public List<Course> findAllCourse(PageHelp pageHelp) {
        pageHelp.setLimit(12);
        List<Course> courses = courseMapper.findAllCourse(pageHelp);
        System.out.println(courses);
        courses.stream()
                .forEach(course -> {
                    course.setUserName(userMapper.findStudent(String.valueOf(course.getTeacherId())).getUsername());
                    course.setSeat(String.valueOf(selectCourseMapper.findCountNum(String.valueOf(course.getId()))));
                });
        return courses;
    }
    /**
     * @ Description   :  根据分类查找课程
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-25 16:00
     */
    @Override
    public List<Course> findCourseBySort(String sort) {
        // 根据分类找到课程
        List<Course> courses = courseMapper.findCourseBySort(sort);
        // stream流遍历
        courses.stream()
                .forEach(course -> {
                    course.setUserName(userMapper.findStudent(String.valueOf(course.getTeacherId())).getUsername());
                    course.setSeat(String.valueOf(selectCourseMapper.findCountNum(String.valueOf(course.getId()))));
                });
        return courses;
    }

    @Override
    public Integer findCountCourse() {
        return courseMapper.findCountCourse();
    }

    @Override
    public List<Course> findRecommendCourse() {
        List<Course> courses = courseMapper.findRecommendCourse();
        courses.stream()
                .forEach(course -> {
                    course.setUserName(userMapper.findStudent(String.valueOf(course.getTeacherId())).getUsername());
                    course.setSeat(String.valueOf(selectCourseMapper.findCountNum(String.valueOf(course.getId()))));

                });
        return courses;
    }

    @Override
    public Course findCourseById(String id) {
        Course course = courseMapper.findCourseById(id);
        course.setUserName(userMapper.findStudent(String.valueOf(course.getTeacherId())).getUsername());
        course.setSeat(String.valueOf(selectCourseMapper.findCountNum(String.valueOf(course.getId()))));
        return course;
    }
    /**
     * @ Description   :  选课
     * @ Author        :  Adios
     * @ CreateDate    :  2020-02-23 18:20
     */
    @Override
    public String selectCourse(String sid, String id) {
        // 课程已选
        Integer b = selectCourseMapper.findSelectTrue(sid,id);
        if (b!=null){
            return "3";
        } else {
            lock.setLockName("course"+id);
            lock.lock();
            try {
                Integer orginSeat = courseMapper.findSeatById(id);
                Integer num = Integer.valueOf(selectCourseMapper.findCountNum(id));
                // 课程可选
                if (orginSeat - num > 0) {
                    SelectCourse selectCourse = new SelectCourse();
                    selectCourse.setStudentId(sid);
                    selectCourse.setCourseId(id);
                    selectCourse.setSelectTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    selectCourseMapper.insert(selectCourse);
                    return "4";
                } else {
                    return "5";
                }
            }catch (Exception e){
                return "5";
            }finally {
                lock.unlock();
            }
        }

    }
}
