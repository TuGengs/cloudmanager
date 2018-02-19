<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>单次检查目录</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/check2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/jquery-weui.css">
</head>

<body>
	<header style="letter-spacing:0em">${json.check_name}   ${json.second_name}</header>
    <div class="add-check2">
        <!-- <span class="add-li">11月10日</span> -->
        <input class="add-li" id="time" type="text" style="border:none;" >
    </div>
	<div id="check2-container">
	<c:if test="${json.type==1 }">
    	<ul>
        	<li class="check2" style="margin-top: 42px;">
            	<span id="add-check2-way">点击添加新纪录</span>
            </li>
        </ul>
	</c:if>
		<ul>
    		<c:forEach items="${json.list }" var="item">
	    		
	        	<li class="check2">
	        		<a href="${pageContext.request.contextPath }/check/sign/detail/${item.id}">
	            	<span>${item.time }
	            	
	            	</span>
	            	</a>
	            	<i style="fontFamily:iconfont;fontWeight:bold" class="delete">&#xe60d;</i>
	            </li>
	            
    		</c:forEach>
        	
        </ul>
    </div>
	<c:if test="${json.type==1 }">
    <div class="bottom bot">
       	<ul>
            <li class="">
            	<span></span>
            	<i>&#xe8cd;</i>
                <p>检查</p>
            </li>
            <li class="">
            	<span></span>
				<a href="${pageContext.request.contextPath }/check/sign/single/list?check_id=${check_id}">
            	<i>&#xe601;</i>
                <p>名单</p>
				</a>
            </li>
            <li class="">
            	<span></span>
				<a href="${pageContext.request.contextPath }/check/sign/single/setting?check_id=${check_id}">
            	<i>&#xe614;</i>
                <p>设置</p>
				</a>
            </li>
        </ul>
    </div>
	</c:if>
    <input type="hidden" id="check_id" value="${check_id }" />
    <script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
    <script src="${pageContext.request.contextPath }/js/jquery-weui.js"></script>
    <script src="${pageContext.request.contextPath }/js/fastclick.js"></script>
    <script src="${pageContext.request.contextPath }/js/addLoadEvent.js"></script>
	<script src="${pageContext.request.contextPath }/js/check2.js"></script>
   <script src="${pageContext.request.contextPath }/js/check2-check-way.js"></script>
   
</body>
</html>