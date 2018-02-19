<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>个人信息设置</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-wesb-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/set-info.css" />
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
</head>

<body>
	<header>
		个人设置<i><input type="submit" value="编辑" class="stu-info-submit" /></i>
	</header>
	<div id="set-info-container">
		<div class="stu-image">
			<div id="stu-img">
				<img id='icon' width="100%" height="100%"
					src="${pageContext.request.contextPath }/images/user.svg" />
				<form id="upload-icon" enctype="multipart/form-data">
					<input type="file" name='icon'
						class="stu-avatar-upload stu-info-input" />
				</form>
			</div>
			<button id="logout">退出登录</button>
		</div>
		<form id="stu-info-form" method="POST">
			<ul class="basic-info">
				<li><span>姓名</span> <span><input class='stu-info-input'
						id='name' type="text" name='name' disabled></span></li>
				<li><span>学校</span> <span><input type="text" id='school'
						disabled></span></li>
				<li><span>院系</span> <span><input class='stu-info-input'
						id='academy' type="text" name="academy" disabled></span></li>
			</ul>
			<ul class="basic-info">
				<li><span>学号/工号</span> <span><input id='sysnum'
						name='sysnum' class='stu-info-input' type="text"
						disabled></span></li>
				<li><span>班级</span> <span><input class='stu-info-input'
						id='class' type="text" placeholder="(教师不用填写)" name="clazz"
						disabled></span></li>
				<li><span>楼栋</span> <span><input class='stu-info-input'
						id='building' type="text" placeholder="(教师不用填写)" name="building"
						disabled></span></li>
				<li><span>寝室号</span> <span><input class='stu-info-input'
						id='room' type="text" placeholder="(教师不用填写)" name="room" disabled></span></li>
			</ul>
		</form>
		<ul class="basic-info">
			<li><i></i> <span>签到照片</span></li>
			<div class="stu-info-image-group">
				<form id="upload-photo" class="camera">
					<label class='upload-hint'>点击上传</label>
					<img id="stu-photo" style="min-width:100px; max-width:100%; max-height:200px" >
					<input type="file" id="photo" name="photo" class="stu-info-image">
				</form>
			</div>
		</ul>
	</div>
	<div class="bottom">
		<ul>
			<li class="">
			  <a href="${pageContext.request.contextPath }/sign">
				<span></span>
				<i>&#xe61d;</i>
				<p>签到</p>
			  </a>
			</li>
			<li class="">
			  <a href="${pageContext.request.contextPath }/check">
				<span></span>
				<i>&#xe8cd;</i>
				<p>检查</p>
			  </a>
			</li>
			<li class="">
			  <a href="${pageContext.request.contextPath }/view">
				<span></span>
				<i>&#xe668;</i>
				<p>查看</p>
			  </a>
			</li>
			<li class="blue"><span></span> <i>&#xe614;</i>
				<p>个人设置</p></li>
		</ul>
	</div>
</body>
    <script src="${pageContext.request.contextPath }/js/common.js"></script>
	<script src="${pageContext.request.contextPath }/js/set-info.js"></script>
</html>
