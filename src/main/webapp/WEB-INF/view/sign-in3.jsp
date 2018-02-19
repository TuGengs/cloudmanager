<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的签到</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/sign-in3.css"/>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=c5796f7fc904f11f66b37e0cb38197f5"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>

<body>
	<header>签到<i id="add-sign-way"><a href="javascript:location.reload();">刷新</a></i></header>
    <div class="sign-way">
    	<div class="scan" id='picture'>
        	<i>&#xe625;</i>
            <p>扫一扫</p>
        </div>
        <!--<video id="video" autoplay=""style='width:640px;height:480px'></video>
		<canvas id="canvas" width="640" height="480"></canvas>-->
        <div class="four-num">
        	<i>&#xe67e;</i>
            <p>四位数</p>
        </div>
    </div>
    <!--  <div class="input-num">
    	<p>四位数</p>
        	<input type="text" id="four-num"  name="four-num">
        <a id="submit">确定</a>
    </div>
    -->
	<div id="sign3-container">
    	<ul>
    	
    		<c:forEach items="${map.list }" var="item">
    			<li class="sign3 <c:if test="${item.status==0 }">signing</c:if>">
    				<input type="hidden" id="is_face" value="${ item.is_face}" />
	            	<div class="sign3-left">
	                	<p>${item.check_name }</p>
	                    <!-- <p>发起签到人:<a>${item.name }</a></p> -->
	                </div>
	                
	                <c:choose>
	                    	<c:when test="${item.status==0 }">
	                    		<div class="sign3-right grey">
				                	<i>&#xe659;</i>
				                    <p>未签</p>
				                </div>
	                    	</c:when>
	                    	<c:when test="${item.status==1 }">
	                    		<div class="sign3-right green">
				                	<i>&#xe657;</i>
				                    <p>已签</p>
				                </div>
	                    	</c:when>
							<c:otherwise>
							
							</c:otherwise>
							</c:choose>
	                
           		</li>
    		</c:forEach>
        	
        </ul>
    </div>
    <div class="bottom">
       	<ul>
       		<a href="/sign">
        	<li class="">
            	<span></span>
            	<i>&#xe61d;</i>
                <p>签到</p>
            </li>
            </a>
             
            <li class="">
            <a href="/check">
            	<span></span>
            	<i>&#xe8cd;</i>
                <p>检查</p>
            </a>
            </li>
            
             
            <li class="">
            <a href="/view">
            	<span></span>
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
    <script type="text/javascript">
    	var msg = ${data};
    </script>
    <script src="${pageContext.request.contextPath }/js/addLoadEvent.js"></script>
    <script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
    <script src="${pageContext.request.contextPath }/js/sign-in3-sign-way.js"></script>
</body>
</html>
