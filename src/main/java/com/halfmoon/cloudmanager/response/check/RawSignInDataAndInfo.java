package com.halfmoon.cloudmanager.response.check;

import java.util.HashMap;
import java.util.List;

public class RawSignInDataAndInfo {

	// 该课程的相关信息
	private SignInCheckInfo signInCheckInfo;
	// 用来存放该课程签到的总情况
	private List<HashMap<String, Object>> totalResult;

	public SignInCheckInfo getSignInCheckInfo() {
		return signInCheckInfo;
	}

	public void setSignInCheckInfo(SignInCheckInfo signInCheckInfo) {
		this.signInCheckInfo = signInCheckInfo;
	}

	public List<HashMap<String, Object>> getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(List<HashMap<String, Object>> totalResult) {
		this.totalResult = totalResult;
	}

}
