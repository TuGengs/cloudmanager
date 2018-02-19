package com.halfmoon.cloudmanager.controller.api.wechat;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import weixin.popular.bean.message.EventMessage;
import weixin.popular.util.XMLConverUtil;

@Controller
@RequestMapping("wechat")
public class WeController {
	
	
	@RequestMapping(path="",method={RequestMethod.GET,RequestMethod.POST})
	public void handle(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ServletInputStream inputStream = request.getInputStream();
		
		if(inputStream != null){
			EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
			String key = eventMessage.getFromUserName() + "__"
					   + eventMessage.getToUserName() + "__"
					   + eventMessage.getMsgType() + "__"
					   + eventMessage.getCreateTime();
			String scene_id = eventMessage.getEventKey();
			if(scene_id != null && !scene_id.equals("")){
				response.sendRedirect("www.gxykq.com/check#"+scene_id);
			}
		}
		
	}
}
