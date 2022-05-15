package com.ynuni.stuMS.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/stuMS?characterEncoding=utf-8";
    private static final String USERNAME = "syc";
    private static final String PASSWORD = "syc8326190";
    Connection con = null;
    ResultSet res = null;

    //获取Connection
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public int AddU(String sql, String str[]) {
        Connection con;
        int a = 0;
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement pst = con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            a = pst.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }

    // 查询
    public ResultSet Search(String sql, String str[]) {
        Connection con;
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement pst =con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            res = pst.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //判断stuId是否存在
    public static boolean isExist(String stuId){
        try {
            DBUtil dbUtil=new DBUtil();
            ResultSet rs=dbUtil.Search("select * from students where stuId=?", new String[]{stuId+""});
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //关闭资源
    public static void close(ResultSet rs,PreparedStatement pst,Connection con) {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(pst!=null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pst = null;
        }
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con = null;
        }
    }
}
