package cn.kejia.news.model;


import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/09
 * @Modified By：
 */
public class NewsType {

    private Integer tid;//新闻分类ID
    private String tName;//分类名称
    private List<News> newsList;//该分类下的所有新闻

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
