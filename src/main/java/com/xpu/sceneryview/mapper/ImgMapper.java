package com.xpu.sceneryview.mapper;

import com.xpu.sceneryview.entity.Image;
import com.xpu.sceneryview.entity.Swiper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImgMapper {

    @Select("select * from images")
    List<Image> list();

    @Select("select * from home_swiper")
    List<Swiper> listSwipers();
}
