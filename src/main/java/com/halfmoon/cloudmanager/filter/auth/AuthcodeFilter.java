package com.halfmoon.cloudmanager.filter.auth;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.halfmoon.cloudmanager.response.OperationResult;
import com.halfmoon.cloudmanager.util.authcode.AuthcodeInfo;

/**
 * Servlet Filter implementation class AuthcodeFilter
 */

@WebFilter(urlPatterns = { "/api/account/register", "/api/account/modify/password", "/api/account/EASRegister" })
public class AuthcodeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthcodeFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");

		// AuthcodeInfo pictureAuthcode = (AuthcodeInfo) session.getAttribute("pictureAuthcode");
		AuthcodeInfo smsAuthcode = (AuthcodeInfo) session.getAttribute("smsAuthcode");

		// 如果提交时没有验证码的信息或验证码未验证
		// 则说明此次提交为恶意提交
		if (// pictureAuthcode == null || !pictureAuthcode.verified ||
				smsAuthcode == null || !smsAuthcode.verified) {
			response.getWriter().write(new Gson().toJson(new OperationResult("非法提交！")).toString());
			return;
		}

		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
