package com.wjit.course.message.resp;


 /**
  * 消息信息
  * @author WANGJIAN
  * @date 2015-12-8
  */
public class MusicMessage extends BaseMessage {  
    // 音乐  
    private Music Music;  
  
    public Music getMusic() {  
        return Music;  
    }  
  
    public void setMusic(Music music) {  
        Music = music;  
    }  
}  