<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新建检查</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/new-check.css"/>
</head>

<body>
	<header>新建检查</header>
    <div id="page-container" >
    <form action="" method="post" id="new-check-form">
    	<div class="new-check-container">
        	<p>课程名</p>
        	<input type="text" name="check_name" value="${data.check_name }"/>
        	<p>班级</p>
        	<input type="text" name="second_name" value="${data.second_name }"/>
        	<input type="hidden" name="check_id" value="${data.check_id }" />
        </div>
        <div class="add-other">
        	<p class="font">添加地点与周期<i class="add-more-info">&#xe604;</i></p>
            <ul class="font add-more">
            	<li>
                	<span>周&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期&nbsp;：</span>
                    <input type="text" name="period" placeholder="1-18周"/> 
                </li>
                <li>
                    <span>节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次&nbsp;：</span>
                    <input type="text" name="times" placeholder="星期一上午1-2节"/>
                </li>
                <li>
                	<span>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点&nbsp;：</span>
                    <input type="text" name="place" placeholder="5-502"/>
                </li> 
            </ul>
        </div>
        <a class="font finish">完成</a>
        </form>  
    </div>
    <script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
    <script src="${pageContext.request.contextPath }/js/addLoadEvent.js"></script>
    <script src="${pageContext.request.contextPath }/js/new-check.js"></script>
    <script src="${pageContext.request.contextPath }/js/common.js"></script>
    <script>
        $('.finish').click(function createCheck(){
            var form = document.getElementById('new-check-form'),
                attrs = ['check_name','second_name'];
            var data = gx.getFormData(form,attrs);
            if(data.check_name === '' || data.second_name === ''){
            	alert('不能为空');
            	return;
            }
            var check_id = $('[name="check_id"]').val();
            var url = '';
            console.log(check_id);
            if(check_id !== "" && check_id !== undefined ){
            	data['check_id'] = check_id;
            	url = '${pageContext.request.contextPath }/check/sign/single/setting/edit/submit';
            }else{
            	url = '${pageContext.request.contextPath }/check/sign/viewAdd/post';
            }
            $.ajax({
                url: url,
                method: 'POST',
                data: data,
                success: function( ret ) {
					location.href="/check"                    
                },
                error: function(err){
                    console.error(err);
                }
            })
        })
    </script>
</body>
</html>
