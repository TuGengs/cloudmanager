<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>详细信息</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/register3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
</head>

<body>
	<header>注册</header>
    <section class="register2_box">
    	<form class="register2_form" id="form">
    		<a>
            	<i>姓名</i>
            	<input type="text" id='name' class="stu_class" name="name">
            </a>
            <a>
            	<i>学院</i>
            	<input type="text" id='academy' class="stu_class" name="academy">
            </a>
            <a>
            	<i>班级</i>
            	<input type="text" id='clazz' class="stu_class" name="clazz">
            </a>
            	<a>
            	<i >学号/工号</i>
            	<input type="text" id='sysnum' class="stu_num" name="sysnum">
            </a>
            <a>
            	<i >楼栋</i>
            	<input type="text" id='building' class="stu_building" name="building">
            </a>
            <a>
            	<i>寝室号</i>
            	<input type="text" id='room' class="stu_room" name="room">
            </a> 
        </form>
        <a class="finish" id="next_btn" href="javascript:void(0);">完成</a>
    </section>
    <script src="${pageContext.request.contextPath }/js/common.js"></script>
   	<script src="${pageContext.request.contextPath }/js/register3.js"></script>
</body>
</html>

