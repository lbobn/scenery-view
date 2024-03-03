package com.xpu.sceneryview.service.impl;

import com.xpu.sceneryview.entity.VRInfo;
import com.xpu.sceneryview.mapper.VRMapper;
import com.xpu.sceneryview.service.VRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VRServiceImpl implements VRService {
    @Autowired
    VRMapper vrMapper;

    @Override
    public VRInfo getVRInfo(Integer id) {
        return vrMapper.getVRInfo(id);
    }
}
