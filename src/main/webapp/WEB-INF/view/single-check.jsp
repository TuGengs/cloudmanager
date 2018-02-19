<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>单门检查查看</title>、
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../css/single-check.css"/>
</head>

<body>
<header>单门检查查看</header>
<div id="page-container">
	<div class="stu-info">
        	<ul class="info-name">
            	<li class="class">班级<i>&#xe652;</i></li>
            	<li class="sysnum">学号<i>&#xe652;</i></li>
                <li class="uname">姓名<i>&#xe652;</i></li>
                <li class="times">缺到次数<i>&#xe652;</i></li>
            </ul>
            <c:forEach items="${data }" var="item">
            <ul class="name-num-info">
                <li>
                    <span class="img">${item.class_name }</span>
                </li>
                <li class="stunum">${item.user_id }</li>
                <li class="name">${item.name }</li>
                <li class="absence">${item.lack_times }</li>             
            </ul>
            </c:forEach>
            
           
     </div>
</div>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath }/js/single-check.js"></script>
</body>
</html>
