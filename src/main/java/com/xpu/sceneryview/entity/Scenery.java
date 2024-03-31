package com.xpu.sceneryview.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description
 * @Author lubb
 * @create 2024-03-30 17:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Scenery {
    private Integer id;
    private String images;
    private String name;
    private String introduce;
    private String intro;
    private Integer like;
    private String address;
}
