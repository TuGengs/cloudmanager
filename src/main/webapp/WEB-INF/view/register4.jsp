<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>教务系统绑定</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link href="${pageContext.request.contextPath }/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/register4.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
</head>

<body>
	<header>注册</header>
    <section class="register2_box">
    	<div class="crtvu">教务系统</div>
    	<form class="register2_form" id="form">
            <a>
            	<i>账号</i>
            	<input type="text" class="stu_building" name="sysnum" placeholder="学号/职工号">
            </a>
            <a>
            	<i>密码</i>
            	<input type="password" class="stu_room" name="eas_password" placeholder="教务系统密码" style="border: none;">
            </a> 
        </form>
        <a class="finish" id="submit" href="javascript:void(0);" >完成</a>
        <span class="hint">教务系统无法注册？<br>点击<a href="/register/extInfo">这里</a>输入详细信息完成注册。</span>
    </section>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
<script src="${pageContext.request.contextPath }/js/register4.js"></script>
</html>
