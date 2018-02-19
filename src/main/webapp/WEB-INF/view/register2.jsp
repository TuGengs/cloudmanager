<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>学校信息</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/register2.css"/>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=c5796f7fc904f11f66b37e0cb38197f5"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
</head>

<body>
	<header>注册</header>
    <div class="user">
  		<div id="choice-student" data-attr="student" class="student">
        	<i>&#xe6b2;</i>
    		<p>学生</p>
  		</div>
  		<div id="choice-teacher" data-attr="teacher" class="teacher">
        	<i>&#xe69d;</i>
    		<p>教师</p>
  		</div>
    </div>
    <section class="register2_box">
    	<form id="form" class="register2_form">
    		<div class="school" style="margin-top: 30px;">
	           	<i>学校</i>
	           	<input class="selected-school" type="text" autocomplete="off">
	        </div>
	        <div class="school-list-container">
	        <!--
	           	<ul class="school-list">
	           		<li>江西农业大学</li>
	           		<li>南昌大学</li>
	           	</ul>
	        -->
	        </div>
	            
        </form>
        <a id="next" class="next" href="javascript:void(0);">下一步</a>
    </section>
    <script src="${pageContext.request.contextPath }/js/common.js"></script>
    <script src="${pageContext.request.contextPath }/js/school-combobox.js"></script>
   	<script src="${pageContext.request.contextPath }/js/register2.js"></script>
</body>
</html>
