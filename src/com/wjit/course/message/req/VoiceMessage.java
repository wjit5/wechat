package com.wjit.course.message.req;
/**
 * ��Ƶ��Ϣ
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class VoiceMessage extends BaseMessage{
	//ý��ID
	private String MediaId;
	//������ʽ
	private String Format;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	
}
