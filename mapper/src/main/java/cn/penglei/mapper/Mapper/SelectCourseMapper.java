package cn.penglei.mapper.Mapper;

import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.SelectCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SelectCourseMapper {
    // 查找课程
    String findCourseById(@Param("selectCourse") SelectCourse selectCourse);
    // 选择课程
    int insert(@Param("selectCourse") SelectCourse selectCourse);
    // 查找选课人数
    String findCountNum(@Param("id") String courseId);
    // 查找我的课程
    List<Course> findMyCourse(@Param("id")String id);
    // 删除选课信息
    void deleteSelectByCid(@Param("sid")String sid,@Param("id") String id);

    // 找到具体的选课信息
    Integer findSelectTrue(@Param("sid") String sid,@Param("id") String id);
}
