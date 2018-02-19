<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>单次检查名单</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/sign-in1.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/sign-in2.css"/>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=c5796f7fc904f11f66b37e0cb38197f5"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery.qrcode.min.js"></script>
</head>

<body>
    <header>${date }</header>
    <div class="add-sign">
    	<div class="left">
        	<p><i>&#xe689;</i>地点签到</p>
            <p class = 'sign-face'><i>&#xe7cb;</i><span>关闭人脸识别</span><i class="face">&#xe638;</i></p>
        </div>
        <div class="right">
        		<div id="qrcode"></div>
        		<img class = 'QRcode-min' src = 'https://ooo.0o0.ooo/2017/04/11/58ecb5d77075f.jpg' />
        		<div class = 'QRcode-max' >
        			<img class = 'QRcode'  />
        		</div>
        		<i id="start-sign">点击开始签到</i>
        </div>
        <div class="start-sign">
        	<div class="time">00:00</div>
            <div class="off">关闭</div>
        </div>
    </div>
    <div class="all-info">
    	<ul>
        	<li>
            	<p>班级</p>
                 <div>
                    <select name="test" id="select">
                    	<option value="0" selected="selected">全部</option>
                    	<c:forEach items="${json.option }" var="list">
                    		<option value="${list.class_id }">${list.class_name }</option>
                    	</c:forEach>
                        
                        
                    </select>
                </div>
            </li>
            <li>
            	<p>总人数</p>
                <p class="all-num">${json.status.sum }</p>
            </li>
            <li>
            	<p>缺到人数</p>
                <p class="lack-num">${json.status.lack_num }</p>
            </li>
            <li>
            	<p>缺到率</p>
                <p class="lack-rate">${json.status.lack_rate }</p>
            </li>
        </ul>
    </div>
    <div id="page-container" class = 'sign1-container'>
    	<div class="mark">
        	<ul>
            	<li>
                	<span class="signed"></span>
                    <a>已签到</a>
                </li>
                <li>
                	<span class="signing"></span>
                    <a>迟到早退</a>
                </li>
                 <li>
                	<span class="leave"></span>
                    <a>请假</a>
                </li>
                 <li>
                	<span class="unsign"></span>
                    <a>未签到</a>
                </li>
               <!--  <li>
                	<span class="more" data-detail = '1'>详情</span>
                </li> -->
            </ul>
        </div>
        <div class="stu-info">
        	<ul class="info-name">
            	<li class="info-asign-status">状态<i>&#xe652;</i></li>
            	<li class="info-asign-sysnum">学号<i>&#xe652;</i></li>
                <li class="info-asign-name">姓名<i>&#xe600;</i></li>
                <li class="info-asign-absence">缺到次数<i>&#xe652;</i></li>
            </ul>
            <%-- <c:forEach items="${json.list}" var="list">
            	
            	<c:forEach items="${list.person}" var="item">
            		<ul class="name-num-info class_id_${list.class_id }">
		                <li class = 'avatar'>
		                    <span class="img"><img src="${pageContext.request.contextPath }/images/lisa.jpg"/></span>
		                </li>
		                <li class="num">${item.sysnum }</li>
		                <li class="name">${item.name }</li>
		                <li class="absence">${item.lack_num }</li>
            		</ul>
            	</c:forEach>
            </c:forEach> 
			--%>
            <div class="stu-info-contaienr"></div>
         
        </div>
        <div class="key-note">
            <textarea id='all-keynote-content'>${remark }</textarea>
            <em id="submit-all-keynote">确认</em>
            <img class="triangle" src="../images/triangle.png">
       </div>
       <div class="bottom">
       	<ul>
        	<li id="assistant-sign">
            	<i>&#xe61d;</i>
                <p>辅助签到</p>
            </li>
            <li id="assistant-sign-all">
            	<i>&#xe601;</i>
                <p>一键全到</p>
            </li>
            <li id="all-keynote"> 
            	<i>&#xe628;</i>
                <p>一键备注</p>
            </li>
        </ul>
        <input type="hidden" value="${single_id }" name="single_id" id="single_id"/>
    </div>
    </div>
    
    <script type="text/javascript">
    	var data = ${data};
    </script>
    
    
	<script src="${pageContext.request.contextPath }/js/addLoadEvent.js"></script>
    <script src="${pageContext.request.contextPath }/js/sign1.js"></script>
</body>
</html>
