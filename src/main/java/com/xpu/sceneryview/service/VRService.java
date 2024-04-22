package com.xpu.sceneryview.service;


import com.alibaba.fastjson.JSONObject;
import com.xpu.sceneryview.entity.BarrageInfo;
import com.xpu.sceneryview.entity.VRInfo;

import java.util.ArrayList;
import java.util.List;

public interface VRService {
    VRInfo getVRInfo(Integer id);

    List<BarrageInfo> getBarrageById(Integer id);

    void addBarrage(JSONObject barrage);
}
