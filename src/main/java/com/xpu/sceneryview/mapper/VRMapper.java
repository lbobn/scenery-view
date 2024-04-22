package com.xpu.sceneryview.mapper;

import com.xpu.sceneryview.entity.BarrageInfo;
import com.xpu.sceneryview.entity.VRInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface VRMapper {
    @Select("select id,name,vr_url vrUrl ,vh_url vhUrl from vr_info where id = #{id}")
    VRInfo getVRInfo(Integer id);

    @Select("select * from barrage_info where scenery_id = #{id};")
    List<BarrageInfo> getBarrage(Integer id);

    @Insert("insert into barrage_info(user_id, scenery_id, barrage, create_time) " +
            "values (#{user_id},#{scenery_id},#{barrage},#{create_time})")
    void addBarrage(
            @Param("user_id") Integer userId,
            @Param("scenery_id") Integer sceneryId,
            @Param("barrage") String barrageInfo,
            @Param("create_time") LocalDateTime now);
}
