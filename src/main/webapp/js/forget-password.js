var rgHandle = (function(){
    var TIME_COUNT = 60;
    var form = document.getElementById('form'),
        verifyBtn = document.getElementById('get_code'),
        finishBtn = document.getElementById('finish'),
        regNumber = document.getElementById('reg_number'),
        verifyCode = document.getElementById('verify_code'),
        time = TIME_COUNT;
    function initVerifyBtn(){
        verifyBtn.value = '获取验证码';
        verifyBtn.disabled = false;
    }
    verifyBtn.addEventListener('click',function requestVerifyCode() {
    	var tel = regNumber.value;
    	function send() {
    		if(time === TIME_COUNT){ 
                verifyBtn.value = '发送中...';
                $.ajax({
                    url : "/api/auth/captcha/sms",
                    method: 'POST',
                    data: {tel: tel},
                    success: function(data){
                        verifyBtn.disabled = true;
                        if(data.success){
                            var timer = setInterval(function countBackwards(){
                                if(time < 1){
                                    time = TIME_COUNT;
                                    initVerifyBtn();
                                    return clearInterval(timer);
                                }
                                verifyBtn.value = time--;
                            },1000)
                        } else{
                            initVerifyBtn();
                            alert(data.errorMessage);
                        }
                    },
                    error: function(err){
                        initVerifyBtn();
                        alert('系统繁忙。。。');
                    }
                });
            }
    	}
    	if(!/^1[34578]\d{9}$/.test(tel)) {
        	alert('请输入11位手机号！');
        	return;
        }
    	$.ajax({
        	url: "/api/account/checkTel",
        	method: 'POST',
        	data: {tel: tel},
        	success: function(ret) {
        		if(ret.success) {
        			if(!ret.data) {
        				alert('该手机号未被注册！');
        				return;
        			}
        			else {
        				send();
        			}
        		}
        	},
        	error: function (e) {
        		console.log(e);
        		alert('系统繁忙。。。');
        	}
        });
    });
    finishBtn.addEventListener('click', function finishBtnClick(){
        var formData = gx.getFormData(form, ['tel','new_password', 'password_repeat']);
        var userInput = verifyCode.value;
        if(!/^1[34578]\d{9}$/.test(formData.tel)) {
        	alert('请输入11位手机号！')
        	return;
        }
        if(formData.new_password === '') {
        	alert('请输入密码！');
        	return;
        }
        if(formData.new_password !== formData.password_repeat) {
            alert('两次输入的密码不一致！');
        	return;
        }
        if(userInput === '') {
        	alert('请输入验证码！');
        	return;
        }
        $.ajax({
            url: '/api/auth/captcha/sms/verify',
            method: 'POST',
            data: {userInput: userInput},
            error: function(err){
                alert('系统繁忙。。。');
                return;
            },
            success: function(data){
                if(data.success){
                    $.ajax({
                        url: '/api/account/reset/password',
                        method: 'POST',
                        data: formData,
                        success: function(ret) {
                            if(ret.success) {
                                alert('密码已重置，请重新登录。');
                                window.location.href = '/login';
                            }
                            else {
                                alert(ret.errorMessage);
                            }
                        },
                        error: function(e) {
                            console.log(e);
                            alert('系统繁忙。。。');
                        }
                    });
                } else{
                    alert(data.errorMessage);
                }
            },
            error: function(e) {
                console.log(e);
                alert('系统繁忙。。。');
            }
        })
    })
    
    //return 暂时不需要
})()