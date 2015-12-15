package com.wjit.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wjit.course.api.TulingApiProcess;
import com.wjit.course.message.resp.Article;
import com.wjit.course.message.resp.ImageMessage;
import com.wjit.course.message.resp.NewsMessage;
import com.wjit.course.message.resp.TextMessage;
import com.wjit.course.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class CoreService {
	/** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";  
            String respImage="";
  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");
            
            // 回复文本消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setContent("欢迎访问<a href=\"http://blog.csdn.net/lyq8479\">柳峰的博客</a>!");
            textMessage.setFuncFlag(0);  
            //将文本消息对象转换成xml字符串
            respMessage=MessageUtil.textMessageToXml(textMessage);
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	String content=requestMap.get("Content");
                //创建图文消息
            	NewsMessage newsMessage=new NewsMessage();
            	newsMessage.setToUserName(fromUserName);
            	newsMessage.setFromUserName(toUserName);
            	newsMessage.setCreateTime(new Date().getTime());
            	newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            	newsMessage.setFuncFlag(0);
                
                List<Article> articleList=new ArrayList<Article>();
                
                if("1".equals(content)){		//单图文消息
                	String picUrl="http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg";
                	Article article=new Article();
                	article.setTitle("微信公众号开发教程java版");
                	article.setDescription("java开发教程，认识更多的人");
                	article.setPicUrl(picUrl);
                	article.setUrl("http://blog.csdn.net/lyq8479");
                	articleList.add(article);
                	//设置图文消息个数
                	newsMessage.setArticleCount(articleList.size());
                	//设置图文消息包含的图文集合
                	newsMessage.setArticles(articleList);
                	//将图文消息对象转换成xml字符串
                	respMessage=MessageUtil.newsMessageToXml(newsMessage);
                }else if("2".equals(content)){		//单图文消息 ---不含图片
                	Article article=new Article();
                	article.setTitle("微信公众号开发教程java版");
                	//图文消息可以使用QQ表情、符号表情
                	article.setDescription(emoji(0x1F6B9)+"微信公众号java版开发教程1111111");
                	article.setPicUrl("");
                	article.setUrl("http://blog.csdn.net/lyq8479");
                	articleList.add(article);
                	newsMessage.setArticleCount(articleList.size());
                	newsMessage.setArticles(articleList);
                	respMessage=MessageUtil.newsMessageToXml(newsMessage);
                }else if("3".equals(content)){		//多图文消息
                	 Article article1 = new Article();  
                     article1.setTitle("微信公众帐号开发教程\n引言");  
                     article1.setDescription("");  
                     article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                     article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");  
   
                     Article article2 = new Article();  
                     article2.setTitle("第2篇\n微信公众帐号的类型");  
                     article2.setDescription("");  
                     article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                     article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");  
   
                     Article article3 = new Article();  
                     article3.setTitle("第3篇\n开发模式启用及接口配置");  
                     article3.setDescription("");  
                     article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                     article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");  
   
                     articleList.add(article1);  
                     articleList.add(article2);  
                     articleList.add(article3);  
                     newsMessage.setArticleCount(articleList.size());  
                     newsMessage.setArticles(articleList);  
                     respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }else if("4".equals(content)){		//多图文消息---首条消息不含图片
                	Article article1 = new Article();  
                    article1.setTitle("微信公众帐号开发教程Java版");  
                    article1.setDescription("");  
                    // 将图片置为空  
                    article1.setPicUrl("");  
                    article1.setUrl("http://blog.csdn.net/lyq8479");  
  
                    Article article2 = new Article();  
                    article2.setTitle("第4篇\n消息及消息处理工具的封装");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");  
  
                    Article article3 = new Article();  
                    article3.setTitle("第5篇\n各种消息的接收与响应");  
                    article3.setDescription("");  
                    article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");  
  
                    Article article4 = new Article();  
                    article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");  
                    article4.setDescription("");  
                    article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3);  
                    articleList.add(article4);  
                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                	
                }else if("5".equals(content)){		//多图文消息---最后一条消息不含图片
                	Article article1 = new Article();  
                    article1.setTitle("第7篇\n文本消息中换行符的使用");  
                    article1.setDescription("");  
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");  
  
                    Article article2 = new Article();  
                    article2.setTitle("第8篇\n文本消息中使用网页超链接");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");  
  
                    Article article3 = new Article();  
                    article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot");  
                    article3.setDescription("");  
                    // 将图片置为空  
                    article3.setPicUrl("");  
                    article3.setUrl("http://blog.csdn.net/lyq8479");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3);  
                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                }else{
                	  TextMessage textMessage2 = new TextMessage();  
                	  String apiresult=new TulingApiProcess().getTulingResult(content);
                      textMessage2.setToUserName(fromUserName);  
                      textMessage2.setFromUserName(toUserName);  
                      textMessage2.setCreateTime(new Date().getTime());  
                      textMessage2.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
                      textMessage2.setContent(apiresult);
                      textMessage.setFuncFlag(0);  
                      //将文本消息对象转换成xml字符串
                      respMessage=MessageUtil.textMessageToXml(textMessage);
                }
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "您发送的是图片消息！";  
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！";  
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "谢谢您的关注！";  
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                    String eventKey = requestMap.get("EventKey");  
  
                    if (eventKey.equals("11")) {  
                        respContent = "户外活动菜单项被点击！";  
                    } else if (eventKey.equals("12")) {  
                        respContent = "聚餐活动菜单项被点击！";  
                    } else if (eventKey.equals("13")) {  
                        respContent = "活动报名菜单项被点击！";  
                    } else if (eventKey.equals("14")) {  
                        respContent = "户外攻略菜单项被点击！";  
                    } else if (eventKey.equals("21")) {  
                        respContent = "笑话连篇菜单项被点击！";  
                    } else if (eventKey.equals("22")) {  
                        respContent = "推理故事菜单项被点击！";  
                    } else if (eventKey.equals("23")) {  
                        respContent = "游戏中心菜单项被点击！";  
                    } else if (eventKey.equals("31")) {  
                        respContent = "火星之家菜单项被点击！";  
                    } else if (eventKey.equals("32")) {  
                        respContent = "关于作者菜单项被点击！";  
                    } else if (eventKey.equals("33")) {  
                        respContent = "联系我们菜单项被点击！";  
                    }  
                }  
            }  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }

	private static String emoji(int i) {
		// TODO Auto-generated method stub
		return null;
	}  
}  