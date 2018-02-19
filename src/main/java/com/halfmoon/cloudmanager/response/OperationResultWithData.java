package com.halfmoon.cloudmanager.response;

/**
 * 前端执行带数据的操作时，使用这个类，将数据放入data变量中，
 * 同样若获取失败同样包含出错信息，使用方法同OperationResult
 * @author hzq
 *
 * @param <DataType> 数据类型
 * @see com.halfmoon.cloudmanager.response.OperationResult
 */
public class OperationResultWithData<DataType> extends OperationResult {
	
	// 目标数据存放在此data变量中
	protected DataType data = null;
	
	public OperationResultWithData() {
		// 空构造方法
	}
	
	public OperationResultWithData(String errorMessage) {
		super.setErrorMessage(errorMessage);
	}
	
	/**
	 * 将取得的数据放入结果对象中
	 * @param data 目标数据
	 */
	public void setData(DataType data) {
		this.success = true;
		this.data = data;
	}

	public DataType getData() {
		return data;
	}
	
}
