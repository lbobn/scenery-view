package com.xpu.sceneryview.mapper;

import com.xpu.sceneryview.entity.Marker;
import com.xpu.sceneryview.entity.MarkerOriginal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MapMapper {
    @Select("select * from marker")
    List<MarkerOriginal> listMarkers();
}
