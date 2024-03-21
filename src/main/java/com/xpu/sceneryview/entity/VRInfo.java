package com.xpu.sceneryview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VRInfo {
    private Integer id;
    private String name;
    private String vrUrl;
    private String vhUrl;
}
