package cn.kejia.news.model;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/10
 * @Modified By：
 */
public class ImageFiled {

    private String src;
    private String originalName;
    private String newName;

    public ImageFiled(String src, String originalName, String newName) {
        this.src = src;
        this.originalName = originalName;
        this.newName = newName;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
