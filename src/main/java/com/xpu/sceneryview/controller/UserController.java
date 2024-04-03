package com.xpu.sceneryview.controller;

import com.alibaba.fastjson.JSONObject;
import com.xpu.sceneryview.entity.Result;
import com.xpu.sceneryview.entity.User;
import com.xpu.sceneryview.entity.vo.UserVo;
import com.xpu.sceneryview.mapper.UserMapper;
import com.xpu.sceneryview.service.UserService;
import com.xpu.sceneryview.service.impl.UserServiceImpl;
import com.xpu.sceneryview.utils.JwtUtil;
import com.xpu.sceneryview.utils.WordCloudUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @Author lubb
 * @create 2024-04-01 15:16
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user) {
        Map<Integer, User> r = userService.login(user);
        if (r.containsKey(0)) {
            User user1 = r.get(0);
            String jsonString = JSONObject.toJSONString(user1);
            String token = JwtUtil.createToken(jsonString);
            String headImg = user1.getHead_img();
            UserVo userVo = new UserVo(user1.getId(), user.getUsername(), user1.getNickname(), null, headImg, token);
            return Result.success(userVo);
        } else if (r.containsKey(1)) {
            return Result.error("用户不存在,请注册");
        } else {
            return Result.error("密码错误");
        }
//        return r == 0 ? Result.success():Result.error("登录失败");
    }

    @PostMapping("register")
    public Result register(@RequestBody User user) {

        try {
            userService.register(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error("用户已存在");
        }
    }

    @PostMapping("comment/add")
    public Result commentAdd(@RequestHeader("token") String token, @RequestBody JSONObject comment) {

        String commentString = comment.getString("comment");
        System.out.println(token);
        System.out.println(comment);

        if (token == null || token.length() == 0) {
            return Result.error("未登录");
        } else if (commentString == null || commentString.length() == 0) {

            return Result.error("评论为空");
        } else {
            Claims claims = JwtUtil.parseToken(token);
//            System.out.println(claims.get("user"));
            userService.addComment(claims.get("user"), comment);
            return Result.success();
        }

    }

    @GetMapping("comment/wordcloud/{id}")
    public void getWordCloud(HttpServletResponse response, @PathVariable Integer id) {
        String comment = userService.getCommentBySceneryId(id);
        System.out.println(comment);
        int backCode = WordCloudUtil.generateWordCloud(comment);
        System.out.println(backCode);
        if (backCode == 0) {
//            return Result.success(comment);
            String file = "D:\\Test\\Java\\scenery-view\\src\\main\\resources\\static\\wordcloud\\word_cloud.jpg";
            try {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] data = new byte[inputStream.available()];
                inputStream.read(data);
                String diskfilename = "word_cloud.jpg";
                response.setContentType("image/png");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"");
                System.out.println("data.length " + data.length);
                response.setContentLength(data.length);
                response.setHeader("Content-Range", "" + Integer.valueOf(data.length - 1));
                response.setHeader("Accept-Ranges", "bytes");
                response.setHeader("Etag", "W/\"9767057-1323779115364\"");
                OutputStream os = response.getOutputStream();

                os.write(data);
                //先声明的流后关掉！
                os.flush();
                os.close();
                inputStream.close();

            } catch (Exception e) {
                e.printStackTrace();

            }
//            return Result.success();
            System.out.println("成功");
        } else {
//            return Result.error("生成失败");
            // 假设这里发生了一个错误
            String errorMessage = "失败";

            // 设置HTTP状态码为500 Internal Server Error
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            // 设置响应内容类型为JSON
            response.setContentType("application/json");

            // 获取PrintWriter对象来写入响应体
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // 构造一个简单的JSON错误对象
            String jsonError = "{\"error\": \"" + errorMessage + "\"}";

            // 写入JSON错误对象到响应体
            out.print(jsonError);

            // 刷新并关闭PrintWriter
            out.flush();
            out.close();
            System.out.println("失败");
        }
//        Result.

    }
}
