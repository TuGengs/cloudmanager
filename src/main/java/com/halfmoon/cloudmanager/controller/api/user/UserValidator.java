package com.halfmoon.cloudmanager.controller.api.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.halfmoon.cloudmanager.model.user.User;

/**
 * 用于验证用户输入的信息是否符合我们的规定
 * @author hzq
 *
 */
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.equals(clazz);
		
	}

	@Override
	public void validate(Object obj, Errors e) {
		
		User user = (User) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "school_id", null);
		
		if(user.getName().length() > 5) {
			e.reject("名字过长");
		}
		
		if(user.getPassword() != null) {
			if(user.getPassword().length() < 6 || user.getPassword().length() > 20) {
				e.reject("密码长度不少于6位，不多于20位。");
			}
		}

	}

}
