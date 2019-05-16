package cn.kejia.news.dao;




import cn.kejia.news.utils.DBUtil;

import java.sql.*;


public abstract class BaseDao {
    protected Connection conn = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;

    // 建立连接
    public Connection getConnection() {
        DBUtil configManger = DBUtil.getInstance();
        String Driver = configManger.getString("driver");
        String url = configManger.getString("url");
        String useName = configManger.getString("user");
        String passWord = configManger.getString("password");
        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(url, useName, passWord);
            conn.setAutoCommit(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到驱动！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败！");
        }
        return conn;
    }

    // 关闭连接
    public void closeConnection(Connection conn, ResultSet rs,
                                PreparedStatement pstmt) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 查询
    public ResultSet executeQuriy(String sql, Object[] params) {
        conn = getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 增删改
    public int executeUpdata(String sql, Object[] params) {
        conn = getConnection();
        int rows = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    // 增删改 返回主键
    public Integer executeUpdata(String sql, Object[] params, Boolean flg) {
        if (flg) {
            conn = getConnection();
            Integer generatedKey = null;
            try {
                pstmt = conn.prepareStatement(sql, new String[]{"DID"});
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        pstmt.setObject(i + 1, params[i]);
                    }
                }
                int rows = pstmt.executeUpdate();
                // 检索对象生成的键
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("数据主键：" + rs.getString(1));
                    generatedKey = Integer.parseInt(rs.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return generatedKey;
        }
        return null;
    }

    /**
     * 把数据库表转成对应实体类
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public abstract Object tableToObject(ResultSet rs) throws SQLException;

}

