package com.halfmoon.cloudmanager.controller.api;

/**
 * 一个专门用来处理错误的controller
 * 全局处理各种错误
 * @author hzq
 *
 */
import java.sql.SQLException;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.halfmoon.cloudmanager.response.OperationResult;

import redis.clients.jedis.exceptions.JedisException;

@ControllerAdvice
public class GlobalExceptionHandlerController {
	
	/**
	 * 数据库相关错误
	 * @return 错误提示信息
	 */
	@ExceptionHandler(value={SQLException.class, JedisException.class})
	@ResponseBody
	public OperationResult handleDatabaseException(Exception e) {
		
		e.printStackTrace();
		return new OperationResult("系统繁忙，请稍候重试！");
		
	}
	
	/**
	 * 数据绑定错误，一般是请求提交的数据与后台的数据不符
	 * 例如属性名不同、数据类型不同等
	 * 空指针错误，一般是请求中没有带上该带的参数
	 * @return 错误提示信息
	 */
	@ExceptionHandler(value={BindException.class, NullPointerException.class, IllegalArgumentException.class})
	@ResponseBody
	public OperationResult handleBindException(Exception e) {

		e.printStackTrace();
		return new OperationResult("非法提交！");

	}
	
	/**
	 * 图片大小超过限制的错误
	 * 其具体数值可以在web.xml中配置
	 * @return 错误提示信息
	 */
	@ExceptionHandler(value={FileSizeLimitExceededException.class})
	@ResponseBody
	public OperationResult handleFileException(Exception e) {

		e.printStackTrace();
		return new OperationResult("文件过大，图片文件请不要超过5M！");

	}
	
}
