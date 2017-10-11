<!DOCTYPE html>
<!-- saved from url=(0019)http://m.mmjpg.com/ -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="Cache-Control" content="no-siteapp">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="viewport"
          content="width=device-width,initial-scale=1, minimum-scale=1.0, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-title" content="屁股图">
    <title>屁股图 - 每日分享高清美女图片</title>
    <meta name="keywords" content="屁股图,美女图片,性感美女,mm">
    <meta name="description" content="屁股图(www.pigutu.com)每日分享最好看的性感美女图片、高清美女写真，做最好的美女网站！">
    <link href="http://img.pigutu.com/css/mstyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <link href="http://img.pigutu.com/css/favicon.ico" rel="shortcut icon"/>
</head>

<body>
<div id="header">
    <ul class="topbar">
        <li class="searchbtn"><span class="icon" onclick="viewsearch();"></span></li>
        <li class="logo"><a href="http://m.pigutu.com/">Pigutu</a></li>
        <li class="nav"><span class="icon" onclick="viewnav();"></span>
            <ul class="menu" id="menu">
                <li><i class="icon"></i><a href="${mUrl}index/1">首页</a></li>
                <li><i class="icon"></i><a href="${mUrl}hot/1">排行榜</a></li>
                <li><i class="icon"></i><a href="${mUrl}update/1">更新</a></li>
                <li><i class="icon"></i><a href="${mUrl}category">分类</a></li>
            </ul>
        </li>
    </ul>
    <div class="search" id="search">
        <form name="formsearch" id="formsearch" method="get" action="/search/1"><p><span><input
                type="text" name="key" id="key"><i class="icon" onclick="searchpic();"></i></span></p>
        </form>
    </div>
</div>
<div>
    <ul class="article" id="article">
    <#list imageSetLists as imageSetList>
        <li><h2><a href="${mUrl}image/${imageSetList.getId()?c}/1">${imageSetList.getTitle()}</a></h2>
            <div class="pic"><a href="${mUrl}image/${imageSetList.getId()?c}/1"><img
                    src="http://img.pigutu.com/img/${imageSetList.getCoverUrl()}/mindex"
                    alt="${imageSetList.getTitle()}"></a></div>
            <div class="info"><span>${imageSetList.getCreateTime()}</span><span
                    class="like">喜欢(${imageSetList.getLikeCount()})</span></div>
        </li>
    </#list>
    </ul>
</div>
<div class="page">

</div>
<div class="footer">
    <div class="tool"><span><a href="http://www.pigutu.com/">去电脑版</a></span><span class="gotop"><a
            href="#">返回顶部</a></span></div>
    <div class="copyright">Copyright © 2017 屁股图 pigutu.com</div>
</div>
<div id="topbtn" onclick="goscrolltop();"></div>
<script type="text/javascript" src="http://img.pigutu.com/js/mimage.js"></script>
<script type="text/javascript">
    var pageObj = $('.page');
    var html = '';
    var pageCount = ${pageCount?c};
    var pageIndex = ${pageIndex?c};
    var pageUrl = '${pageUrl}';
    var pageNum;

    if (pageCount % 18 == 0) {
        pageNum = pageCount / 18;
    } else {
        pageNum = pageCount / 18 + 1;
    }
    pageNum = parseInt(pageNum);
    if (pageIndex != 1 || pageNum != 1) {
        html = '<ul>';
        if (pageIndex == 1) {
            html += '<li class="pre">上一页</li><li>第${pageIndex?c}页</li><li class="next"><a href="${mPageUrl}/${(pageIndex+1)?c}">下一页</a></li>';
        } else if (pageIndex == pageNum) {
            html +='<li class="pre"><a href="${mPageUrl}/${(pageIndex-1)?c}">上一页</a></li><li>第${pageIndex?c}页</li><li class="next"><i>下一页</i></li>';
        }else{
            html+='<li class="pre"><a href="${mPageUrl}/${(pageIndex-1)?c}">上一页</a></li><li>第${pageIndex?c}页</li><li class="next"><a href="${mPageUrl}/${(pageIndex+1)?c}">下一页</a></li>';
        }
        html+='</ul>'
        pageObj.html(html);
    }
</script>
</body>
</html>