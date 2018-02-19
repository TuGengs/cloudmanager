<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>

<html>
<head>
<meta charset="utf-8">
<title>签到1</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/sign-in1.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/name-list.css" />

<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script
	src="${pageContext.request.contextPath }/js/jquery.qrcode.min.js"></script>
<script src="${pageContext.request.contextPath }/js/name-list.js"></script>

</head>

<body>
	<header class="clear">
		<span class="num">名&nbsp;&nbsp;&nbsp;&nbsp;单</span> <i class="lt jia"><span>增加</span>&#xe618;</i>
		<i class="rt remove"><span>删除</span>&#xe636;</i>
	</header>
	<div id="page-container">
		<div class="check2-way-container">
			<div class="check2-way" style="height: 68%; top: 35%">
				<p>加入检查</p>
				<div id="four-num2">关注微信公众号《高校云考勤》，扫描下方二维码即可加入检查</div>
				<img src="${pageContext.request.contextPath }/images/line1.png" />
				<p>二&nbsp;&nbsp;维&nbsp;&nbsp;码</p>
				<div id="qcode" style="width: 200; height: 200"></div>
				<img id="imgOne" style="width: 200, height:200" /> <img
					src="${pageContext.request.contextPath }/images/line1.png" />
				<p class="atention">请将二维码或者四位数告知学生，即刻加入</p>
				<a class="check2-way-button">确&nbsp;&nbsp;定</a>
			</div>
		</div>
		<form>
			<div id="code"></div>
			<ul>
				<c:forEach items="${json.data }" var="data">
					<li class="banji"><p>
							<i>&#xe652;</i>&nbsp;${data.class_name }
						</p>
						<div class="checkboxFour all">
							<label for="checkboxFourInput"></label>
						</div>
						<p class="number">
							<span>${data.num }</span>&nbsp;
						</p>
						<ul class="list">
							<c:forEach items="${data.list }" var="item">
								<li class="list-item"><em>${item.name }</em><em>${item.sysnum }</em>
									<input type="hidden" value="${item.user_id }" />
									<div class="checkboxFour">
										<label for="checkboxFourInput"></label>
									</div></li>
							</c:forEach>

						</ul></li>
				</c:forEach>

			</ul>
		</form>
	</div>
	<div class="bottom">
		<ul>
			<li id="assistant-sign"><a
				href="${pageContext.request.contextPath }/check/sign/single/${check_id}">
					<i>&#xe61d;</i>
					<p>检查</p>
			</a></li>
			<li><i>&#xe601;</i>
				<p>名单</p> <em></em></li>
			<li><a
				href="${pageContext.request.contextPath }/check/sign/single/setting?check_id=${check_id}">
					<i>&#xe614;</i>
					<p>设置</p>
			</a></li>
		</ul>
	</div>
	<input type="hidden" value="${check_id }" id="check_id" />
	</div>


</body>
</html>
