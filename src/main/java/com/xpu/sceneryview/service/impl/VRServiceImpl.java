package com.xpu.sceneryview.service.impl;

import com.xpu.sceneryview.entity.VRInfo;
import com.xpu.sceneryview.mapper.VRMapper;
import com.xpu.sceneryview.service.VRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VRServiceImpl implements VRService {
    @Value("${hdfs-img}")
    private boolean hdfsImg;
    @Value("${hdfs-vr-img}")
    private boolean usehdfsVrImg;
    @Value("${springboot-img-url}")
    private String springImgUrl;
    @Value("${hdfs-base-url}")
    private String HDFSBaseUrl;
    @Value("${start}")
    private String start;
    @Value("${end}")
    private String end;
    @Autowired
    VRMapper vrMapper;

    @Override
    public VRInfo getVRInfo(Integer id) {
        VRInfo vrInfo = vrMapper.getVRInfo(id);
        //设置Vr图片是否使用HDFS路径
        if(usehdfsVrImg){
            vrInfo.setVrUrl(HDFSBaseUrl + start + vrInfo.getVrUrl() + end);
        }else{
            vrInfo.setVrUrl(springImgUrl + vrInfo.getVrUrl());
        }


        // TODO 暂时使用HDFS,因为H5暂时还不能处理
        //设置虚拟人视频是否使用HDFS路径
        if (hdfsImg) {
            vrInfo.setVhUrl(HDFSBaseUrl + start + vrInfo.getVhUrl() + end);
        } else {
            vrInfo.setVhUrl(springImgUrl + vrInfo.getVhUrl());
        }
        return vrInfo;
    }
}
