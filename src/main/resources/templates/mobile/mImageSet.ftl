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
    <title>屁股图 - 每日分享高清美女图片</title>
    <meta name="keywords" content="${imageSetListEntity.getLabel()}">
    <meta name="description" content="屁股图(www.pigutu.com)每日分享最好看的性感美女图片、高清美女写真，做最好的美女网站！">
    <link href="//img.pigutu.com/css/mstyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <link href="//img.pigutu.com/css/favicon.ico" rel="shortcut icon"/>
</head>

<body>
<div id="header">
    <ul class="topbar">
        <li class="searchbtn"><span class="icon" onclick="viewsearch();"></span></li>
        <li class="logo"><a href="//m.pigutu.com/">Pigutu</a></li>
        <li class="nav"><span class="icon" onclick="viewnav();"></span>
            <ul class="menu" id="menu">
                <li><i class="icon"></i><a href="//m.pigutu.com/">首页</a></li>
                <li><i class="icon"></i><a href="//m.pigutu.com/hot/1">排行榜</a></li>
                <li><i class="icon"></i><a href="//m.pigutu.com/update/1">更新</a></li>
                <li><i class="icon"></i><a href="//m.pigutu.com/category">分类</a></li>
            </ul>
        </li>
    </ul>
    <div class="search" id="search">
        <form name="formsearch" id="formsearch" method="get" action="/search/1"><p><span><input
                type="text" name="key" id="key"><i class="icon" onclick="searchpic();"></i></span></p>
        </form>
    </div>
</div>
<#--<div>
    <ul class="article" id="article">
    <#list imageSetLists as imageSetList>
        <li>
            <div class="pic"><img src="http://img.pigutu.com/img/${imageSetList.getUrl()}/pigutu"></div>
        </li>
    </#list>
    </ul>
</div>-->
<div class="content">
    <h1>${imageSetListEntity.getTitle()}</h1>
    <div class="picinfo"><#--<i>${imageSetListEntity.getCreateTime()} 发布</i>--><i>浏览(${(imageSetListEntity.getViewCount()+35298)?c})</i><i id="love">喜欢(${imageSetListEntity.getLikeCount()})</i></div>
<#list imageSetLists as imageSetList>
    <li id="content">
    <#--    <div class="pic"><img src="http://img.pigutu.com/img/${imageSetList.getUrl()}/pigutu"></div>-->
        <div><img src="//img.pigutu.com/img/${imageSetList.getUrl()}/${style}" alt="${imageSetListEntity.getTitle()}" /></div>
    </li>
</#list>
</div>
<div class="page">

</div>
<div class="love"><span id="likemm" onClick="likepic(${id?c},0);">点赞</span></div>

<div class="msg"><i>美图精选</i></div>
<div>
    <ul class="other">
    </ul>
    <div style="clear: both;"></div>
</div>
<div class="styleKey" style="display:none ">${style}</div>
<div class="titleKey" style="display:none ">${imageSetListEntity.getTitle()}</div>
<div class="footer">
    <div class="tool"><span><a href="//www.pigutu.com/">去电脑版</a></span><span class="gotop"><a
            href="#">返回顶部</a></span></div>
    <div class="copyright">Copyright © 2017 屁股图 pigutu.com</div>
</div>
<div id="topbtn" onclick="goscrolltop();"></div>
<script type="text/javascript">
    var pageObj = $('.page');
    var html = '';
    var pageCount = ${pageCount?c};
    var pageIndex = ${pageIndex?c};
    var pageNum;

    if (pageCount % 6 == 0) {
        pageNum = pageCount / 6;
    } else {
        pageNum = pageCount / 6 + 1;
    }
    pageNum = parseInt(pageNum);
    if (pageIndex != 1 || pageNum != 1) {
        html = '<ul>';
        if (pageIndex == 1) {
            html += '<li class="pre">上一页</li><li>第${pageIndex?c}页</li><li class="next"><a href="./${(pageIndex+1)?c}">下一页</a></li>';
        } else if (pageIndex == pageNum) {
            html +='<li class="pre"><a href="./${(pageIndex-1)?c}">上一页</a></li><li>第${pageIndex?c}页</li><li class="next"><i>下一页</i></li>';
        }else{
            html+='<li class="pre"><a href="./${(pageIndex-1)?c}">上一页</a></li><li>第${pageIndex?c}页</li><li class="next"><a href="./${(pageIndex+1)?c}">下一页</a></li>';
        }
        html+='</ul>'
        pageObj.html(html);
    }

    myrecommend();
    //图片点赞
    function myrecommend(){
        if(window.XMLHttpRequest){
            xmlhttp = new XMLHttpRequest;
            if(xmlhttp.overrideMimeType){
                xmlhttp.overrideMimeType('text/xml');
            }
        }else if(window.ActiveXObject){
            xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
        }
        xmlhttp.onreadystatechange = recommendcallback;
        xmlhttp.open('GET','/myrecommend?number=4',true);
        xmlhttp.send(null);
    }
    function recommendcallback(){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            var renum = xmlhttp.responseText;
            var obj = $.parseJSON(renum);
            var html='';
            for(var i=0;i<4;i++){
                html+='<li><p><a href="${mUrl}image/'+obj[i].id+'/1"><img src="http://img.pigutu.com/img/'+obj[i].coverUrl+'/thumb" alt="'+obj[i].title+'" /></a><span>'+obj[i].title+'</span></p></li>';
            }
            $('.other').html(html);
        }
    }
    var $wg = function(id){return document.getElementById(id)};
    var viewcss = ['open','close'],showsearch = 0,showmenu = 0;
    //顶部导航
    function viewsearch(){
        $wg('search').className = 'search '+viewcss[showsearch]+'search';
        (showsearch == 1) ? showsearch = 0 : showsearch = 1;
    }
    function viewnav(){
        $wg('menu').className = viewcss[showmenu]+'menu';
        (showmenu == 1) ? showmenu = 0 : showmenu = 1;
    }
    //执行搜索
    function searchpic(){
        if($wg('searchkey').value != ''){document.formsearch.submit();}
    }
    //图片点赞
    var loveyes = 0;
    var likeid = 0;
    function likepic(aid,lid){
        if(likeid == aid){return false;}
        var d_n = new Date().getTime();
        likeid = aid;
        if(window.XMLHttpRequest){
            xmlhttp = new XMLHttpRequest;
            if(xmlhttp.overrideMimeType){
                xmlhttp.overrideMimeType('text/xml');
            }
        }else if(window.ActiveXObject){
            xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
        }
        xmlhttp.onreadystatechange = likeCallback;
        xmlhttp.open('GET','/like?id='+aid,true);
        xmlhttp.send(null);
        $wg('likemm').innerHTML = '已赞';
    }
    function likeCallback(){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            var renum = xmlhttp.responseText;
            renum = parseInt(renum);
            if(renum > 0){
                $wg('love').innerHTML = '喜欢('+renum+')';
            }
        }
    }
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?cc77122375a80acf05c0f78f84deb59a";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();


    (function(){
        var bp = document.createElement('script');
        var curProtocol = window.location.protocol.split(':')[0];
        if (curProtocol === 'https') {
            bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
        }
        else {
            bp.src = 'http://push.zhanzhang.baidu.com/push.js';
        }
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(bp, s);
    })();
</script>
<#--<script>
    var page = 0;
    var style = $(".styleKey").text();
    var titleKey = $("#titleKey").text();
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
                                pageHtml+=' <div><img src="http://img.pigutu.com/img/'+response[i].url+'/'+style+'" alt="'+titleKey+'" /></div>';
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