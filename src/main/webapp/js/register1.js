var rgHandle = (function(){
    var TIME_COUNT = 60;
    var form = document.getElementById('form'),
        verifyBtn = document.getElementById('get_code'),
        nextBtn = document.getElementById('next'),
        regNumber = document.getElementById('reg_number'),
        verifyCode = document.getElementById('verify_code'),
        time = TIME_COUNT;
    function initVerifyBtn(){
        verifyBtn.value = '获取验证码';
        verifyBtn.disabled = false;
    }
    verifyBtn.addEventListener('click',function requestVerifyCode(){
    	var tel = regNumber.value;
    	if(!/^1[34578]\d{9}$/.test(tel)) {
        	alert('请输入11位手机号！');
        	return;
        }
    	
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
    	$.ajax({
        	url: "/api/account/checkTel",
        	method: 'POST',
        	data: {tel: tel},
        	success: function(ret) {
        		if(ret.success) {
        			if(ret.data) {
        				alert('该手机号已被注册！');
        				return;
        			}else {
            			send();
            		}
        		}
        		else {
        			alert(ret.errorMessage);
        		}
        	},
        	error: function (e) {
        		console.log(e);
        		alert('系统繁忙。。。');
        	}
        });
    });
    verifyCode.addEventListener('blur', function veridyCodeBlur(){
        var verify = verifyCode.value.trim();
        if(verify.length === 6){
            $.ajax({
                url: '/api/auth/captcha/sms/verify',
                method: 'POST',
                data: {userInput: verify},
                error: function(err){
                    alert('系统繁忙。。。');
                }
            })
        }
    });
    nextBtn.addEventListener('click', function nextBtnClick(){
        var userInfo = gx.getFormData(form,['tel','password']);
        var userInput = verifyCode.value;
        if(!/^1[34578]\d{9}$/.test(userInfo.tel)) {
        	alert('请输入11位手机号！')
        	return;
        }
        if(userInfo.password === '') {
        	alert('请输入密码！');
        	return;
        }
        if(userInfo.password.length < 6 || userInfo.password.length > 20) {
        	alert('密码长度不少于6位，不多于20位。')
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
            },
            success: function(data){
                if(data.success){
                	gx.register.storageRegisterInfo(userInfo);
                    window.location.href = '/register/schoolInfo';
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
})();