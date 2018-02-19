package com.halfmoon.cloudmanager.filter.sms;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.halfmoon.cloudmanager.dao.nosql.tel.impl.TelDao;
import com.halfmoon.cloudmanager.response.OperationResult;

/**
 * 拦截发送短息的请求，判断是否可以发送
 * @author hzq
 *
 */
@WebFilter("/api/auth/captcha/sms")
public class SmsFilter implements Filter {
	
	private TelDao telDao;
	
	private static final int MAX_SENDNUM_PER_DAY = 10;	// 每天最多发送的条数
	private static final int TIME_SPAN = 60;			// 两次发送的最短间隔时间（秒）

    /**
     * Default constructor. 
     */
    public SmsFilter() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		
		String tel = request.getParameter("tel");
		
		ArrayList<String> sentNumAndTime = telDao.getSentNumAndTime(tel);
		String temp1 = sentNumAndTime.get(0);
		String temp2 = sentNumAndTime.get(1);
		
		long now = System.currentTimeMillis() / 1000;
		int sentNum = temp1 == null ? 0 : Integer.parseInt(temp1);
		long lastSentTime = temp2 == null ? 0 : Long.parseLong(temp2);
		
		// 发送的条数达到上限
		if(sentNum >= MAX_SENDNUM_PER_DAY) {
			response.getWriter().write(new Gson().toJson(
					new OperationResult("该手机号发送验证码的次数过多，请24小时后再试！")).toString());
			return;
		}
		// 和上一次发送的时间差小于规定间隔
		else if (now - lastSentTime < TIME_SPAN) {
			response.getWriter().write(new Gson().toJson(
					new OperationResult("该手机号发送验证码过于频繁，请稍候再试！")).toString());
			return;
		}
		else {
			telDao.incrSentNum(tel, sentNum==0);	// 是否是24小时内第一次发送短信
			chain.doFilter(request, response);
		}
			
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		// 从StackOverflow上看到的
		// filter的加载要比SpringMVC的bean优先，filter里的bean无法加载
		// 导致filter里无法通过注解来获取bean，都是null
		// 所以只能这样获取bean、好搓啊，没办法，毕竟filter是原生的，安全一点
		
		ApplicationContext ctx = WebApplicationContextUtils
			      .getRequiredWebApplicationContext(fConfig.getServletContext());
			    this.telDao = ctx.getBean(TelDao.class);
		
	}

}
