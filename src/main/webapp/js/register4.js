var submitBtn = document.getElementById("submit");
var form = document.getElementById('form');

submitBtn.addEventListener('click', function() {
	submitBtn.style.disabled = true;
	var data = gx.getFormData(form, ['sysnum', 'eas_password']);
	
	if(data.sysnum === '') {
		alert('请输入教务系统账号！');
		return;
	}
	if(data.eas_password === '') {
		alert('请输入教务系统密码！');
		return;
	}
	
	var register =  gx.register.getRegisterInfo();
	$.ajax({
		url: '/api/account/EASRegister',
		method: 'POST',
		data: Object.assign(data, register),
		success: function(data) {
			if(data.success) {
				alert('注册成功！');
				location.href = '/login/wechat';
			}
			else {
				alert(data.errorMessage);
				submitBtn.style.disabled = false;
			}
		},
		error: function() {
			submitBtn.style.disabled = false;
		}
	});
	
});