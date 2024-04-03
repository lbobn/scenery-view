package com.xpu.sceneryview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description
 * @Author lubb
 * @create 2024-04-03 20:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private Integer id;
    private String title;
    private String src;
    private String address;
}
