package cn.kejia.news.utils;

import cn.kejia.news.model.ImageFiled;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/10
 * @Modified By：
 */
public class ImgResult {

    private Integer code;
    private String msg;
    private ImageFiled data;


    public static ImgResult ok(ImageFiled data) {
        return new ImgResult(0, "", data);
    }

    public static ImgResult failed(String msg) {
        return new ImgResult(1, msg, null);
    }

    public ImgResult(Integer code, String msg, ImageFiled data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ImgResult() {
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

    public ImageFiled getData() {
        return data;
    }

    public void setData(ImageFiled data) {
        this.data = data;
    }
}
