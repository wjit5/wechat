package com.wjit.course.message.resp;
/**
 * ������Ϣ
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class VoiceMessage extends BaseMessage{
	// ����
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
