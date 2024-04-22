package com.xpu.sceneryview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description
 * @Author lubb
 * @create 2024-04-19 10:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarrageInfo {
    private Integer id;
    private Integer user_id;
    private Integer scenery_id;
    private String barrage;
    private LocalDateTime create_time;

}
