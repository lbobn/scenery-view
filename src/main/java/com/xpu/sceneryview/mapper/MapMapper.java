package com.xpu.sceneryview.mapper;

import com.xpu.sceneryview.entity.Marker;
import com.xpu.sceneryview.entity.MarkerOriginal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MapMapper {
    /*@Select("select * from marker")
    List<MarkerOriginal> listMarkers();*/

    @Select("SELECT id,latitude,longitude,title," +
            "CONCAT('http://127.0.0.1:8080/',icon_path) icon_path,content," +
            "CONCAT('http://127.0.0.1:8080/',src_path) src_path," +
            "display,width,height " +
            "from marker\n")
    List<MarkerOriginal> listMarkers();
}
