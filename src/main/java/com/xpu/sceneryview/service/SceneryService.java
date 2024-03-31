package com.xpu.sceneryview.service;

import com.xpu.sceneryview.entity.Comment;
import com.xpu.sceneryview.entity.Scenery;
import com.xpu.sceneryview.entity.vo.SceneryVo;

import java.util.List;

/**
 * @description
 * @Author lubb
 * @create 2024-03-30 17:15
 */
public interface SceneryService {
    List<Comment> getCommentsBySceneryId(Integer id);

    SceneryVo getDetailInfo(Integer id);

    List<SceneryVo> getList();
}
