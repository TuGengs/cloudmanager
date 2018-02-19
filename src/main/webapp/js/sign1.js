//qrcode

qr();

function qr(){
	$.ajax({
        url: 'qrcode',
        method: 'GET',
        success: function(ret){
        	var text = ret.data+$('#single_id')[0].value;
        	var qrcode = $('#qrcode').qrcode({width:200,height:200,text:text}).hide();
        	var c = qrcode.find('canvas').get(0);
        	$('.QRcode-min').attr('src',c.toDataURL('image/jpg'));
        	$('.QRcode').attr('src',c.toDataURL('image/jpg'));
        },
        error: function(err){console.log(err)}
    })
}

var isShowFace = false;
$('.face').hide();
$('.sign-face').click(function(){
	if(isShowFace){
		$(this).find('span').text('关闭人脸识别');
		$('.face').hide();
		isShowFace = false;
	} else{
		$('.face').show();
		$(this).find('span').text('开启人脸识别');
		isShowFace = true;
	}
})
$('.QRcode-min').click(function(){
	$('.QRcode-max').show();
})
$('.QRcode-max').click(function(){
	$('.QRcode-max').hide();
})

$('.more').click(function(e){
    if(this.dataset.detail === '1'){
        $('.more').css({background:'#666'})
        $('.info-asign-absence').css({display: 'none'});
        $('.absence-num').css({display: 'none'});
        $('.info-name li').css({width: '33%'});
        $('.name-num-info li').css({width: '33%'});
        this.dataset.detail = '0';
    } else{
        $('.more').css({background:'#77cf85'})
        $('.info-asign-absence').css({display: 'block'});
        $('.absence-num').css({display: 'block'});
        $('.info-name li').css({width: '25%'});
        $('.name-num-info li').css({width: '25%'});
        this.dataset.detail = '1';
    }
})

$('#assistant-sign-all').click(function(){
    $('.name').parent().css({color:'#1e8d26'});
    var sysnums = [],
        single_id = $('#single_id')[0].value;
    for(var i=0; i<$('.name').length;i++){
        $('.name')[i].dataset.isSelect = '1';
        sysnums.push($($('.name')[i]).parent().children()[4].value);
        
    }
    //一键 {sysnums:sysnums,single_id:single_id}
    $.ajax({
        url: 'sign_all',
        method: 'POST',
        data: JSON.stringify({sysnums: sysnums,single_id: single_id}),
        contentType: "application/json;charset=utf8",
        success: function(ret) {},
        error: function(err){alert(err)}
    })
})

