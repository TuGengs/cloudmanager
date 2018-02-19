var userType = new Object();
var schoolInfo = new Object();
var data = [];

function getPosition(onSuccess,onError){
	
	var map, geolocation;
	   
	// 加载地图，调用浏览器定位服务
	    map = new AMap.Map('container', {resizeEnable: false});
	    map.plugin('AMap.Geolocation', function() {
	        geolocation = new AMap.Geolocation({enableHighAccuracy: true});
	        geolocation.getCurrentPosition();
	        AMap.event.addListener(geolocation, 'complete', onComplete);// 返回定位信息
	        AMap.event.addListener(geolocation, 'error', onError);      // 返回定位出错信息
	    });
	    // 解析定位结果
	    function onComplete(data) {
	        onSuccess(data.position.getLng(),data.position.getLat());
	    }
	}
var selectedSchool = document.getElementsByClassName('selected-school')[0];
var schoolListContainer = document.getElementsByClassName('school-list-container')[0];
var schoolComboBox = new SchoolComboBox(selectedSchool, schoolListContainer);
schoolComboBox.init();

window.onload = getPosition(function(longitude,latitude) {
	$.ajax({
        url: '/api/resource/school/nearby',
        method: 'POST',
        data: {latitude: latitude, longitude: longitude},
        success: function(ret){
			if(ret.success) {
				data = ret.data;
				var schools = new Array();
				ret.data.map(function(item){
					schools.push(item.name);
				});
				schoolComboBox.addSchools(schools);
			}
        },
        error: function(err){console.log(err)}
    })
}, function (e){alert('无法获取您的地理位置，请手动输入学校！');console.log(e);});

var student = $('#choice-student');
var teacher = $('#choice-teacher');
student.click(function () {
	student.css("color", "#cfcfcf");
	teacher.css("color", "#9d9d9d");
	userType.is_teacher = 0;
	gx.register.storageRegisterInfo(userType);
});
teacher.click(function () {
	student.css("color", "#9d9d9d");
	teacher.css("color", "#cfcfcf");
	userType.is_teacher = 1;
	gx.register.storageRegisterInfo(userType);
});

$('.selected-school').blur(function(e){
	setTimeout(function() {
		var school = e.target.value;
		schoolInfo.school_id = null;
		for(var i = 0; i < data.length; i++) {
			if(data[i]['name'] === school) {
				schoolInfo.school_id = data[i].id;
				gx.register.storageRegisterInfo(schoolInfo);
				schoolInfo.crawled = data[i]['crawled'];
				return;
			}
		}
	}, 300);
});

var timer;
$('.selected-school').keyup(function(e) {
	var school = e.target.value;
	if(school === '') return;
	clearTimeout(timer);
	timer = setTimeout(function() {
		$.ajax({
			url: '/api/resource/school/keyword',
			method: 'POST',
			data: {keyword: school},
			success: function(ret){
				if(ret.success) {
					data = ret.data;
					var schools = new Array();
					ret.data.map(function(item){
						schools.push(item.name);
					});
					schoolComboBox.addSchools(schools);
				}
				else {
					alert(ret.errorMessage);
				}
	        },
	        error: function(err) {
				alert('系统繁忙。。。');
				console.log(err);
			}
		})
	}, 300);
});

$('#next').click(function() {
	
	if(userType.is_teacher == null) {
		alert('请选择您的身份（学生或教师）！');
		return;
	}
	if(schoolInfo.school_id == null) {
		alert('您没有输入学校，或您输入的学校不存在，请重新输入！');
		return;
	}
	
	if(schoolInfo.crawled) {
		window.location.href = "/register/easLogin";
	}
	else {
		window.location.href = "/register/extInfo";
	}
	
});