package cn.penglei.pojo.pojo;

import lombok.Data;

@Data
public class Course {
    private int id;
    private String sort;
    private int teacherId;
    private String name;
    private String studyTime;
    private String place;
    private String seat;
    private String submitTime;
    private String orginSeat;
    // 额外字段存入教师name
    private String userName;

    // 额外字段存入选课时间
    private String selectTime;
}
