package cn.penglei.pojo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Json {
    private int code;
    private int count;
    private String msg;
    private Object data;

}
