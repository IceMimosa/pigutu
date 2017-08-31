<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>屁股图- 每日分享高清美女图片</title>
    <meta name="keywords" content="美女图片,性感美女,妹子图,mm,pigutu">
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
    <script type="text/javascript" src="./PagingManage.js?version=2"></script>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>
<div class="topbar">
    <div class="header"><h1>屁股图 - 每日分享高清美女图片</h1></div>
    <div class="nav"><a href="http://www.pigutu.com">首页</a><a href="http://www.pigutu.com/hot/" class="hot">浏览排行榜</a><a
            href="http://www.mmjpg.com/top/" class="good">推荐美图</a><a href="http://www.pigutu.com/more/">标签</a><i>手机看美女图片可通过m.pigutu.com访问本站</i>
    </div>
    <div class="subnav"><span>所有</span><#list categorys as category><a
            href="/beauty/${category.getParameter()}/1">${category.getTitle()}</a></#list></div>
</div>
<div class="main">
    <div class="pic">
        <ul>
        <#list imageSetLists as imageSetList>
            <li><a href="http://www.pigutu.com:8080/image/${imageSetList.getId()?c}" target="_blank"><img src="http://hellohappy.oss-cn-shanghai.aliyuncs.com/img/${imageSetList.getCover_url()}"
                                                                            width="220" height="330"
                                                                            alt="${imageSetList.getTitle()}"/></a><span
                    class="title"><a href="http://www.mmjpg.com/mm/1082" target="_blank">${imageSetList.getTitle()}</a></span>
                <!--<span>${imageSetList.getCreate_time()} 发布</span>--><span
                        class="view">浏览(${imageSetList.getView_count()})</span></li></#list></ul>
    </div>
    <div style="clear: both;"></div>
    <div id="mypage" class="page"></div>
</div>
<div class="footer"><p><span>最好看的美女图片就在屁股图，记住我们的网址 pigutu.com<br/>Copyright &copy; 2016 屁股图 湘ICP备16007494号-3</span></p>
</div>
<div style="display:none;">
    <script type="text/javascript" src="images/mmjpg.js?151205"></script>
    <script type="text/javascript">
        $(function () {
            PagingManage($('#mypage'), 100, 10, 1);
        });

        /**
         * 响应动态生成的分页按钮的的点击事件
         * @param divId
         * @param page
         */
        function switchPage(divId, page) {

            alert('to do...');
        }
    </script>
</div>
</body>
</html>