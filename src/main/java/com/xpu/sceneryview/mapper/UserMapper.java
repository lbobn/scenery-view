package com.xpu.sceneryview.mapper;

import com.xpu.sceneryview.entity.CommentInfo;
import com.xpu.sceneryview.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description
 * @Author lubb
 * @create 2024-04-01 15:20
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);

    @Insert("insert into `user`(`username`, `password`, `nickname`) values(#{username},#{password},#{nickname})")
    void insertUser(@Param("username") String username,
                    @Param("password") String password,
                    @Param("nickname") String nickname);

    @Insert("insert into " +
            "`comment_info`(`user_id`,`scenery_id`,`comment`,`like`,`create_time`) " +
            "values(#{user_id},#{scenery_id},#{comment},#{like},#{create_time})")
    void insertComment(@Param("user_id") Integer user_id,
                       @Param("scenery_id") String scenery_id,
                       @Param("comment") String comment,
                       @Param("like") int like,
                       @Param("create_time") LocalDateTime create_time);

    @Select("select * from comment_info where scenery_id = #{id}")
    List<CommentInfo> getCommentBySceneryId(Integer id);
}
