package com.xpu.sceneryview.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @description
 * @Author lubb
 * @create 2024-03-31 14:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SceneryVo {
        private Integer id;
        private List<String> images;
        private String name;
        private String introduce;
        private String intro;
        private Integer like;
        private String address;
    }
