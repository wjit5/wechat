package com.wjit.course.message.resp;
/**
 * 视频消息
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class VideoMessage extends BaseMessage{
	// 视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
