package com.halfmoon.cloudmanager.response.check;

import com.halfmoon.cloudmanager.model.check.SignInCheck;
import com.halfmoon.cloudmanager.response.user.UserInfo;

public class SignInCheckInfo extends SignInCheck {
	
	private UserInfo creator;

	public UserInfo getCreator() {
		return creator;
	}

	public void setCreator(UserInfo creator) {
		this.creator = creator;
	}
	
}
