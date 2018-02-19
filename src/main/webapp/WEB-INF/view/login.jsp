<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>高校云考勤</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
</head>

<body>
	<img id="login-logo" src="${pageContext.request.contextPath }/images/login-logo.png"/>  
    <section id="login-box">
    	<!--<img class="login-icon" src="../images/login-icon.png"/>-->
        <div class="login-icon"><i>&#xe605;</i></div>
        <form id='form' action="" method="">
            <a>
            	<i class="username">手机</i>
            	<input type="text" class="reg_number" name='tel'>
            </a>
            <a>
            	<i class="password">密码</i>
            	<input type="password" class="reg_password" name='password'>
            </a>
        </form>
        <a id='login' class="login-btn" href='javascript:void(0);'></a>
    </section>	
   	<hr>
    <div class="box-bottom">
    	<a class="more register" href="/register/common">注册账号</a>
        <span>|</span><a href="/resetPassword" class="more forget">忘记密码</a>
    </div>
</body>
<script type="text/javascript">
	var loginBtn = document.getElementById('login'),
	form = document.getElementById('form');
	loginBtn.addEventListener('click',function login(){
		
		var formData = gx.getFormData(form,['tel','password']);
		
		if(formData.tel === '') {
			alert('请输入手机号！');
			return;
		}
		if(formData.password === '') {
			alert('请输入密码！');
			return;
		}
		
		$.ajax({
		    url: '/api/account/login',
		    method: 'POST',
		    data: formData,
		    success: function(data){
		        if(data.success) {
		        	location.href = '/check';
		        	return;
		        }
		        alert(data.errorMessage);
		    },
		    error: function(err){
		        alert('无法连接服务器。。。');
		        console.log(err);
		    }
		})
	})
</script>
</html>
