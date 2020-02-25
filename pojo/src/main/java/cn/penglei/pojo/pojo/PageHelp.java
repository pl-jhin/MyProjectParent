package cn.penglei.pojo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageHelp {
    private String id;
    private int page;
    private int limit;
    private String username;
    private String name;
    private String idType;
}
