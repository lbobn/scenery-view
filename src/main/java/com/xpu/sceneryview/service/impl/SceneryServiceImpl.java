package com.xpu.sceneryview.service.impl;

import com.xpu.sceneryview.entity.Comment;
import com.xpu.sceneryview.entity.Scenery;
import com.xpu.sceneryview.entity.vo.SceneryVo;
import com.xpu.sceneryview.mapper.SceneryMapper;
import com.xpu.sceneryview.service.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description
 * @Author lubb
 * @create 2024-03-30 17:19
 */
@Service
public class SceneryServiceImpl implements SceneryService {
    @Value("${start}")
    private String start;
    @Value("${end}")
    private String end;
    @Value("${base-img-url}")
    private String baseImgUrl;

    @Autowired
    SceneryMapper sceneryMapper;

    @Override
    public List<Comment> getCommentsBySceneryId(Integer id) {
        return sceneryMapper.getCommentsBySceneryId(id);
    }

    @Override
    public SceneryVo getDetailInfo(Integer id) {
        Scenery detail = sceneryMapper.getDetail(id);
        String[] split = detail.getImages().split("#");
        List<String> imgs = new ArrayList<>();
        for (String s : split) {
            imgs.add(baseImgUrl + start + s + end);
        }
        SceneryVo sceneryVo = new SceneryVo(
                detail.getId(),
                imgs,
                detail.getName(),
                detail.getIntroduce(),
                detail.getIntro(),
                detail.getLike(),
                detail.getAddress()
        );
        return sceneryVo;
    }

    @Override
    public List<SceneryVo> getList() {

        List<Scenery> sceneries = sceneryMapper.listScenery();
        List<SceneryVo> result = new ArrayList<>();
        List<String> imgs;
        for (Scenery scenery : sceneries) {
            imgs = new ArrayList<>();
            String[] images = scenery.getImages().split("#");
            for (String image : images) {
                //添加协议头
                imgs.add(baseImgUrl + start + image + end);
            }
            result.add(new SceneryVo(
                    scenery.getId(),
                    imgs,
                    scenery.getName(),
                    scenery.getIntroduce(),
                    scenery.getIntro(),
                    scenery.getLike(),
                    scenery.getAddress()
            ));
//            System.out.println(result);
        }
        return result;
    }
}
