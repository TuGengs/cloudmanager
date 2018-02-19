// JavaScript Document
// 添加签到方式：扫一扫和二维码
function addSignWay(){
	console.log("1");
	if(!document.getElementById||!document.getElementsByClassName) return false;
	var oI=document.getElementById("add-sign-way");
	var oDiv=document.getElementsByClassName("sign-way")[0];
	var oBox=document.getElementsByClassName("input-num")[0];
	var m=1;
	var n=1;
	/*oI.onclick=function(){
		if(n){
			oBox.style.display="block";
			n=0;
			}
		else{
			oBox.style.display="none";
			n=1;
			}
		}*/
	}

$('#submit').click(function(){
	var code = $('#four-num').val();
	$.ajax({
		url:'api/check/code/'+code,
		method:'GET',
	 	success: function(ret){
	 		
	 		console.log(ret);
	 		if(ret.success){
	 			console.log(ret.data);
	 			ret = ret.data;
	 			// 从redis数据库获取检查的类型和ID
	 			var type = ret.type==="signin"?1:0; // 根据传过来的字段进行更改 但架构就是这样
	 			var check_id = ret.checkInfo.check_id;
	 			
	 			$.ajax({
	 				url:'check/join',
	 				method:'POST',
	 				data:{type:type,check_id:check_id},
	 			 	success: function(ret){
	 			 		if(ret.success){
	 			 			alert("成功加入");
	 			 			$('.input-num').css({display:'none'});
	 			 		}else{
	 			 			alert(ret.errorMessage);
	 			 			$('.input-num').css({display:'none'});
	 			 		}
	 			 		
	 			 	},
	 			 	error: function(err){
	 			 		alert(err);
	 			 		$('.input-num').css({display:'none'});
	 			 	}
	 			 	
	 			 })
	 		}else{
	 			alert(ret.errorMessage);
	 			$('.input-num').css({display:'none'});
	 		}
	 		
	 	},
	 	error: function(err){
	 		$('.input-num').css({display:'none'});
	 	}
	 })
})

//打开输入二维码弹窗
function showBox(){
	var oBox=document.getElementsByClassName("input-num")[0];
	var oFourNum=document.getElementsByClassName("four-num")[0];
	var n=1;
	oFourNum.onclick=function(){
		if(n){
			oBox.style.display="block";
			n=0;
			}
		else{
			oBox.style.display="none";
			n=1;
			}
		}
	}
//打开摄像头
function openCamera(){
	var video = document.getElementById("video");  
	var context = canvas.getContext("2d");  
	var errocb = function () {  
		console.log('sth wrong!');  
	}  
	  
	if (navigator.getUserMedia) { // 标准的API  
		navigator.getUserMedia({ "video": true }, function (stream) {  
			video.src = stream;  
			video.play();  
		}, errocb);  
	} else if (navigator.webkitGetUserMedia) { // WebKit 核心的API  
		navigator.webkitGetUserMedia({ "video": true }, function (stream) {  
			video.src = window.webkitURL.createObjectURL(stream);  
			video.play();  
		}, errocb);  
	}  
	  
	document.getElementById("picture").addEventListener("click", function () {  
		context.drawImage(video, 0, 0, 640, 480);  
	});  
	}
addSignWay();
showBox();
//openCamera();

//var data = '{"list":[{"is_face":1,"check_auto_id":32,"name":"test1","single_id":21,"check_id":32,"check_name":"测试","status":0}]}'
function renderSigned(){
	var div = $('<div></div>').html('<i>&#xe657;</i><p>已签</p>');
	div.addClass('sign3-right green');
	return div;
}
function renderunSigned(){
	var div = $('<div></div>').html('<i>&#xe659;</i><p>未签成功</p>');
	div.addClass('sign3-right grey');
	return div;
}
function renderSigning(){
	var div = $('<div></div>').html('<i>&#xe65b;</i><p>正在签到</p>');
	div.addClass('sign3-right blue');
	return div;
	
}
function chooseImg_Opinion() {
	wx.chooseImage({
			sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
			sourceType: ['camera'], // 可以指定来源是相册还是相机，默认二者都有
			success: function (res) {
					/*wx.uploadImage({
							localId: res.localIds, // 需要上传的图片的本地ID，由chooseImage接口获得
							success: function (res) {
									var serverId = res.serverId; // 返回图片的服务器端ID
									return serverId;
							}
					});*/
			}
	});
}

$('.signing').click(function(){
		
		var is_face = $(this).find('#is_face').val();
		var serverId = 0;
		if(is_face == 1){
			serverId=chooseImg_Opinion();
			
		}
		var self = $(this);
		self.find('.sign3-right').remove();
		self.append(renderSigning());
		var info = msg.list,
		index = $('.sign3').toArray().indexOf(this);
		
		info = info[index];
		
		getPosition(function(longitude,latitude){
			var data = {check_auto_id:info.check_auto_id,
			 		   single_id:info.single_id,
			 		   check_id:info.check_id,
			 		   latitude:latitude,
			 		   longitude:longitude};
			if(serverId > 0){
				data['serverId'] = serverId;
			}
			$.ajax({
				url:'sign/submit',
				method:'POST',
			 	data: data,
			 	success: function(ret){
			 		if(ret.success){
			 			self.find('.sign3-right').remove();
			 			self.append(renderSigned());
			 		}else{
			 			alert(ret.errorMessage);
			 			self.find('.sign3-right').remove();
			 			self.append(renderunSigned());
			 		}
			 		
			 	},
			 	error: function(err){
			 		self.find('.sign3-right').remove();
		 			self.append(renderunSigned());
			 	}
			 })
			 
		});
		
})


function configWxAPI(conf){
		conf = JSON.parse(conf);
        wx.config({
            debug:false,//开启调试模式，调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端
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
        wx.error(function(res){
    		alert("error");
    	});
       
    };
    $.ajax({
        url: '/wechat/jssdk',
        method: 'GET',
        data: {url:window.location.href},
        success: function(ret){
        	configWxAPI(ret);
        },
        error: function(err){console.log(err)}
    });

    


function getPosition(onSuccess,onError){
		wx.ready(function(res){
			wx.getLocation({
				type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
				success: function (res) {
						var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
						var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
						var speed = res.speed; // 速度，以米/每秒计
						var accuracy = res.accuracy; // 位置精度
						console.log(latitude+' '+longitude);
						onSuccess(longitude,latitude);
				},
				error: function(err){
					alert("error");
					onError(err);
				}
			});
		});
}

