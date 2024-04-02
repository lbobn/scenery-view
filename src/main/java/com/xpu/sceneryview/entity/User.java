package com.xpu.sceneryview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description
 * @Author lubb
 * @create 2024-04-01 15:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String head_img;
}
