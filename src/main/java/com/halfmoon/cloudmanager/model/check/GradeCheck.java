package com.halfmoon.cloudmanager.model.check;

/**
 * @author LITAO
 * @time 上午10:29:02  2017年3月11日
 * @info 
 * @修改 该类修改为与SignInCheck公用一个父类，继承SignInCheck中的字段
 */
public class GradeCheck extends Check {
	
	public GradeCheck(){
		super();
	}
	/**
	 * 具体实施检查的人id
	 */
	private int emp_id;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
}