package com.wjit.course.message.req;
/**
 * ����λ����Ϣ
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class LocationMessage extends BaseMessage{
	
	//����λ��ά��
	private String Location_X;
	//����λ�þ���
	private String Location_Y;
	//��ͼ���Ŵ�С
	private String Scale;
	//����λ����Ϣ
	private String Label;
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String locationX) {
		Location_X = locationX;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String locationY) {
		Location_Y = locationY;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	
	
}
