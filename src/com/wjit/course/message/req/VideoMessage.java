package com.wjit.course.message.req;
/**
 * ��Ƶ��Ϣ
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class VideoMessage {
	// ��Ƶ��Ϣý��id
	private String MediaId;
	// ��Ƶ��Ϣ����ͼ��ý��id
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}

