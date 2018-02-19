package com.halfmoon.cloudmanager.controller.api.check.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.halfmoon.cloudmanager.dao.nosql.check.impl.CheckDao;
import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.response.OperationResultWithData;

@Controller
@RequestMapping("check")
public class CheckCodeController {
	
	@Autowired
	CheckDao checkDao;
	
	@ResponseBody
	@RequestMapping(path = "/code/{code}")
	public OperationResultWithData<CheckAndItsType>
	getCheckInfoByCode(HttpServletRequest request, @PathVariable("code") String code) {
		
		OperationResultWithData<CheckAndItsType> result = new OperationResultWithData<CheckAndItsType>();
		
		String classAndId = checkDao.getTypeAndIdByCode(code);
		if(classAndId == null) {
			result.setErrorMessage("此代码不存在或已过期！");
			return result;
		}
		
		String temp[] = classAndId.split(CheckDao.SEPARATOR);
		
		String beanId = temp[0].substring(0,1).toLowerCase() + temp[0].substring(1);
		int checkId = Integer.parseInt(temp[1]);
		
		ApplicationContext ac = (XmlWebApplicationContext) request.getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT");
		
		BaseDao<?> baseDao = (BaseDao<?>) ac.getBean(beanId);
		Object checkInfo = baseDao.get(checkId);
		result.setData(new CheckAndItsType(checkInfo, beanId));
		
		return result;
		
	}
	
	private class CheckAndItsType {
		
		@SuppressWarnings("unused")
		private Object checkInfo;
		@SuppressWarnings("unused")
		private String type = null;
		
		public CheckAndItsType(Object checkInfo, String type) {
			
			this.checkInfo = checkInfo;
			this.type = type.split("Check")[0].toLowerCase();
			
		}
		
	}

}
