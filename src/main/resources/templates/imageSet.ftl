<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0027)http://www.mmjpg.com/mm/870 -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>${imageSetListEntity.getTitle()}_屁股图</title>
    <meta name="keywords" content="秀人网,性感">
    <meta name="description" content="妹子图分享高清美女图片-${imageSetListEntity.getTitle()} www.pigutu.com">
    <link rel="alternate" media="only screen and(max-width: 640px)" href="http://m.mmjpg.com/mm/870">
    <link href="http://img.pigutu.com/css/image.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="topbar">
    <div class="header"><h1>屁股图 - 每日分享高清美女图片</h1></div>
    <div class="nav"><a href="http://www.pigutu.com/">首页</a><a href="http://www.pigutu.com/hot/1"
                                                               class="hot">浏览排行榜</a><a
            href="http://www.pigutu.com/recommend/1" class="good">推荐美图</a><a
            href="http://m.mmjpg.com/mm/870" class="mobile">手机版</a></div>
    <div class="subnav"><a href="http://www.pigutu.com/">所有</a><#list categorys as category><a
            href="/beauty/${category.getParameter()}/1">${category.getTitle()}</a></#list></div>
</div>
<div class="main">
    <div class="article">
        <h2>${imageSetListEntity.getTitle()}</h2>
        <div class="info"><i>发表于: ${imageSetListEntity.getCreate_time()}</i><i>来源:
            pigutu</i><i>人气(${(imageSetListEntity.getView_count()+35298)?c})</i><i
                id="like">喜欢(${imageSetListEntity.getLike_count()})</i><i class="like"
                                                                          onclick="likemm(${id},1);">给妹子点赞</i>
        </div>
        <div class="content" id="content"><#list imageSetLists as imageSet>
            <a href="/view?imageUrl=${imageSet.getUrl()}"><img
                    src="http://img.pigutu.com/img/${imageSet.getUrl()}/pigutu"></a></#list></div>
        <div class="other">
            <div class="tags">
            <#list imageSetListEntity.getLabel()?split(",") as label>
                <a href="${url}search/1?key=${label}">${label}</a>
            </#list>
            </div>
            <div class="share"><span>分享到:</span><i title="分享到QQ空间" onclick="sharemm(1);"></i><i title="分享到新浪微博"
                                                                                                onclick="sharemm(2);"></i>
            </div>
        </div>

    </div>
    <div class="sidebar">
        <div class="search">
            <form name="formsearch" method="get" action="${url}search/1"><input name="key" type="text"
                                                                                id="key" value="搜妹子"
                                                                                onfocus="searchnow();"><span
                    onclick="searchpic();"></span></form>
        </div>
        <div class="baidu250" id="baidu250"></div>
    </div>
    <div class="clearfloat">
        <script type="text/javascript">var picinfo = [2017, 870, 38];</script>
    </div>
</div>
<div class="footer"><p><span>最好看的美女图片就在屁股图，记住我们的网址 pigutu.com<br>Copyright © 2016 妹子图 湘ICP备1600号-3</span></p></div>
<div style="display:none;">
    <script type="text/javascript" src="http://img.pigutu.com/js/image.js"></script>
</div>

</body>
</html>