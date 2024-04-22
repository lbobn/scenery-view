package com.xpu.sceneryview.controller;

import com.alibaba.fastjson.JSONObject;
import com.xpu.sceneryview.entity.BarrageInfo;
import com.xpu.sceneryview.entity.Result;
import com.xpu.sceneryview.entity.VRInfo;
import com.xpu.sceneryview.service.impl.VRServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class VRController {

    @Autowired
    VRServiceImpl vrService;

//    @GetMapping("/vrpano/{id}")
//    public Result getVRInfo(@PathVariable Integer id){
//        log.info("id:"+id);
//        VRInfo vrInfo = vrService.getVRInfo(id);
//        return Result.success(vrInfo);
//    }

    @GetMapping("/vr/{id}")
    public Result getVRInfo(@PathVariable Integer id) {
        log.info("id:" + id);
        VRInfo vrInfo = vrService.getVRInfo(id);
        return Result.success(vrInfo);
    }

    @GetMapping("/barrage/{id}")
    public Result getBarrageById(@PathVariable Integer id){
        List<BarrageInfo> l = vrService.getBarrageById(id);
        return Result.success(l);

    }

    @PostMapping("barrage/add")
    public Result addBarrageById(@RequestBody JSONObject barrage){
        try {
            vrService.addBarrage(barrage);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("弹幕添加失败");
        }

    }
}
