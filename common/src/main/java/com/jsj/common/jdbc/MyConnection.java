package com.jsj.common.jdbc;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Created by jinshouji on 2018/4/3.
 */
public class MyConnection {
    private Connection connect = null;

    //构造函数
    public MyConnection(Connection connect)
    {
        this.connect=connect;
    }
    public MyConnection(){}


    /**
     * 根据连接的信息设置数据库连接
     * @param db_driver
     * @param db_url
     * @param db_username
     * @param db_password
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void setConnection(String db_driver,
                              String db_url,String db_username,String db_password)
            throws SQLException, ClassNotFoundException, IOException {

            //当前数据库连接不为空，强行释放
            this.closeConnect();

            //重新根据连接信息建立连接
            Class.forName(db_driver);
            this.connect = DriverManager.getConnection(db_url,db_username,db_password);
    }

    public Connection getConnect() {
        return connect;
    }

    /**
     * 设置当前的数据库连接
     * @param connect
     * @throws SQLException
     */
    public void setConnect(Connection connect)  throws SQLException {
        if(null!=null) {
            this.closeConnect();
            this.connect = connect;
        }
    }

    /**
     * 根据sql语句返回List的Map结果
     * @param sql
     * @return
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public List<Map<String, Object>> getQueryMapList(String sql) throws SQLException,
            InstantiationException, IllegalAccessException {
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        Statement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = this.connect.createStatement();
            rs = preStmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (null != rs && rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < columnCount; i++) {
                    String name = rsmd.getColumnName(i + 1);
                    Object value = rs.getObject(name);
                    map.put(name, value);
                }
                lists.add(map);
            }
        } finally {
            if (null != rs)
                rs.close();
            if (null != preStmt)
                preStmt.close();
        }
        return lists;
    }

    /**
     * 根据sql语句和参数返回List的Map结果
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public List<Map<String, Object>> getQueryMapList(String sql, Object... params)
            throws SQLException, InstantiationException, IllegalAccessException {
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            preStmt = this.connect.prepareStatement(sql);
            for (int i = 0; i < params.length; i++)
                preStmt.setObject(i + 1, params[i]);// 下标从1开始
            rs = preStmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (null != rs && rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < columnCount; i++) {
                    String name = rsmd.getColumnName(i + 1);
                    Object value = rs.getObject(name);
                    map.put(name, value);
                }
                lists.add(map);
            }
        } finally {
            if (null != rs)
                rs.close();
            if (null != preStmt)
                preStmt.close();
        }
        return lists;
    }

    /**
     * 关闭当前的连接
     * @throws SQLException
     */
    public  void closeConnect() throws SQLException {
        try {
            if (null != this.connect)
                this.connect.close();
        } finally {
            this.connect= null;
            System.gc();
        }
    }
}
