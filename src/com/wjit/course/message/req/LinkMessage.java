package com.wjit.course.message.req;
/**
 * ������Ϣ
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class LinkMessage extends BaseMessage{
	
	//��Ϣ����
	private String Title;
	//��Ϣ����
	private String Description;
	//��Ϣ����
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}

}
