package com.halfmoon.cloudmanager.response.check;

import java.util.List;

import com.halfmoon.cloudmanager.model.check.GradeCheck;
import com.halfmoon.cloudmanager.model.check.GradingItem;
import com.halfmoon.cloudmanager.response.user.UserInfo;

public class GradeCheckInfo extends GradeCheck {

	private UserInfo creator;
	private List<GradingItem> grading_items;

	public UserInfo getCreator() {
		return creator;
	}

	public void setCreator(UserInfo creator) {
		this.creator = creator;
	}

	public List<GradingItem> getGrading_items() {
		return grading_items;
	}

	public void setGrading_items(List<GradingItem> grading_items) {
		this.grading_items = grading_items;
	}

}
