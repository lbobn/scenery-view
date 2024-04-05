package com.xpu.sceneryview.utils;

import java.sql.*;

/**
 * @description
 * @Author lubb
 * @create 2024-04-05 16:35
 */
public class JDBCUtils {
    // 数据库连接信息
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/senery";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "25gn3umb";

    // 静态代码块，用于加载数据库驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver.");
        }
    }

    // 获取数据库连接
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get database connection.");
        }
    }

    // 关闭数据库连接、Statement和ResultSet
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 执行查询操作
    public static ResultSet executeQuery(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            close(connection, preparedStatement, resultSet);
            throw new RuntimeException("Failed to execute query.");
        }
    }
}

