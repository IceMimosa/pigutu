<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>${imageSetListEntity.getTitle()}_屁股图</title>
    <meta name="keywords" content="${imageSetListEntity.getLabel()}">
    <meta name="description" content="妹子图分享高清美女图片-${imageSetListEntity.getTitle()} www.pigutu.com">
    <link rel="alternate" media="only screen and(max-width: 640px)" href="http://m.pigutu.com/">
    <link href="http://img.pigutu.com/css/image.css" rel="stylesheet" type="text/css">
    <link href="http://img.pigutu.com/css/favicon.ico" rel="shortcut icon"/>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>
<div class="topbar">
    <div class="header"><h1>屁股图 - 每日分享高清美女图片</h1></div>
    <div class="nav"><a href="http://www.pigutu.com/">首页</a>
        <a href="http://www.pigutu.com/hot/1" class="hot">浏览排行榜</a>
        <a href="${url}update/1" class="good">最新美图</a>
        <a href="${url}feedback" class="good">反馈</a>
        <i>手机看美女图片可通过m.pigutu.com访问本站</i></div>
    <div class="subnav"><a href="http://www.pigutu.com/">所有</a><#list categorys as category><a
            href="/beauty/${category.getParameter()}/1">${category.getTitle()}</a></#list></div>
</div>
<div class="main">
    <div class="article">
        <h2>${imageSetListEntity.getTitle()}</h2>
        <div class="info"><i>发表于: ${imageSetListEntity.getCreateTime()}</i><i>来源:
            pigutu</i><i>人气(${(imageSetListEntity.getViewCount()+35298)?c})</i><i
                id="like">喜欢(${imageSetListEntity.getLikeCount()})</i><i class="like"
                                                                         onclick="likemm(${id?c},1);">给妹子点赞</i>
        </div>
        <div class="content" id="content"><#list imageSetLists as imageSet>
            <a href="/view?imageUrl=${imageSet.getUrl()}" target="_blank"><img
                    src="http://img.pigutu.com/img/${imageSet.getUrl()}/${style}"></a></#list></div>
        <div class="page"></div>
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
        <div class="hot">
            <dl id="hot" class="myrecommend">
                <dt>美图推荐</dt>
            </dl>
        </div>
        <div class="showmore"><span onclick="myrecommend();">换一批妹子图</span></div>

    </div>
    <div class="sidebar">
        <div class="search">
            <form name="formsearch" method="get" action="${url}search/1"><input name="key" type="text"
                                                                                id="key" value="搜妹子"
                                                                                onfocus="searchnow();"><span
                    onclick="searchpic();"></span></form>
        </div>
        <div class="baidu250" id="baidu250"></div>
        <div class="styleKey" style="display:none ">${style}</div>
        <dl class="like" style="margin-top: 50px">
            <dt><h3>最新点赞</h3></dt>
        <#list likeRecords as likeRecord>
            <dd><a href="${url}image/${likeRecord.getAllImagesId()?c}" target="_blank"><img src="http://img.pigutu.com/img/${likeRecord.getCoverUrl()}/like" alt="${likeRecord.getTitle()}"/></a><span><a
                    href="${url}image/${likeRecord.getAllImagesId()?c}" target="_blank">${likeRecord.getTitle()}</a></span></dd>
        </#list>
        </dl>
    </div>
    <div class="clearfloat">
        <script type="text/javascript">var picinfo = [2017, 870, 38];</script>
    </div>
</div>
<div class="footer"><p><span>最好看的美女图片就在屁股图，记住我们的网址 pigutu.com<br>Copyright © 屁股图 苏ICP备15000307号</span></p></div>
<div style="display:none;">
    <script type="text/javascript" src="http://img.pigutu.com/js/image.js"></script>
</div>
<script type="text/javascript">
    var myobj = $('.page');
    var pageCount = ${pageCount?c};
    var pageIndex = ${pageIndex?c};
    var pageNum;
    var html = '';

    if (pageCount % 6 == 0) {
        pageNum = pageCount / 6;
    } else {
        pageNum = pageCount / 6 + 1;
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
                    page = '<a href="'+'./' + page + '" class="ch">上一页</a>';
                    break;
                case 2:
                    page = '<a href="'+'./' + page + '">' + page + '</a>';
                    break;
                case 3:
                    page =  '<a href="'+'./' + (pageIndex + 1) + '" class="ch">下一页</a>';;
                    break;
            }
        } else {
            switch (mode){
                case 1:
                    page = '<a href="'+'./' + page + '?key='+key+'" class="ch">上一页</a>';
                    break;
                case 2:
                    page = '<a href="'+'./' + page + '?key='+key+'">' + page + '</a>';
                    break;
                case 3:
                    page = '<a href="'+'./' + page + '?key='+key+'" class="ch">下一页</a>';;
                    break;

            }
        }
        return page;
    }
    console.log(html+'aaa')
    myobj.html(html);


    myrecommend();

    //图片点赞
    function myrecommend() {
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest;
            if (xmlhttp.overrideMimeType) {
                xmlhttp.overrideMimeType('text/xml');
            }
        } else if (window.ActiveXObject) {
            xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
        }
        xmlhttp.onreadystatechange = recommendcallback;
        xmlhttp.open('GET', '/myrecommend?number=8', true);
        xmlhttp.send('');
    }

    function recommendcallback() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var renum = xmlhttp.responseText;
            var obj = $.parseJSON(renum);
            var html = '';
            for (var i = 0; i < 8; i++) {
                if (i == 0 || i == 4) {
                    html += '<dd class="left"><a href="${url}image/' + obj[i].id + '/1" target="_blank"><img src="http://img.pigutu.com/img/' + obj[i].coverUrl + '/thumb" width="182" height="277" alt="' + obj[i].title + '" />' + obj[i].title + '</a></dd>';
                } else {
                    html += '<dd><a href="${url}image/' + obj[i].id + '/1" target="_blank"><img src="http://img.pigutu.com/img/' + obj[i].coverUrl + '/thumb" width="182" height="277" alt="' + obj[i].title + '" />' + obj[i].title + '</a></dd>';
                }
            }
            html = '<dt>美图推荐</dt>' + html;
            $('.myrecommend').html(html);
        }
    }
</script>
<#--<script>
    var page = 0;
    var style = $(".styleKey").text();
    function loadData(){
        totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());
        if ($(document).height() <= totalheight+500) {  // 说明滚动条已达底部
            page++;
            /*这里使用Ajax加载更多内容*/
            var container = $("#content"); // 加载容器
            jQuery.ajax({
                type:"GET",
                url: "/imagewithpage/${id?c}/"+page,
                dataType: "json",
                beforeSend: function(XMLHttpRequest){
                }, success:function(response) {
                    console.log(response);
                    if(response.length>0){
                        for(var i=0, length = response.length; i<length; i++){
                            var pageHtml = '';
                            for(var i=0;i<response.length;i++){
                               pageHtml+='<a href="/view?imageUrl='+response[i].url+'" target="_blank"><img src="http://img.pigutu.com/img/'+response[i].url+'/'+style+'"></a>';
                           }
                            container.append(pageHtml);
                        }
     }
    }, error:function(){
        alert("加载失败");
    }
    });
    }
    }
    $(window).scroll( function() {
        loadData();
    });
    loadData();
</script>-->
</body>
</html>