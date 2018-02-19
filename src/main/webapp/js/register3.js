var next = document.getElementById('next_btn'), 
	form = document.getElementById('form');
next.addEventListener('click', function register() {
	
	var data = gx.getFormData(form, [ 'name', 'academy', 'clazz', 'sysnum',
			'building', 'room' ]);
	var register = gx.register.getRegisterInfo();
	
	if(data.name === '') {
		alert("请输入姓名！");
		return;
	}
	if(data.academy === '') {
		alert("请输入学院！");
		return;
	}
	if(data.class === '') {
		alert("请输入班级！");
		return;
	}
	if(data.sysnum === '') {
		alert("请输入学号！");
		return;
	}
	if(data.building === '') {
		alert("请输入楼栋！");
		return;
	}
	if(data.room === '') {
		alert("请输入寝室号！");
		return;
	}
	
	console.log(Object.assign(data, register));
	
	$.ajax({
		method : 'POST',
		url : '/api/account/register',
		data : Object.assign(data, register),
		success : function(data) {
			if(data.success) {
				gx.register.deleteRegisterInfo();
				alert("注册成功！");
				window.location.href = '/login/wechat';
			}
			else
				alert(data.errorMessage);
		},
		error : function(err) {
			alert('系统繁忙。。。');
		}
	});

});
