<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>美女图片_屁股图</title>
    <meta name="keywords" content="美女图片,pigutu,屁股图">
    <meta name="description" content="屁股图分享高清美女图片- www.pigutu.com">
    <link rel="alternate" media="only screen and(max-width: 640px)" href="http://m.pigutu.com/">
    <link href="http://img.pigutu.com/css/image.css" rel="stylesheet" type="text/css">
    <link href="http://img.pigutu.com/css/favicon.ico" rel="shortcut icon"/>
    <style>
        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        body {
            display: flex;
            align-items: center; /*定义body的元素垂直居中*/
            justify-content: center; /*定义body的里的元素水平居中*/
        }
    </style>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<form name="feedback" method="post" action="/submitfeedback">
    <div class="form-group">
        <label for="inputFeedBack">请输入反馈内容</label>
        <textarea id="feedbackcontent" name="content" class="form-control" rows="3"></textarea>
    </div>
    <button type="button" onclick="submitFeedBack();" class="btn btn-default">提交</button>
</form>
<script type="text/javascript">
    function submitFeedBack() {
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest;
            if (xmlhttp.overrideMimeType) {
                xmlhttp.overrideMimeType('text/xml');
            }
        } else if (window.ActiveXObject) {
            xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
        }
        var area = document.getElementById("feedbackcontent");
        var params = '{content:'+area.innerHTML+'}';
        alert(params);
        xmlhttp.onreadystatechange = callback;
        xmlhttp.open('POST', '/submitfeedback', true);
        xmlhttp.send(params);
    }
    function callback(){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            alert("反馈提交成功!");
        }
    }
</script>
</body>
</html>