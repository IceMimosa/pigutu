<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>屁股图- 每日分享高清美女图片</title>
    <meta name="keywords" content="美女图片,性感美女,屁股图,pigutu,妹子图">
    <meta name="description" content="屁股图(www.pigutu.com)每日分享最好看的性感美女图片、高清美女写真，做最好的美女网站！">
    <meta name="mobile-agent" content="format=html5;url=http://m.pigutu.com"/>
    <link rel="alternate" media="only screen and(max-width: 640px)" href="http://m.pigutu.com">
    <link href="http://img.pigutu.com/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="http://img.pigutu.com/css/favicon.ico" rel="shortcut icon"/>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <meta name="baidu_union_verify" content="f8af49d45be255a12e7c02db6e132dda"/>
</head>

<body>
<div class="topbar">
    <div class="header"><h1>屁股图 - 每日分享高清美女图片</h1></div>
    <div class="nav"><a href="${url}">首页</a><a href="${url}hot/1" class="hot">浏览排行榜</a>
        <a href="${url}update/1" class="good">最新美图</a>
        <a href="${url}feedback" class="good">反馈</a>
        <i>手机看美女图片可通过m.pigutu.com访问本站</i>
    </div>
    <div class="subnav"><span>所有</span><#list categorys as category><a
            href="${url}beauty/${category.getParameter()}/1">${category.getTitle()}</a></#list></div>
</div>
<div class="main">
    <div class="pic">
        <ul>
        <#list imageSetLists as imageSetList>
            <li><a href="${url}image/${imageSetList.getId()?c}/1" target="_blank"><img
                    src="http://img.pigutu.com/img/${imageSetList.getCoverUrl()}/thumb"
                    width="220" height="330"
                    alt="${imageSetList.getTitle()}"/></a><span
                    class="title"><a href="${url}image/${imageSetList.getId()?c}/1"
                                     target="_blank">${imageSetList.getTitle()}</a></span><span
                    class="view">浏览(${(imageSetList.getViewCount()+35298)?c})</span></li></#list></ul>
        <div class="page"></div>
    </div>
    <div class="sidebar">
        <div class="search">
            <form name="formsearch" method="get" action="/search/1"><input name="key" type="text" id="key" value="搜妹子"
                                                                           onfocus="searchnow();"><span
                    onclick="searchpic();"></span></form>
        </div>
        <dl class="like" style="margin-top: 50px">
            <dt><h3>最新点赞</h3></dt>
        <#list likeRecords as likeRecord>
            <dd><a href="${url}image/${likeRecord.getAllImagesId()?c}" target="_blank"><img
                    src="http://img.pigutu.com/img/${likeRecord.getCoverUrl()}/like"
                    alt="${likeRecord.getTitle()}"/></a><span><a
                    href="${url}image/${likeRecord.getAllImagesId()?c}"
                    target="_blank">${likeRecord.getTitle()}</a></span></dd>
        </#list>
        </dl>
        <div id="SOHUCS" sid="index"  style="margin-top: 100px;float: right"></div>
    </div>

    <div style="clear: both;"></div>
</div>


</div>
</div>

<div class="friendlink">
    <dl>
        <dt><span>友情链接</span></dt>
        <dd><a href="http://www.pigutu.com">美女图片</a><a href="http://www.pigutu.com/beauty/%E6%98%8E%E6%98%9F/1"
                                                       target="_blank">明星美女</a><a
                href="http://www.pigutu.com/beauty/%E6%B8%85%E7%BA%AF/1" target="_blank">清纯美女</a><a
                href="http://www.pigutu.com/beauty/%E8%BF%90%E5%8A%A8/1"
                target="_blank">运动美女</a>
        </dd>
    </dl>
</div>
<div class="footer"><p><span>最好看的美女图片就在屁股图，记住我们的网址 pigutu.com<br/>Copyright &copy; 2017 屁股图 苏ICP备15000307号</span></p>
</div>
<div style="display:none;">
    <script type="text/javascript" src="http://img.pigutu.com/js/image.js"></script>
</div>
<script type="text/javascript">
    var obj = $('.page');
    var pageCount = ${pageCount?c};
    var pageIndex = ${pageIndex?c};
    var pageUrl = '${pageUrl}';
    var pageNum;
    var html = '';

    if (pageCount % 18 == 0) {
        pageNum = pageCount / 18;
    } else {
        pageNum = pageCount / 18 + 1;
    }
    pageNum = parseInt(pageNum);
    if (pageNum > 7) {
        for (var i = 1; i <= 7; i++) {
            if (pageIndex <= 3) {
                if (i == 1 && pageIndex != 1)
                    html += getPage(1, pageIndex - 1, '${key}');
                if (pageIndex == i) {
                    html += '<em>' + pageIndex + '</em>';
                } else {
                    html += getPage(2, i, '${key}');
                }
                if (i == 7)
                    html += getPage(3, (pageIndex + 1), '${key}');
            } else if (pageIndex >= pageNum - 3) {
                if (i == 1)
                    html += getPage(1, (pageIndex - 1), '${key}');
                if (pageIndex == i) {
                    html += '<em>' + pageIndex + '</em>';
                } else {
                    html += getPage(2, (pageIndex - i - 1), '${key}');
                }
                if (i == 7 && pageIndex != pageNum)
                    html += getPage(3, (pageIndex + 1), '${key}');
            } else {
                if (i == 1)
                    html += getPage(1, (pageIndex - 1), '${key}');
                if (4 == i) {
                    html += '<em>' + pageIndex + '</em>';
                } else {
                    html += getPage(2, (pageIndex + i - 4), '${key}');
                }
                if (i == 7)
                    html += getPage(3, (pageIndex + 1), '${key}');
            }
        }
    } else if (pageNum > 0) {
        for (var n = 1; n <= pageNum; n++) {
            if (n == 1 && pageIndex != 1) {
                html += getPage(1, (pageIndex - 1), '${key}');
            }
            if (pageIndex == n) {
                html += '<em>' + pageIndex + '</em>';
            } else {
                html += getPage(2, n, '${key}');
            }
            if (n == pageNum && pageIndex != pageNum) {
                html += getPage(3, (pageIndex + 1), '${key}');
            }
        }
    }

    function getPage(mode,page, key) {
        if (key.length==0) {
            switch (mode){
                case 1:
                    page = '<a href="'+pageUrl+'/' + page + '" class="ch">上一页</a>';
                    break;
                case 2:
                    page = '<a href="'+pageUrl+'/' + page + '">' + page + '</a>';
                    break;
                case 3:
                    page =  '<a href="'+pageUrl+'/' + (pageIndex + 1) + '" class="ch">下一页</a>';;
                    break;
            }
        } else {
           switch (mode){
               case 1:
                   page = '<a href="'+pageUrl+'/' + page + '?key='+key+'" class="ch">上一页</a>';
                   break;
               case 2:
                   page = '<a href="'+pageUrl+'/' + page + '?key='+key+'">' + page + '</a>';
                   break;
               case 3:
                   page = '<a href="'+pageUrl+'/' + page + '?key='+key+'" class="ch">下一页</a>';;
                   break;

           }
        }
        return page;
    }

    obj.html(html);</script>
    <script charset="utf-8" type="text/javascript" src="https://changyan.sohu.com/upload/changyan.js" ></script>
    <script type="text/javascript">
        window.changyan.api.config({
            appid: 'cytdA3M0s',
            conf: 'prod_960907fbd9d3d39b55dd4a1b65e04297'
        });
    </script>
</body>
</html>