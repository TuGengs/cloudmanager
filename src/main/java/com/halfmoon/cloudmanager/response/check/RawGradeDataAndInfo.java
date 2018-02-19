package com.halfmoon.cloudmanager.response.check;

import java.util.HashMap;
import java.util.List;

public class RawGradeDataAndInfo {

	// 该分值检查的相关信息
	private GradeCheckInfo gradeCheckInfo;
	// 用来存放该分值检查的加减分总情况
	private List<HashMap<String, Object>> totalResult;

	public GradeCheckInfo getGradeCheckInfo() {
		return gradeCheckInfo;
	}

	public void setGradeCheckInfo(GradeCheckInfo gradeCheckInfo) {
		this.gradeCheckInfo = gradeCheckInfo;
	}

	public List<HashMap<String, Object>> getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(List<HashMap<String, Object>> totalResult) {
		this.totalResult = totalResult;
	}

}
