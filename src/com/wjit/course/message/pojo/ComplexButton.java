package com.wjit.course.message.pojo;
/**
 * ���Ӱ�ť������ť��
 * 
 * @author WANGJIAN
 * @date 2015-12-11
 */
public class ComplexButton extends Button{
	 private Button[] sub_button; 

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] subButton) {
		sub_button = subButton;
	}
}
