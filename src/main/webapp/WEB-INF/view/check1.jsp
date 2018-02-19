<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的检查</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/check1.css"/>
</head>

<body>
<header>检<i id="add-check1-way">&#xe66b;</i>查</header>
<div class="check1-way">
	<a href="${pageContext.request.contextPath }/check/sign/viewAdd">
  		<div class="sign">
       		<i>&#xe628;</i>
    		<p>签到检查</p>
  		</div>
  	</a> 
    <a href="new-check.html">
  		<div class="dorm">
  			<i>&#xe8cd;</i>
    		<p>寝室检查</p>
  		</div>
  	</a>
  	<div class="syt-import">
    	<i>&#xe620;</i>
    	<p>导入检查</p>
  	</div>
</div>
<div class="check1-way check1-way1">
  	<div class="sign1 color">
    	<i>&#xe609;</i>
   		<p>教务系统</p>
	</div>
  	<div class="input-info">
    	<li>
        	<span>账&nbsp;&nbsp;号</span>
      		<input type="text" name="user-name">
    	</li>
    	<li>
        	<span>密&nbsp;&nbsp;码</span>
      		<input type="password" mane="password">
    	</li>
  	</div>
  	<div>
    	<i class="ok">&#xe638;</i>
    </div>
</div>
<div class="input-num">
    	<p>早检签到<i>&#xe63d;</i></p>
        <p>早检签到<i>&#xe63d;</i></p>
        <p>早检签到<i>&#xe63d;</i></p>
		<a class="import">导入</a>
</div>
<div id="check1-container">
  <ul>
   
    <c:forEach items="${json }" var="item">
    	<a href="${pageContext.request.contextPath }/check/sign/single/${item.check_id}">
    		<li class="check1"> <span>${item.check_name }</span> <span>${item.second_name }</span> </li>
    	</a>
    </c:forEach> 
		
  </ul>
</div>
<div class="bottom">
  <ul>
  	
    <li class="">
    <a href="/sign">
     <span></span> <i>&#xe61d;</i>
      <p>签到</p>
      </a>
    </li>
    
    <a href="/check">
    <li class="blue"> <span class="bblue"></span> <i>&#xe8cd;</i>
      <p>检查</p>
    </li>
    </a>
     
    <li class=""> 
    <a href="/view">
    <span></span> <i>&#xe668;</i>
      <p>查看</p>
      </a>
    </li>
    
     
    <li class=""> 
    <a href="/profile">
    <span></span> <i>&#xe614;</i>
      <p>个人设置</p>
     </a>
    </li>
    
  </ul>
</div>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath }/js/addLoadEvent.js"></script> 
<script src="${pageContext.request.contextPath }/js/check1-check-way.js"></script>
<%-- <script src="${pageContext.request.contextPath }/js/check1.js"></script> --%> 
<script src="${pageContext.request.contextPath }/js/import-check.js"></script>
</body>
</html>
