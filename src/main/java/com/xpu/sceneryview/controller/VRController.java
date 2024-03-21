package com.xpu.sceneryview.controller;

import com.xpu.sceneryview.entity.Result;
import com.xpu.sceneryview.entity.VRInfo;
import com.xpu.sceneryview.service.impl.VRServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class VRController {

    @Autowired
    VRServiceImpl vrService;

    @GetMapping("/vrpano/{id}")
    public Result getVRInfo(@PathVariable Integer id){
        log.info("id:"+id);
        VRInfo vrInfo = vrService.getVRInfo(id);
        return Result.success(vrInfo);
    }
}
