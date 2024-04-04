package com.xpu.sceneryview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description
 * @Author lubb
 * @create 2024-04-04 11:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favor {
    private Integer id;
    private String user_id;
    private String scenery_id;
    private LocalDateTime create_time;
}
