package cn.penglei.mapper.Mapper;

import cn.penglei.pojo.pojo.Course;
import cn.penglei.pojo.pojo.PageHelp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<Course> findAllCourse(@Param("page")PageHelp pageHelp);
    Integer findSeatById(@Param("id") String id);

    List<Course> findCourseBySort(@Param("sort") String sort);

    Integer findCountCourse();

    List<Course> findCourseByTeacherId(@Param("id") String id,@Param("pageStart")String pageStart,@Param("pageEnd")String pageEnd);

    Integer findCountCourseByTeacherId(@Param("id") String id);

    Integer deleteCourseById(@Param("tid")String tId, @Param("id")String id);

    void insertNewCourse(@Param("course") Course course);

    List<Course> findRecommendCourse();

    Course findCourseById(@Param("id") String id);

    String checkCourse(@Param("course") Course course);
}
