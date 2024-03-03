package com.xpu.sceneryview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkerOriginal {
    private Integer id;
    private double latitude;
    private double longitude;
    private String title;
    private String icon_path;
    private String content;
    private String src_path;
    private String display;
    private String width;
    private String height;
}
