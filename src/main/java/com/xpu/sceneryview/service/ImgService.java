package com.xpu.sceneryview.service;


import com.xpu.sceneryview.entity.Image;
import com.xpu.sceneryview.entity.Swiper;

import java.util.List;

public interface ImgService {
    List<Image> getImages();

    List<Swiper> getSwiper();
}
