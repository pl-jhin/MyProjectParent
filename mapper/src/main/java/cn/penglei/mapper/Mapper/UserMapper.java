package cn.penglei.mapper.Mapper;

import cn.penglei.pojo.pojo.PageHelp;
import cn.penglei.pojo.pojo.SelectCourse;
import cn.penglei.pojo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User loginCheck(@Param("user") User user);
    User findStudent(@Param("id") String id);

    int findUserCheck(@Param("user") User user);

    void insertUser(@Param("user") User user);

    List<SelectCourse> findUserByTeacherId(@Param("page") PageHelp pageHelp);

    void updatePassword(@Param("id") String id,@Param("password") String password);

    List<User> findAllUserByType(@Param("page") PageHelp pageHelp);

    void updateUserName(@Param("id") String id, @Param("username") String username);
}
