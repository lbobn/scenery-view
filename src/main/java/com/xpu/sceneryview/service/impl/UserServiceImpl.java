package com.xpu.sceneryview.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xpu.sceneryview.entity.CommentInfo;
import com.xpu.sceneryview.entity.Favor;
import com.xpu.sceneryview.entity.Scenery;
import com.xpu.sceneryview.entity.User;
import com.xpu.sceneryview.entity.vo.SceneryVo;
import com.xpu.sceneryview.mapper.SceneryMapper;
import com.xpu.sceneryview.mapper.UserMapper;
import com.xpu.sceneryview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @Author lubb
 * @create 2024-04-01 15:19
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${hdfs-img}")
    private boolean hdfsImg;
    @Value("${springboot-img-url}")
    private String springImgUrl;
    @Value("${hdfs-base-url}")
    private String HDFSBaseUrl;
    @Value("${start}")
    private String start;
    @Value("${end}")
    private String end;
    @Autowired
    UserMapper userMapper;
    @Autowired
    SceneryMapper sceneryMapper;

    /**
     * @param user
     * @return 0:成功，1:用户不存在，2:密码错误
     */
    @Override
    public Map<Integer, User> login(User user) {
        User u = userMapper.getUserByUsername(user.getUsername());
        if(hdfsImg){
            u.setHead_img(HDFSBaseUrl + start + u.getHead_img() + end);
        }else{
            u.setHead_img(springImgUrl+ u.getHead_img());
        }

        Map<Integer, User> r = new HashMap<>();
//        System.out.println("前端对象"+user.toString());
//        System.out.println("数据库"+ u);
        if (u == null) {
            r.put(1, null);
            return r;
        } else {
            if (u.getPassword().equals(user.getPassword())) {
                r.put(0, u);
            } else {
                r.put(2, null);
            }
            return r;
        }
    }

    @Override
    public void register(User user) throws Exception {
        userMapper.insertUser(user.getUsername(), user.getPassword(), user.getNickname());
    }

    @Override
    public void addComment(Object user, JSONObject comment) {
        User u = JSONObject.parseObject(user.toString(), User.class);

        userMapper.insertComment(u.getId(), comment.getString("scenery_id"), comment.getString("comment"), 0, LocalDateTime.now());
    }

    @Override
    public String getCommentBySceneryId(Integer id) {
        StringBuilder sb = new StringBuilder();
        List<CommentInfo> l = userMapper.getCommentBySceneryId(id);
        for (CommentInfo commentInfo : l) {
            sb.append(commentInfo.getComment());
        }
        return sb.toString();
    }

    @Override
    public List<SceneryVo> favorListByUser(Object user) {
        User u = JSONObject.parseObject(user.toString(), User.class);
//        List<Favor> l = userMapper.getFavorListByUserId(u.getId());
//        return l;
        List<Scenery> sceneries = sceneryMapper.listFavorScenery(u.getId());
        List<SceneryVo> result = new ArrayList<>();
        List<String> imgs;
        if (hdfsImg) {
            for (Scenery scenery : sceneries) {
                imgs = new ArrayList<>();
                String[] images = scenery.getImages().split("#");
                for (String image : images) {
                    //添加协议头
                    imgs.add(HDFSBaseUrl + start + image + end);
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
        } else {
            for (Scenery scenery : sceneries) {
                imgs = new ArrayList<>();
                String[] images = scenery.getImages().split("#");
                for (String image : images) {
                    //添加协议头
                    imgs.add(springImgUrl + image);
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
            }
        }
        return result;
    }

    @Override
    public Integer isFavor(Object user, Integer sceneryId) {

        User u = JSONObject.parseObject(user.toString(), User.class);
        Integer count = userMapper.isFavor(u.getId(),sceneryId);
        return count;
    }

    @Override
    public void favorAdd(Object user, Integer id) {
        User u = JSONObject.parseObject(user.toString(), User.class);
        userMapper.insertFavor(u.getId(),id,LocalDateTime.now());
    }

    @Override
    public void favorDel(Object user, Integer id) {
        User u = JSONObject.parseObject(user.toString(), User.class);
        userMapper.deleteFavor(u.getId(),id);
    }
}
