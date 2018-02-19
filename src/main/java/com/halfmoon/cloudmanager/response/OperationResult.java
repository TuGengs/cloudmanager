package com.halfmoon.cloudmanager.response;

/**
 * 返回给前端的操作结果，包括是否成功、出错信息
 * 和相关数据，均为JSON
 * 
 * @author hzq
 *
 */
public class OperationResult {

	protected boolean success = false;
	protected String errorMessage = null;
	
	/**
	 * 默认调用无参构造函数，成功信号为true
	 */
	public OperationResult() {
		this.success = true;
	}
	
	/**
	 * 若失败，则传入出错信息
	 * @param errorMessage 出错信息
	 */
	public OperationResult(String errorMessage) {
		this.setErrorMessage(errorMessage);
	}
	
	public void setErrorMessage(String errorMessage) {
		this.success = false;
		this.errorMessage = errorMessage;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
