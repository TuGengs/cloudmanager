<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>忘记密码</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/register1.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/forget-password.css"/>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
</head>

<body>
	<header>重置密码</header>
    <section class="register1_box">
    	<form id="form" class="register1_form">
        	<a>
            	<i class="phonenumber">手机号码</i>
            	<input id="reg_number" type="text" class="reg_number" name="tel">
            </a>
            <a>
            	<i class="verification_code">验证码</i>
            	<input id="verify_code" type="text" class="reg_code">
            </a>
             <input id="get_code" type="button" value="获取验证码" class="get_code">
           	 <a>
            	<i class="password">重置密码</i>
            	<input id="password_new" type="password" class="reg_password" name="new_password">
            </a>
             <a>
            	<i class="password">确认密码</i>
            	<input id="password_repeat" type="password" class="reg_password" name="password_repeat">
            </a>  
        </form>
        <a id="finish" class="next" href="javascript:void(0);">完成</a>
    </section>
</body>
<script src="${pageContext.request.contextPath }/js/common.js"></script>
<script src="${pageContext.request.contextPath }/js/forget-password.js"></script>
</html>