var SAT = false;
$('#assistant-sign').click(function(){
    if(!SAT){
        $('.add-sign').show();
        $(this).css({color: '#0871e7'});
        return SAT = true;
    } else{
        $('.add-sign').hide();
        $(this).css({color: '#666'});
        return SAT = false;
    }
})
$('#start-sign').click(function(){
    
    var single_id = $('#single_id')[0].value;
    var data = {is_open: parseInt(1), single_id: parseInt(single_id) ,is_face:isShowFace?1:0}
    
    getPosition(function(longitude,latitude){
    	$.ajax({
            url: 'help/address',
            method: 'POST',
            data: {single_id:parseInt($('#single_id')[0].value),latitude: latitude,longitude:longitude},
            success: function(ret){
            },
            error: function(err){console.log(err)}
        })
    });
    $.ajax({
        url: 'help',
        method: 'POST',
        data: data,
        success: function(ret){
        	if(ret.success){
        		$('.start-sign').show();
        		TIME.startCount();
        	}else{
        		alert(ret.errorMessage);
        	}
        },
        error: function(err){console.log(err)}
    })
    

})
$('.off').click(function(){
    TIME && TIME.stopCount();
    $('.add-sign').hide();
    $('#assistant-sign').css({color: '#666'});
    var single_id = $('#single_id')[0].value;
    var data = {is_open: parseInt(0), single_id: parseInt(single_id) ,is_face:parseInt(0)}
    $.ajax({
        url: 'help',
        method: 'POST',
        data: data,
        success: function(ret){
        	window.location.reload(true);
        },
        error: function(err){console.log(err)}
    })
    return SAT = false;
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
    // 获取jssdk的信息
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
// render user info list
//var data = '{"time":0,"list":[{"icon_url":"upload/65913eff61924c1897c7cf9f09e052b2.jpg","sysnum":20140001,"class_id":2,"name":"test1","lack_num":0},{"icon_url":"upload/65913eff61924c1897c7cf9f09e052b2.jpg","sysnum":20140002,"class_id":2,"name":"test2","lack_num":2},{"icon_url":"upload/65913eff61924c1897c7cf9f09e052b2.jpg","sysnum":20140004,"class_id":1,"name":"test4","lack_num":1}],"class":[{"class_id":1,"class_name":"计科1401"},{"class_id":2,"class_name":"计科1402"}],"status":{"lack_sum":3,"lack_rate":"100%","sum":3}}'

//var datat = '{"time":"0","list":[{"person":[{"sysnum":20140003,"img_url":"upload/65913eff61924c1897c7cf9f09e052b2.jpg","name":"test3","status":1,"lack_num":1},{"sysnum":20140004,"img_url":"upload/65913eff61924c1897c7cf9f09e052b2.jpg","name":"test4","status":1,"lack_num":1}],"class_id":1,"class":"计科1401","status":{"lack_rate":"0%","sum":2,"lack_num":0}},{"person":[{"sysnum":20140001,"img_url":"upload/65913eff61924c1897c7cf9f09e052b2.jpg","name":"test1","status":1,"lack_num":0},{"sysnum":20140002,"img_url":"upload/65913eff61924c1897c7cf9f09e052b2.jpg","name":"test2","status":0,"lack_num":1}],"class_id":2,"class":"计科1402","status":{"lack_rate":"50%","sum":2,"lack_num":1}}],"status":{"lack_rate":"25%","sum":4,"lack_num":1}}'
var statusColor = {
    status0: '#535353', //缺到
    status1: '#fe5127', //未全签
    status2: '#398dd5', //请假
    status3: '#1e8d26', //success
}
var TIME;
$('.info-asign-status').click(function(e){sort({sort_id:0})})
$('.info-asign-sysnum').click(function(e){sort({sort_id:1})})
$('.info-asign-name').click(function(e){location.reload(true)})
$('.info-asign-absence').click(function(e){sort({sort_id:3})})

$('#select').change(function(e){
    // sort({class:e.target.value});
    if(e.target.value !== '0'){
    	var index = e.target.value;
        $('.all_class').hide();
        $('.class_id_'+e.target.value).show();
        $('.all-num').html(data['notOk'][index][0]);
        $('.lack-num').html(data['notOk'][index][1]);
        $('.lack-rate').html(data['notOk'][index][2]);
    } else{
        $('.all_class').show();
        $('.all-num').html(data['status']['sum']);
        $('.lack-num').html(data['status']['lack_num']);
        $('.lack-rate').html(data['status']['lack_rate']);
    }
    
})


function sort(data){
    var single_id = $('#single_id')[0].value;
    data.single_id = single_id;
    $.ajax({
        url: 'sort',
        method: 'GET',
        data: data,
        success: function(ret){
        	ret = JSON.parse(ret);
            renderUserInfoList(ret);
        },
        error: function(err){console.log(err)}
    })
}
renderUserInfoList(data);

function renderUserInfoList(info){
	//info = info || JSON.parse(info);
    info =  info || data;
    var list = info.list;
    var time = parseInt(info.time) !== 0 ? parseInt(info.time) : 0;
    $('.stu-info-contaienr').html('');
    TIME = TIME || (function(time){
        var timer;
        function startCount(){
            timer = setInterval(function(){
                time++;
                console.log(time);
                $('.time').html(timeDeal(time));
            },1000);
        }
        function stopCount(){
            clearInterval(timer);
            time = 0;
        }
        return {startCount: startCount, stopCount: stopCount}
    })(time);
    if(time !== 0){
        $('.start-sign').show();
        TIME.startCount();
    }
    $('.all-num').text(info.status.sum);
    $('.lack-num').text(info.status.lack_num);
    $('.lack-rate').text(info.status.lack_rate);
    for(var i = 0;i < list.length; i++){
        $('.stu-info-contaienr').append(renderUserInfoListItem(list[i]));
    }
    $('#select').val(0);
}
function timeDeal(time){
    var hour = Math.floor(time/3600);
    var min = Math.floor((time-hour*3600)/60);
    var sec = Math.floor(time%60);
    hour = hour > 9 ? hour : '0'+ hour;
    min = min > 9 ? min : '0'+ min;
    sec = sec > 9 ? sec : '0'+ sec;
    return hour+' : '+min+' : '+sec;
}

function renderUserInfoListItem(info){
	var path = "";
    var ul = $('<ul></ul>').addClass('name-num-info'),
        avatar = $('<li></li>').addClass('avatar').html(`<span class="img"><img onerror="this.src='/images/user.svg'" src="${info.img_url}" /></span>`),
        sysnum = $('<li></li>').addClass('num').html(info.sysnum),
        name = $('<li></li>').html(info.name).addClass('name'),
        absence = $('<li></li>').html(info.lack_num).addClass('absence-num'),
        user_id = $('<input type="hidden" value="'+info.user_id+'" />').addClass('user_id');
    ul.click(clickName);
    avatar.click(clickAvatar);
    
    ul[0].dataset.isSelect = info.status;
    ul.css({color: statusColor['status'+info.status]});
    ul.addClass('all_class class_id_'+info.class_id);
    ul.append(avatar).append(sysnum).append(name).append(absence).append(user_id);
    return ul;
}

function renderDetail(info,user_id){
	info = JSON.parse(info);
    var div = $('<div></div>').html( `<span class="big-img"><img onerror="this.src='/images/user.svg'" src="${info.img_url}"></span>`);
    var moreInfo = $('<div></div>').html(`<p><span>${info.class}</span><span class="sex">${info.gender ? '男': '女'}</span></p>
                <p>${info.tel}</p>`);
    var button1 = $('<a></a>').text('请假').addClass('more-info-button');
    var button2 = $('<a></a>').text('迟到早退').addClass('more-info-button');
    var remark;
    if(info.remark===undefined){
    	remark = "";
    }else{
    	remark = info.remark;
    }
    var form = $('<div></div>').html('<input class="remark" type="text" name="text" placeholder="填写备注" value="'+remark+'" >');
    form.addClass('clear-info-form');
    var remark = $('<i>').html('&#xe635;');
    form.append(remark);
    moreInfo.append(button1);
    moreInfo.append(button2);
    moreInfo.addClass('more-info');
    div.addClass('clear-info');
    div.append(moreInfo).append(form);
    button1.click(clickButton(2));
    button2.click(clickButton(1));
    remark.click(function(e){
    	e.stopPropagation();
    	clickRemark(user_id);
    	});
    div.click(function(e){e.stopPropagation()})
    return div;
}
function clickRemark(user_id){
	var form = $(this).parent();
	var remark = form.find('input').val();
	$.ajax({
        url: 'single/remark',
        method: 'POST',
        data: {remark:remark,single_id:$('#single_id')[0].value,user_id:user_id},
        success: function( ret ) {
        }
    })
}
function clickButton(status){
	return function(e){
		var infoContainer = $(e.target).parent().parent();
	    var tips = infoContainer.find('.remark').val()
	        nameObj = infoContainer.parent().children()[2];
	    console.log('nameObj:',nameObj);
	    checkDeal(nameObj,status,tips);
	}   
}

function clickAvatar(e){
	e.stopPropagation();
    var infoContainer = $(this).parent();
    var info = infoContainer.find('.clear-info')[0];
    if(info) return $(info).remove();
    var sysnum = parseInt(infoContainer.children()[4].value);
     $.ajax({
         url: 'more_info',
         method: 'GET',
         data: {sysnum: sysnum,single_id:$('#single_id')[0].value},
         success: function( ret ) {
        	 
             infoContainer.append(renderDetail(ret.data,sysnum));
         }
     })
}
function clickName(e){
    e.stopPropagation();
    // 当isSelect 为0或者undefined时 点击后标记为已签到
    var isSelect = this.dataset.isSelect;
    this.dataset.isSelect = isSelect === '0'|| isSelect === undefined ? 3:0;
    checkDeal($(this).find('.name')[0],this.dataset.isSelect);
}

function checkDeal(name,status,tips){
	console.log(status);
    $(name).parent().css({color: statusColor['status'+status]});
    var sysnum = $(name).parent().children()[4].value;
    var single_id = $('#single_id')[0].value;
    $.ajax({
        url: 'sign',
        method: 'POST',
        data: JSON.stringify({
            sysnum: sysnum,
            single_id: single_id,
            status: parseInt(status), 
            tips: tips
        }),
        contentType: "application/json;charset=utf8",
        success: function(ret) {},
        error: function(err){alert(err)}
    });
}
$("#all-keynote").click(function(){
	$(".key-note").toggle();
	var oDiv=$(".key-note").get(0);
	var oLi=$("#all-keynote").get(0);		
	if(oDiv.style.display=="none"){
		oLi.style.color="#535353"
		}
	else{
		oLi.style.color="#0871e7";
		}
	})

$('#submit-all-keynote').click(function(){
	var content = $('#all-keynote-content').val();
	$.ajax({
		url: 'all/remark',
		method: 'POST',
		data: {content: content,single_id:$('#single_id')[0].value},
		success: function(){}
	})
})
