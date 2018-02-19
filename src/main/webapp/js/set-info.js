var data;

$('#logout').click(function(){
	if(window.confirm('你确定要退出登录吗？')){
		$.ajax({
			url: '/api/account/logout',
			method: 'GET',
			success: function(ret) {
				if(ret.success) {
					alert('您已退出登录！');
					window.location.href = '/login/wechat';
				}
				else alert('系统繁忙。。。');
			},
			error: function(ret) {
				console.log(ret);
			}
		});
	}
});



$('.stu-info-submit').click(function(){
    if(this.value === '完成'){
    	var form = document.getElementById('stu-info-form');
        var formData = gx.getFormData(form, ['name', 'academy', 'clazz', 'sysnum', 'building', 'room']);
    	formData.school_id = data.school.id;
        $.ajax({
        	url: '/api/account/modify/profile',
        	method: 'POST',
        	data: formData,
        	success: function(ret) {
        		if(ret.success) {
        			alert('个人信息修改成功！');
        			window.location.reload();
        		}
        		else {
        			alert(ret.errorMessage);
        			return;
        		}
        	},
        	error: function(ret) {
        		console.log(ret);
        	}
        });
        $('.stu-info-input').attr('disabled',true);
        this.value = '编辑';
    } else{
        $('.stu-info-input').attr('disabled',false);
        this.value = '完成';
    }
})

var getStudentInfo = function(){
	$.ajax({
		url: '/api/account/profile/self',
        method: 'GET',
        success: function(ret){
			if(ret.success) {
				data = ret.data;
				
				var icon = document.getElementById('icon');
				var name = document.getElementById('name');
				var school = document.getElementById('school');
				var academy = document.getElementById('academy');
				var clazz = document.getElementById('class');
				var building = document.getElementById('building');
				var room = document.getElementById('room');
				var sysnum = document.getElementById('sysnum');
				
				icon.src = typeof data.icon_url !== 'undefined' ? data.icon_url.replace('icon_small', 'icon_large') : icon.src;
				name.value = typeof data.name !== 'undefined' ? data.name : '';
				school.value = typeof data.school !== 'undefined' ? data.school.name : '';
				academy.value = typeof data.academy !== 'undefined' ? data.academy : '';
				clazz.value = typeof data.clazz !== 'undefined' ? data.clazz : '';
				sysnum.value = typeof data.sysnum !== 'undefined' ? data.sysnum : '';
				building.value = typeof data.building !== 'undefined' ? data.building : '';
				room.value = typeof data.room !== 'undefined' ? data.room : '';
			}
        },
        error: function(err){console.log(err)}
    })
};

var getStudentPhoto = function() {
	$.ajax({
		url: '/api/account/photo/self',
		method: 'GET',
		success: function(ret) {
			if(ret.success) {
				var data = ret.data;
				var photo = document.getElementById('stu-photo');
				if(typeof data !== 'undefined') {
					photo.src = data.url;
				}
			}
		}
	})
};

window.onload = function() {
	getStudentInfo();
	getStudentPhoto();
};

var uploadIcon = document.getElementById('upload-icon');
uploadIcon.addEventListener('change', function() {
	var formData = new FormData(document.forms.namedItem('upload-icon'));
	$.ajax({
		url: '/api/account/uploadIcon',
		method: "POST",
		data: formData,
		contentType: false,
        processData: false,
		success: function (ret) {
			if(ret.success) {
				alert('头像更换成功！');
				window.location.reload();
			}
			else {
				alert(ret.errorMessage);
			}
		},
		error: function (ret) {
		    console.log(ret);
		    alert('系统繁忙。。。');
		}
	});
});

var uploadPhoto = document.getElementById('upload-photo');
uploadPhoto.addEventListener('change', function() {
	var formData = new FormData(document.forms.namedItem('upload-photo'));
	$.ajax({
		url: '/api/account/uploadPhoto',
		method: 'POST',
		data: formData,
		contentType: false,
        processData: false,
        success: function (ret) {
			if(ret.success) {
				alert('签到照片更换成功！');
				window.location.reload();
			}
			else {
				alert(ret.errorMessage);
			}
		},
		error: function (ret) {
		    console.log(ret);
		    alert('');
		}
	});
})
