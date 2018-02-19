<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>基本信息</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/register1.css"/>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
</head>

<body>
	<header>注册</header>
    <section class="register1_box">
    	<form class="register1_form"  id='form' action="" method="">
        	<a>
            	<i class="phonenumber">手机号码</i>
            	<input type="text" name="tel" class="reg_number" id="reg_number" placeholder="请输入手机号">
            </a>
            <a>
            	<i class="password">密码</i>
            	<input type="password" name="password" id="reg_password" class="reg_password" placeholder="6-20位，不含中文">
            </a>
            <a>
            	<i class="verification_code">验证码</i>
            	<input type="text" id="verify_code" class="reg_code">
            </a>
             <input type="button" value="获取验证码" id="get_code" class="get_code">
        </form>
        <a class="next" id="next" href="javascript:void(0);">下一步</a>
    </section>
</body>
<script src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/register1.js"></script>
</html>
