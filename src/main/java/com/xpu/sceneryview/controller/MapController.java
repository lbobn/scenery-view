package com.xpu.sceneryview.controller;

import com.xpu.sceneryview.entity.Marker;
import com.xpu.sceneryview.entity.Result;
import com.xpu.sceneryview.service.impl.MapServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MapController {

    @Autowired
    MapServiceImpl mapService;

    @GetMapping("/markers")
    public Result getMarkers() {
        log.info("访问接口"+"markers");
        List<Marker> markers = mapService.listMarkers();
        return Result.success(markers);
    }
}
