package com.xpu.sceneryview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marker {
    private Integer id;
    private double latitude;
    private double longitude;
    private String title;
    private String iconPath;
    private Map<String, Object> customCallout;
}
