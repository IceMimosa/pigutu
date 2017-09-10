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
    <meta name="keywords" content="妹子图,美女图片,性感美女,mm">
    <meta name="description" content="屁股图(www.pigutu.com)每日分享最好看的性感美女图片、高清美女写真，做最好的美女网站！">
    <link href="http://img.pigutu.com/css/mstyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>
<div id="header">
    <ul class="topbar">
        <li class="searchbtn"><span class="icon" onclick="viewsearch();"></span></li>
        <li class="logo"><a href="http://m.mmjpg.com/">屁股图</a></li>
        <li class="nav"><span class="icon" onclick="viewnav();"></span>
            <ul class="menu" id="menu">
                <li><i class="icon"></i><a href="http://m.pigutu.com/">首页</a></li>
                <li><i class="icon"></i><a href="http://m.pigutu.com/hot/1">排行榜</a></li>
                <li><i class="icon"></i><a href="http://m.pigutu.com/recommend/1">推荐</a></li>
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
    <div class="picinfo"><#--<i>${imageSetListEntity.getCreate_time()} 发布</i>--><i>浏览(${(imageSetListEntity.getView_count()+35298)?c})</i><i id="love">喜欢(${imageSetListEntity.getLike_count()})</i></div>
<#list imageSetLists as imageSetList>
    <li>
    <#--    <div class="pic"><img src="http://img.pigutu.com/img/${imageSetList.getUrl()}/pigutu"></div>-->
        <div><img src="http://img.pigutu.com/img/${imageSetList.getUrl()}/pigutu" alt="${imageSetListEntity.getTitle()}" /></div>
    </li>
</#list>
</div>
<div class="love"><span id="likemm" onClick="likepic(${id?c},0);">点赞</span></div>
<div class="msg"><i>美图精选</i></div>
<div>
    <ul class="other">
    </ul>
    <div style="clear: both;"></div>
</div>

<div class="footer">
    <div class="tool"><span><a href="http://www.pigutu.com/">去电脑版</a></span><span class="gotop"><a
            href="#">返回顶部</a></span></div>
    <div class="copyright">Copyright © 2017 屁股图 pigutu.com</div>
</div>
<div id="topbtn" onclick="goscrolltop();"></div>
<script type="text/javascript">
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
                html+='<li><p><a href="${mUrl}image/'+obj[i].id+'"><img src="http://img.pigutu.com/img/'+obj[i].cover_url+'/thumb" alt="'+obj[i].title+'" /></a><span>'+obj[i].title+'</span></p></li>';
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
</script>
</body>
</html>