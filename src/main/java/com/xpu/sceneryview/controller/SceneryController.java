package com.xpu.sceneryview.controller;

import com.xpu.sceneryview.entity.Comment;
import com.xpu.sceneryview.entity.Result;
import com.xpu.sceneryview.entity.Scenery;
import com.xpu.sceneryview.entity.vo.SceneryVo;
import com.xpu.sceneryview.service.SceneryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description
 * @Author lubb
 * @create 2024-03-30 17:11
 */
@Slf4j
@RestController
public class SceneryController {
    @Autowired
    SceneryService sceneryService;
    @GetMapping("/detail/{id}")
    public Result detailInfo(@PathVariable Integer id){
        SceneryVo s = sceneryService.getDetailInfo(id);
//        s.setImages(new String[]{
//                "http://localhost:8080/image/swiper1.png",
//                "http://localhost:8080/image/swiper2.png"
//        });
        return Result.success(s);
    }

    @GetMapping("/detail/comments/{id}")
    public Result sceneryComments(@PathVariable Integer id){
        List<Comment> comments = sceneryService.getCommentsBySceneryId(id);
        return Result.success(comments);
    }

    @GetMapping("/scenery/list")
    public Result listScenery(){
        List<SceneryVo> l =  sceneryService.getList();
        return Result.success(l);
    }
}
