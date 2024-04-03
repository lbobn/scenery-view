package com.xpu.sceneryview.controller;

import com.xpu.sceneryview.entity.Image;
import com.xpu.sceneryview.entity.Result;
import com.xpu.sceneryview.entity.Swiper;
import com.xpu.sceneryview.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImgController {
    @Autowired
    ImgService imgService;

    @GetMapping("/get")
    public String test(){
        return "success";
    }

    @GetMapping("images")
    public Result listImages(){
        List<Image> images =  imgService.getImages();
        return Result.success(images);
    }

    @GetMapping("swiper")
    public Result getSwiper(){
        List<Swiper> swiper =  imgService.getSwiper();
        return Result.success(swiper);
    }

}
