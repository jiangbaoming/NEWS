package cn.kejia.news.utils;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/09
 * @Modified By：
 */
public class NewsResult {
    private Integer code;//状态码
    private String msg;//描述信息
    private Object data;//数据

    //成功无数据
    public static NewsResult success() {
        return new NewsResult(null);
    }

    //成功带数据
    public static NewsResult success(Object data) {
        return new NewsResult(data);
    }

    //自定义带数据
    public static NewsResult build(Integer code, String msg, Object data) {
        return new NewsResult(code, msg, data);
    }

    //自定义不带数据
    public static NewsResult build(Integer code, String msg) {
        return new NewsResult(code, msg, null);
    }

    public NewsResult(Object data) {
        this.code=200;
        this.msg="ok";
        this.data = data;
    }

    public NewsResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
