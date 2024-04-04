package com.xpu.sceneryview.service;

import com.alibaba.fastjson.JSONObject;
import com.xpu.sceneryview.entity.Favor;
import com.xpu.sceneryview.entity.User;
import com.xpu.sceneryview.entity.vo.SceneryVo;

import java.util.List;
import java.util.Map;

/**
 * @description
 * @Author lubb
 * @create 2024-04-01 15:17
 */
public interface UserService {

    Map<Integer,User> login(User user);

    void register(User user) throws Exception;

    void addComment(Object user, JSONObject comment);

    String getCommentBySceneryId(Integer id);

    List<SceneryVo> favorListByUser(Object user);

    Integer isFavor(Object user, Integer sceneryId);

    void favorAdd(Object user, Integer id);

    void favorDel(Object user, Integer id);
}
