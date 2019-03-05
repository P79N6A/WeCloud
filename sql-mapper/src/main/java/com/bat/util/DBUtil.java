package com.bat.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Author: caoke
 * @Date: 2019/3/5 10:35
 * @Version 1.0
 */
public class DBUtil {
    public static final String URL;
    public static final String USERNAME;
    public static final String PASSWORD;
    public static final String DRIVER;
    public static Properties properties = new Properties();
    private DBUtil() {
    }

    static {
        InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(in);
        }catch (Exception e){
            e.printStackTrace();
        }
        URL = properties.getProperty("mysql.db.url");
        DRIVER = properties.getProperty("mysql.db.driver");
        USERNAME = properties.getProperty("mysql.db.username");
        PASSWORD = properties.getProperty("mysql.db.password");
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败");
        }
        return conn;
    }

    public static void close(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (stat != null)
                stat.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
