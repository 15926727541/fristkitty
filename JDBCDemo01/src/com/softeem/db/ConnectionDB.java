package com.softeem.db;

import com.mysql.jdbc.Driver;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionDB {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mytest20?useUnicode = true&characterEncoding=utf8";
        String username = "root";
        String password = "123456";
        try {
            //第一种加载jdbc驱动，一般都是使用第一种
            Class.forName("com.mysql.jdbc.Driver");

            //第二种加载jdbc驱动，很少用
            // DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接对象 = " + connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Test
    public void testConnection5() throws Exception {
        //1.加载配置文件
        InputStream is = ConnectionDB.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);
        //2.读取配置信息
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");
        //3.加载驱动
        Class.forName(driverClass);
        //4.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }
}