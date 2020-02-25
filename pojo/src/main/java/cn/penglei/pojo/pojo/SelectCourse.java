package cn.penglei.pojo.pojo;

import lombok.Data;

@Data
public class SelectCourse {
    private String id;
    private String studentId;
    private String courseId;
    private String selectTime;
    private String studentName;

}
