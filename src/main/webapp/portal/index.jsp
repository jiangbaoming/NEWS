<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/19
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新闻</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/statics/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/portal/static/css/main.css">
</head>
<body class="micronews">
<div class="micronews-header-wrap">
    <div class="micronews-header w1000 layui-clear">
        <h1 class="logo">
            <a href="/index">
                <img src="/portal/static/images/LOGO.png" alt="logo">
                <span class="layui-hide">LOGO</span>
            </a>
        </h1>
        <p class="nav">
            <a href="/index" class="active">最新</a>
            <c:forEach items="${requestScope.newsTypes}" var="newsType">
                <a href="/list?tid=${newsType.tid}">${newsType.tName}</a>
            </c:forEach>
        </p>
        <div class="search-bar">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="text" name="title" placeholder="搜索你要的内容" autocomplete="off" class="layui-input">
                        <button class="layui-btn search-btn"  formnovalidate><i class="layui-icon layui-icon-search"></i></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="layui-fulid micronews-banner w1000">
    <div class="layui-carousel imgbox" id="micronews-carouse">
        <div carousel-item>
            <c:forEach var="banner" items="${requestScope.bannerList}">
                <div>
                    <p class="title">${banner.title}</p>
                    <a href="/list"><img style="height: 300px; width: 1000px"  src="${banner.banner}"></a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<div class="micronews-container w1000">
    <div class="layui-fluid">
        <div class="layui-row">
            <div class="layui-col-xs12 layui-col-sm12 layui-col-md8">
                <div class="main">
                    <div class="list-item" id="LAY_demo2">
                        <c:forEach items="${requestScope.bannerList}" var="banner">
                            <div class="item">
                                <a href="/details?nid=${banner.nid}">
                                    <img style="width: 160px; height: 100px" src="${banner.banner}">
                                </a>
                                <div class="item-info">
                                    <h4><a href="/details?nid=${banner.nid}">${banner.introduction}</a></h4>
                                    <div class="b-txt">
                                        <span class="label">${banner.tName}</span>
                                        <span class="icon message"><i class="layui-icon layui-icon-dialogue"></i>${banner.times}</span>
                                        <span class="icon time"><i class="layui-icon layui-icon-log"></i>${banner.releaseDate}</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="layui-col-xs12 layui-col-sm12 layui-col-md4">
                <div class="popular-info">
                    <div class="layui-card">
                        <div class="layui-card-header">
                            <h3>热门资讯</h3>
                        </div>
                        <div class="layui-card-body">
                            <ul class="list-box">
                                <c:forEach var="banner" items="${requestScope.bannerList}">
                                    <li class="list">
                                        <a href="list.html">${banner.title}</a><i class="heat-icon"></i>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="micronews-footer-wrap">
    <div class="micronews-footer w1000">
        <div class="ft-nav">
            <a href="#">关于我们</a>
            <a href="#">合作伙伴</a>
            <a href="#">广告服务</a>
            <a href="#">常见问题</a>
        </div>
        <div class="Copyright">
            <span>Copyright&nbsp;</span>&nbsp;&copy;<span>新闻网&nbsp;&nbsp;</span><span>赣ICP备12345678号</span>
        </div>
        <div class="f-icon">
            <a href="#" class="w-icon">
                <img src="/portal/static/images/wechat_ic.png">
            </a>
            <a href="#" class="wb-icon">
                <img src="/portal/static/images/qq_ic.png">
            </a>
        </div>
    </div>
</div>

<script type="text/javascript" src="/statics/layui/layui.js"></script>

<script>
    layui.config({
        base: '/portal/static/js/'
    }).use('index',function(){
        var index = layui.index;
        index.banner()
        index.seachBtn()
        index.arrowutil()
    });
</script>
<!--[if lt IE 9]>
<script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
<script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</body>
</html>