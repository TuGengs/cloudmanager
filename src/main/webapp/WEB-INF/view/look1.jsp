<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>获得查看的检查</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/look1.css"/>
</head>

<body>
	<header>查看<i id="add-check1-way">&#xe66b;</i></header>
     <div class="check1-way">
    	<div id="find">
        	<i>&#xe65d;</i>
            <p>ID查找</p>
        </div>
        <div>
        	<i>&#xe625;</i>
            <p>扫一扫</p>
        </div>
    </div>
    <div class="id-way">
        <div class="input-id">
        	<li><span>I&nbsp;D&nbsp;帐&nbsp;&nbsp;号:</span><input type="text" id="user-name"></li>
            <li><span>I&nbsp;D&nbsp;密&nbsp;&nbsp;码:</span><input type="password" id="password"></li>
        </div>
		
        	<div class="ok">
       			<i>&#xe638;</i>
       		</div>
       
    </div>
	<div id="check1-container">
    	<ul>
    		<c:forEach items="${list }" var="item">
    			<a href="view/list?check_id=${item.check_id }&sort_id=0">
	                <li class="check1">
	                    <span>${item.check_name }</span>
	                    <span>${item.second_name }</span>
	                </li>
            	</a>
    		</c:forEach>
        	
        </ul>
    </div>
    <div class="bottom">
       	<ul>
        	<li class="">
        		<a href="/sign">
	            	<span></span>
	            	<i>&#xe61d;</i>
	                <p>签到</p>
                </a>
            </li>
            <li class="">
            	<a href="/check">
            	<span></span>
            	<i>&#xe8cd;</i>
                <p>检查</p>
                </a>
            </li>
            <li class="blue">
            	<a href="/view">
            	<span class="bblue"></span>
            	<i>&#xe668;</i>
                <p>查看</p>
                </a>
            </li>
            <li class="">
            	<a href="/profile">
            	<span></span>
            	<i>&#xe614;</i>
                <p>个人设置</p>
                </a>
            </li>
        </ul>
    </div>
    <script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
    <script src="${pageContext.request.contextPath }/js/addLoadEvent.js"></script>
   <script src="${pageContext.request.contextPath }/js/look.js"></script>
</body>
</html>
