package com.halfmoon.cloudmanager.util;

import java.util.List;

import com.halfmoon.cloudmanager.model.check.dto.UInfo;

public class Bsearch {
	
	public static UInfo bsearch(List<UInfo> list,int low,int high,int target){
		while(low <= high){
			int mid = low + (high - low)/2;
			if(list.get(mid).getUser_id() > target){
				high = mid - 1;
			}else if(list.get(mid).getUser_id() < target){
				low = mid + 1;
			}else{
				return list.get(mid);
			}
		}
		return null;
	}
}
