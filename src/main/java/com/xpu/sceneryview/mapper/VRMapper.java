package com.xpu.sceneryview.mapper;

import com.xpu.sceneryview.entity.VRInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VRMapper {
    @Select("select id,name,vr_url vrUrl ,vh_url vhUrl from vr_info where id = #{id}")
    VRInfo getVRInfo(Integer id);
}
