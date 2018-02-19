<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
    .demo-form{
      padding: 15px;
      margin: 20px auto;
    }
    @media only screen and (min-width: 960px){
      .demo-form{
        width: 500px;
      }
    }
  </style>
</head>
<body>
	<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
	var data = ${data};
	console.log(data);
    $(function(){
            configWxAPI(data);
    });
    //配置权限
    function configWxAPI(conf){
        wx.config({
            debug:true,//开启调试模式，调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端
                        //打开，参数信息会通过log打出，仅在pc端时才会打印
            appId: conf.appId,//必填，公众号的唯一标识
            timestamp:conf.timestamp,//必填，生成签名的时间戳
            nonceStr:conf.nonceStr,//必填，生成签名的随机串
            signature:conf.signature,//必填，签名
            jsApiList:[
                'checkJsApi',
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'onMenuShareQQ',
                'onMenuShareWeibo',
                'hideMenuItems',
                'showMenuItems',
                'hideAllNonBaseMenuItem',
                'showAllNonBaseMenuItem',
                'onRecordEnd',
                'openLocation',
                'getLocation',
                'hideOptionMenu',
                'showOptionMenu',
                'chooseImage',
                'uploadImage',
                'previewImage',
                'closeWindow',
                'scanQRCode',
                'chooseWXPay'
            ]//必填，需要使用的JS接口列表，也就是配置你想使用的调用接口
        });
    };

	wx.error(function(res){
		alert("error");
	});

    //隐藏相按钮
    //由于权限验证是异步操作，
    //所以我们隐藏操作需要放在wx.ready()里面

    wx.ready(function(){
    	alert("success");
        wx.hideMenuItems({
            menuList:[
                'menuItem:share:appMessage', // 发送给朋友
                'menuItem:share:timeline', // 分享到朋友圈
                'menuItem:share:qq', // 分享到QQ
                'menuItem:share:weiboApp', //分享到微博
                'menuItem:share:QZone', //分享到qq空间
                'menuItem:copyUrl', //复制网页
                'menuItem:openWithQQBrowser', // 在QQ浏览器中打开
                'menuItem:openWithSafari', // 在Safari中打开
                'menuItem:onMenuShareQZone'
            ]// 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
        });
    });
</script>
</body>
</html>