package cn.kejia.news.model;

import java.util.Date;
import java.util.Locale;

/**
 * 新闻类
 *
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/09
 * @Modified By：
 */
public class News {

    private Long nid;//新闻编号
    private Long uid;//用户编号
    private String title;//新闻标题
    private String banner;//新闻轮播图
    private Locale times;//浏览次数
    private String content;//内容
    private Date releaseDate;//发布时间
    private Long tid;//新闻分类

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Locale getTimes() {
        return times;
    }

    public void setTimes(Locale times) {
        this.times = times;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }
}
