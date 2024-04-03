package com.xpu.sceneryview.service.impl;

import com.xpu.sceneryview.entity.Marker;
import com.xpu.sceneryview.entity.MarkerOriginal;
import com.xpu.sceneryview.mapper.MapMapper;
import com.xpu.sceneryview.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapServiceImpl implements MapService {
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
    MapMapper mapper;

    @Override
    public List<Marker> listMarkers() {
        //获取数据库原始数据
        List<MarkerOriginal> markers = mapper.listMarkers();
        List<Marker> res = new ArrayList<>();
        /*将数据转换为前端需要的格式*/

        if(hdfsImg){
            for (MarkerOriginal marker : markers) {
                Marker temp = new Marker();
                temp.setId(marker.getId());
                temp.setLatitude(marker.getLatitude());
                temp.setLongitude(marker.getLongitude());
                temp.setTitle(marker.getTitle());
                temp.setIconPath(HDFSBaseUrl + start + marker.getIcon_path() + end);
                temp.setHeight(marker.getHeight());
                temp.setWidth(marker.getWidth());
                Map<String, Object> map = new HashMap<>();
                map.put("content", marker.getContent());
                map.put("srcPath", HDFSBaseUrl + start + marker.getSrc_path() + end);
                map.put("display", marker.getDisplay());
                temp.setCustomCallout(map);
                res.add(temp);
            }
        }else{
            for (MarkerOriginal marker : markers) {
                Marker temp = new Marker();
                temp.setId(marker.getId());
                temp.setLatitude(marker.getLatitude());
                temp.setLongitude(marker.getLongitude());
                temp.setTitle(marker.getTitle());
                temp.setIconPath(springImgUrl + marker.getIcon_path());
                temp.setHeight(marker.getHeight());
                temp.setWidth(marker.getWidth());
                Map<String, Object> map = new HashMap<>();
                map.put("content", marker.getContent());
                map.put("srcPath", springImgUrl + marker.getSrc_path());
                map.put("display", marker.getDisplay());
                temp.setCustomCallout(map);
                res.add(temp);
            }
        }

        return res;
    }
}
