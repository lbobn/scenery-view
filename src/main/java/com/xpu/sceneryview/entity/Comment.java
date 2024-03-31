package com.xpu.sceneryview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description
 * @Author lubb
 * @create 2024-03-30 17:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private String username;
    private String scenery_name;

    private String comment;
    private Integer like;
    private String create_time;
}
