package com.wjit.course.message.event;
/**
 * �ϱ�����λ���¼�
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class LocationEvent {
	// ����λ��γ��
	private String Latitude;
	// ����λ�þ���
	private String Longitude;
	// ����λ�þ���
	private String Precision;

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}
}
