package com.wjit.course.message.resp;
/**
 * 文本消息
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class TextMessage extends BaseMessage{
	//回复消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
