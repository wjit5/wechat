package com.wjit.course.message.event;
/**
 * 自定义菜单事件
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class MenuEvent {
	// 事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
