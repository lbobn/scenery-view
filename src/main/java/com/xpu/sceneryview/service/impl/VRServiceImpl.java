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
    @Value("${start}")
    private String start;
    @Value("${end}")
    private String end;
    @Value("${base-img-url}")
    private String baseImgUrl;
    @Autowired
    VRMapper vrMapper;

    @Override
    public VRInfo getVRInfo(Integer id) {
        VRInfo vrInfo = vrMapper.getVRInfo(id);
        vrInfo.setVrUrl(vrInfo.getVrUrl());
        vrInfo.setVhUrl(baseImgUrl + start + vrInfo.getVhUrl() + end);
        return vrInfo;
    }
}
