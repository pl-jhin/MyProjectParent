package cn.penglei.mapper.Mapper;

import cn.penglei.pojo.pojo.PageHelp;
import cn.penglei.pojo.pojo.Tip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TipMapper {
    List<Tip> findAllTip(@Param("page")PageHelp pageHelp);

    void insertNewTip(@Param("tip") Tip tip);
}
