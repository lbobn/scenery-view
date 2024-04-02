package com.xpu.sceneryview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description
 * @Author lubb
 * @create 2024-04-02 11:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentInfo {
    private Integer id;
    private String user_id;
    private String scenery_id;
    private String comment;
    private Integer like;
    private LocalDateTime create_time;
}
