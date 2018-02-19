<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>检查</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../css/check2.css"/>
</head>

<body>
	<div class="top">高校云管理</div>
	<header>检查</header>
    <div class="add-check2">
        <span class="add-li">11月10日</span>
        <i id="add-check2-way">&#xe603;</i>
    </div>
    <div class="check2-way">
    	<p>四&nbsp;&nbsp;位&nbsp;&nbsp;数</p>
        <div id="four-num">1234</div>
        <img src="../images/line1.png"/>
        <p>二&nbsp;&nbsp;维&nbsp;&nbsp;码</p>
        <img src="../images/two-dimension code.png"/>
        <img src="../images/line1.png"/>
        <p class="atention">请将二维码或者四位数告知学生，即刻加入</p>
        <a>确&nbsp;&nbsp;定</a>
    </div>
    <div class="bottom">
       	<ul>
        	<li class="">
        	  <a href="${pageContext.request.contextPath }/sign">
            	<span></span>
            	<i>&#xe61d;</i>
                <p>签到</p>
              </a>
            </li>
            <li class="blue">
              <a href="${pageContext.request.contextPath }/check">
            	<span class="bblue"></span>
            	<i>&#xe8cd;</i>
                <p>检查</p>
              </a>
            </li>
            <li class="">
            	<span></span>
            	<i>&#xe631;</i>
                <p>查询</p>
            </li>
            <li class="">
              <a href="${pageContext.request.contextPath }/profile">
            	<span></span>
            	<i>&#xe614;</i>
                <p>个人设置</p>
              </a>
            </li>
        </ul>
    </div>
     <script src="../js/addLoadEvent.js"></script>
</body>
</html>
