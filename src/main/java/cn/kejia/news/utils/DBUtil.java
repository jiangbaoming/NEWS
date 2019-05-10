package cn.kejia.news.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/04/29
 * @Modified By：
 */
public class DBUtil {
    private static Properties properties;
    private static DBUtil dBUtil;

    private DBUtil() {
        // IO流
        InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("database.properties");

        properties=new Properties();
        try {
            properties.load(is);
            is.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static DBUtil getInstance() {
        if (dBUtil == null) {
            dBUtil = new DBUtil();
        }
        return dBUtil;
    }

    public String getString(String key) {

        return properties.getProperty(key);

    }

}
