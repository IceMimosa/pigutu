<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>屁股图- 每日分享高清美女图片</title>
    <meta name="keywords" content="美女图片,性感美女,妹子图,pigutu">
    <meta name="description" content="屁股图(www.pigutu.com)每日分享最好看的性感美女图片、高清美女写真，做最好的美女网站！">
    <meta name="mobile-agent" content="format=html5;url=http://m.pigutu.com"/>
    <link rel="alternate" media="only screen and(max-width: 640px)" href="http://m.pigutu.com">
    <link href="http://hellohappy.oss-cn-shanghai.aliyuncs.com/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var ubr = navigator.userAgent.toLowerCase();
        if (ubr.indexOf('mobile') > -1) {
            top.location.href = 'http://m.pigutu.com';
        }
    </script>
    <script type="text/javascript"
            src="http://hellohappy.oss-cn-shanghai.aliyuncs.com/js/PagingManage.js?version=3"></script>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>
<div class="topbar">
    <div class="header"><h1>屁股图 - 每日分享高清美女图片</h1></div>
    <div class="nav"><a href="${url}">首页</a><a href="${url}hot/1" class="hot">浏览排行榜</a><a
            href="${url}update/1" class="good">最新美图</a><i>手机看美女图片可通过m.pigutu.com访问本站</i>
    </div>
    <div class="subnav"><span>所有</span><#list categorys as category><a
            href="${url}beauty/${category.getParameter()}/1">${category.getTitle()}</a></#list></div>
</div>
<div class="main">
    <div class="pic">
        <ul>
        <#list imageSetLists as imageSetList>
            <li><a href="${url}image/${imageSetList.getId()?c}" target="_blank"><img
                    src="http://hellohappy.oss-cn-shanghai.aliyuncs.com/img/${imageSetList.getCoverUrl()}/thumb"
                    width="220" height="330"
                    alt="${imageSetList.getTitle()}"/></a><span
                    class="title"><a href="${url}image/${imageSetList.getId()?c}"
                                     target="_blank">${imageSetList.getTitle()}</a></span><span
                    class="view">浏览(${imageSetList.getViewCount()})</span></li></#list></ul>
        <div class="page"></div>
    </div>
    <div class="sidebar">
        <div class="search">
            <form name="formsearch" method="get" action="/search/1"><input name="key" type="text" id="key" value="搜索"
                                                                           onfocus="searchnow();"><span
                    onclick="searchpic();"></span></form>
        </div>
    </div>
    <div style="clear: both;"></div>
</div>


</div>

<div class="footer"><p><span>最好看的美女图片就在屁股图，记住我们的网址 pigutu.com<br/>Copyright &copy; 2016 屁股图 湘ICP3</span></p>
</div>
<div style="display:none;">
    <script type="text/javascript" src="http://img.pigutu.com/js/image.js"></script>
</div>
<script type="text/javascript">
    var obj = $('.page');
    var pageCount = ${pageCount?c};
    var pageIndex = ${pageIndex?c};
    var pageUrl= ${pageUrl};
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
                    html += getPage(1,pageIndex - 1,'${key}');
                if (pageIndex == i) {
                    html += '<em>' + pageIndex + '</em>';
                } else {
                    html += getPage(2,i,'${key}');
                }
                if (i == 7)
                    html += getPage(3,(pageIndex + 1) ,'${key}');
            } else if (pageIndex >= pageNum - 3) {
                if (i == 1)
                    html += getPage(1,(pageIndex - 1),'${key}');
                if (pageIndex == i) {
                    html += '<em>' + pageIndex + '</em>';
                } else {
                    html += getPage(2,(pageIndex - i - 1) ,'${key}');
                }
                if (i == 7 && pageIndex != pageNum)
                    html += getPage(3,(pageIndex + 1) ,'${key}');
            } else {
                if (i == 1)
                    html += getPage(1,(pageIndex - 1),'${key}');
                if (4 == i) {
                    html += '<em>' + pageIndex + '</em>';
                } else {
                    html += getPage(2,(pageIndex + i - 4),'${key}');
                }
                if (i == 7)
                    html +=getPage(3,(pageIndex + 1),'${key}');
            }
        }
    } else if (pageNum > 0) {
        for (var n = 1; n <= pageNum; n++) {
            if (n == 1 && pageIndex != 1) {
                html += getPage(1,(pageIndex - 1) ,'${key}');
            }
            if (pageIndex == n) {
                html += '<em>' + pageIndex + '</em>';
            } else {
                html += getPage(2,n,'${key}');
            }
            if (n == pageNum && pageIndex != pageNum) {
                html += getPage(3,(pageIndex + 1),'${key}');
            }
        }
    }

    function getPage(mode,page, key) {
        if (key === null) {
            switch (mode){
                case 1:
                    page = '<a href="/" class="ch">上一页<'+pageUrl+'/' + page + 'a>';
                    break;
                case 2:
                    page = ''+pageUrl+'<a href="/">' + page + '' + page + '</a>';
                    break;
                case 3:
                    page =  '<a href="/" class="ch">下一页<'+pageUrl+'/' + (pageIndex + 1) + 'a>';;
                    break;
            }
        } else {
           switch (mode){
               case 1:
                   page = '<a href="/?key=" class="ch">上一页<'+pageUrl+'/' + page + ''+key+'a>';
                   break;
               case 2:
                   page = ''+pageUrl+'' + page + '<a href="/?key=">'+key+'' + page + '</a>';
                   break;
               case 3:
                   page = '<a href="/?key=" class="ch">下一页<'+pageUrl+'/' + page + ''+key+'a>';;
                   break;

           }
        }
        return page;
    }

    obj.html(html);
</script>
</body>
</html>