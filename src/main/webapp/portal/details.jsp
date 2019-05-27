<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/19
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>微新闻</title>
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
            <a href="/index">最新</a>
            <c:forEach items="${requestScope.navigations}" var="navigation">

                <a href="/list?tid=${navigation.tid}" <c:if test="${requestScope.tid==navigation.tid}">class="active" </c:if> >${navigation.tName}</a>

            </c:forEach>
        </p>
        <%--<div class="search-bar">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="text" name="title"  placeholder="搜索你要的内容" autocomplete="off" class="layui-input">
                        <button class="layui-btn search-btn"  formnovalidate><i class="layui-icon layui-icon-search"></i></button>
                    </div>
                </div>
            </form>
        </div>--%>
    </div>
</div>


<div class="micronews-container micronews-details-container w1000">
    <div class="layui-fluid">
        <div class="layui-row">
            <div class="layui-col-xs12 layui-col-sm12 layui-col-md8">
                <div class="main">
                    <div class="title">
                        <h3>${news.title}</h3>
                        <div class="b-txt">
                            <span class="label">${news.tName}</span>
                            <span class="icon">
                  <i class="layui-icon layui-icon-radio"></i>
                  <b>${news.times}</b>人
                </span>
                            <a href="#message">
                <span class="icon message" >
                  <i class="layui-icon layui-icon-dialogue"></i>
                  <b>${news.times}</b>条
                </span>
                            </a>
                            <span class="icon time">
                  <i class="layui-icon layui-icon-log"></i>
                  ${news.releaseDate}
                </span>
                        </div>
                    </div>
                    <div class="article">
                        <p class="source">来源：<span>${news.uName}</span></p>
                        <p class="original-tit mt30">原标题：<span>${news.introduction}</span></p>
                        ${news.content}
                    </div>
                </div>
            </div>
            <div class="layui-col-xs12 layui-col-sm12 layui-col-md4">
                <div class="popular-info popular-info-tog">
                    <div class="layui-card">
                        <div class="layui-card-header">
                            <h3>资讯推荐</h3>
                        </div>
                        <div class="layui-card-body">
                            <ul class="list-box">
                                <c:forEach var="banner" items="${requestScope.bannerList}">
                                    <a href="/details?nid=${banner.nid}"><img  style="height: 100px ;width: 130px" src="${banner.banner}" > </a>
                                    <li class="list">
                                        <a href="/details?nid=${banner.nid}">${banner.title}</a></i>
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
<!--[if lt IE 9]>
<script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
<script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script>
    layui.config({
        base: '/portal/static/js/'
    }).use('index',function(){
        var index = layui.index,$ = layui.$;
        index.EnterMessage()
        index.Page('micronews-details-test',50)
        var collOff = true;
        $('.Collection').on('click',function(){
            if(collOff){
                $(this).addClass('active')
            }else{
                $(this).removeClass('active')
            }
            collOff = !collOff
        })
        index.seachBtn()
        index.onInput()
        index.arrowutil()
    });
</script>
</body>
</html>

</body>
</html>
