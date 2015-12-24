package com.wjit.course.message.pojo;

public class Prize {

	private int id;
	private String pri_id;
	private String pri_name;
	private int pri_amount;
	private String pri_des;
	private String pri_flag;
	private String pri_price;
	private String pri_value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPri_id() {
		return pri_id;
	}
	public void setPri_id(String priId) {
		pri_id = priId;
	}
	public String getPri_name() {
		return pri_name;
	}
	public void setPri_name(String priName) {
		pri_name = priName;
	}
	public int getPri_amount() {
		return pri_amount;
	}
	public void setPri_amount(int priAmount) {
		pri_amount = priAmount;
	}
	public String getPri_des() {
		return pri_des;
	}
	public void setPri_des(String priDes) {
		pri_des = priDes;
	}
	public String getPri_flag() {
		return pri_flag;
	}
	public void setPri_flag(String priFlag) {
		pri_flag = priFlag;
	}
	public String getPri_price() {
		return pri_price;
	}
	public void setPri_price(String priPrice) {
		pri_price = priPrice;
	}
	public String getPri_value() {
		return pri_value;
	}
	public void setPri_value(String priValue) {
		pri_value = priValue;
	}
	
	public Prize() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prize(int id, String priId, String priName, int priAmount,
			String priDes, String priFlag, String priPrice, String priValue) {
		super();
		this.id = id;
		pri_id = priId;
		pri_name = priName;
		pri_amount = priAmount;
		pri_des = priDes;
		pri_flag = priFlag;
		pri_price = priPrice;
		pri_value = priValue;
	}
	
} 
