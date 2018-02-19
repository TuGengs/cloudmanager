package com.halfmoon.cloudmanager.common;

import java.text.SimpleDateFormat;
/**
 * 
 * @author xiaogao.XU
 * 一些常用的常量
 */
public class Constant {
		// 创建者为1 辅助检查为0
		public static final int CREATOR = 1;
		public static final int FOLLOWER = 0;
		//是否允许添加学生 1为允许  0为不允许
		public static final int ALLOW = 1;
		public static final int NOT_ALLOW = 0;
		// 分享的类别 1为辅助签到 0为查看检查
		public static final int HELPER = 1;
		public static final int LOOKER = 0;
		// 扣分项是对个人还是团体
		public static final int IS_TO_SINGLE = 1;
		public static final int NOT_TO_SINGLE = 0;
		// 扣分检查和签到检查
		public static final int GRADE_CHECK = 0;
		public static final int SIGN_IN_CHECK = 1;
		// 检查是否开启
		public static final int IS_OPEN = 1;
		public static final int NOT_OPNE = 0;
		// 缺到、迟到、 、到了 
		public static final int LACK = 0;
		public static final int LATE = 1;
		public static final int LEAVE = 2;
		public static final int IS_HERE = 3;
		
		
		public static final int DISTANCE = 1000;
		public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
}

