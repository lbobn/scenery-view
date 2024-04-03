package com.xpu.sceneryview.service.impl;

import com.xpu.sceneryview.entity.Image;
import com.xpu.sceneryview.entity.Swiper;
import com.xpu.sceneryview.mapper.ImgMapper;
import com.xpu.sceneryview.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {
    @Value("${hdfs-img}")
    private boolean hdfsImg;
    @Value("${springboot-img-url}")
    private String springImgUrl;
    @Value("${hdfs-base-url}")
    private String HDFSBaseUrl;
    @Value("${start}")
    private String start;
    @Value("${end}")
    private String end;
    @Autowired
    ImgMapper imgMapper;

    @Override
    public List<Image> getImages() {
        List<Image> imgs = imgMapper.list();
        if (hdfsImg) {
            //使用HDFS图片路径
            for (Image img : imgs) {
                img.setSrc(HDFSBaseUrl + start + img.getSrc() + end);
            }
        } else {
            for (Image img : imgs) {
                img.setSrc(springImgUrl + img.getSrc());
            }
        }
        return imgs;
    }

    @Override
    public List<Swiper> getSwiper() {
        List<Swiper> swipers = imgMapper.listSwipers();
        if (hdfsImg) {
            //使用HDFS图片路径
            for (Swiper swiper : swipers) {
                swiper.setSrc(HDFSBaseUrl + start + swiper.getSrc() + end);
            }
        } else {
            for (Swiper swiper : swipers) {
                swiper.setSrc(springImgUrl + swiper.getSrc());
            }
        }
        return swipers;
    }
}
