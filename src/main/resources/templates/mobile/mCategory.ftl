<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="Cache-Control" content="no-siteapp">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="viewport"
          content="width=device-width,initial-scale=1, minimum-scale=1.0, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-title" content="屁股图">
    <title>Pigutu - 每日分享高清美女图片</title>
    <meta name="keywords" content="屁股图,美女图片,性感美女,pigutu,妹子图">
    <meta name="description" content="屁股图(www.pigutu.com)每日分享最好看的性感美女图片、高清美女写真，做最好的美女网站！">
    <link href="http://img.pigutu.com/css/mstyle.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="header">
    <ul class="topbar">
        <li class="searchbtn"><span class="icon" onclick="viewsearch();"></span></li>
        <li class="logo"><a href="http://m.pigutu.com/">Pigutu</a></li>
        <li class="nav"><span class="icon" onclick="viewnav();"></span>
            <ul class="menu" id="menu">
                <li><i class="icon"></i><a href="http://m.pigutu.com/index/1">首页</a></li>
                <li><i class="icon"></i><a href="http://m.pigutu.com/hot/1">排行榜</a></li>
                <li><i class="icon"></i><a href="http://m.pigutu.com/update/1">更新</a></li>
                <li><i class="icon"></i><a href="http://m.pigutu.com/category">分类</a></li>
            </ul>
        </li>
    </ul>
    <div class="search" id="search">
        <form name="formsearch" id="formsearch" method="get" action="/search/1"><p><span><input
                type="text" name="searchkey" id="searchkey"><i class="icon" onclick="searchpic();"></i></span></p>
        </form>
    </div>
</div>
<div>
    <ul class="article" id="article">
    <#list categorys as category>
        <li><h2><a href="${mUrl}beauty/${category.getParameter()}/1">${category.getTitle()}</a></h2>
            <div class="pic"><a href="${mUrl}beauty/${category.getParameter()}/1"><img
                    src="http://img.pigutu.com/${category.getPath()}/mindex"
                    alt="${category.getTitle()}"></a></div>
        </li>
    </#list>
    </ul>
</div>
<div class="footer">
    <div class="tool"><span><a href="http://www.pigutu.com/">去电脑版</a></span><span class="gotop"><a
            href="http://m.pigutu.com/#">返回顶部</a></span></div>
    <div class="copyright">Copyright © 2017 屁股图 pigutu.com</div>
</div>
<div id="topbtn" onclick="goscrolltop();"></div>
<script type="text/javascript" src="http://img.pigutu.com/js/mimage.js"></script>

</body>
</html>