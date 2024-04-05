package com.xpu.sceneryview.mapper;

import com.xpu.sceneryview.entity.Comment;
import com.xpu.sceneryview.entity.Scenery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description
 * @Author lubb
 * @create 2024-03-30 17:20
 */
@Mapper
public interface SceneryMapper {

    @Select("select\n" +
            "    c.id id,\n" +
            "    u.nickname nickname,\n" +
            "    s.name scenery_name,\n" +
            "    c.comment comment,\n" +
            "    c.`like` `like`,\n" +
            "    c.create_time create_time\n" +
            "from comment_info c\n" +
            "join user u on c.user_id = u.id\n" +
            "join scenery s on c.scenery_id = s.id\n" +
            "where c.scenery_id = #{sceneryId}")
    List<Comment> getCommentsBySceneryId(Integer sceneryId);

    @Select("select * from scenery where id = #{id}")
    Scenery getDetail(Integer id);

    @Select("select * from scenery")
    List<Scenery> listScenery();

    @Select("SELECT * from scenery\n" +
            " where id in (\n" +
            "\t\tSELECT scenery_id id\n" +
            "\t\tFROM favor\n" +
            "\t\twhere user_id = #{id}\n" +
            ")")
    List<Scenery> listFavorScenery(Integer id);
}
