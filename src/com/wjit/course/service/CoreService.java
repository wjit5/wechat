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
 * ���ķ�����
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class CoreService {
	/** 
     * ����΢�ŷ��������� 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // Ĭ�Ϸ��ص��ı���Ϣ����  
            String respContent = "�������쳣�����Ժ��ԣ�";  
            String respImage="";
  
            // xml�������  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // ���ͷ��ʺţ�open_id��  
            String fromUserName = requestMap.get("FromUserName");  
            // �����ʺ�  
            String toUserName = requestMap.get("ToUserName");  
            // ��Ϣ����  
            String msgType = requestMap.get("MsgType");
            
            // �ظ��ı���Ϣ  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setContent("��ӭ����<a href=\"http://blog.csdn.net/lyq8479\">����Ĳ���</a>!");
            textMessage.setFuncFlag(0);  
            //���ı���Ϣ����ת����xml�ַ���
            respMessage=MessageUtil.textMessageToXml(textMessage);
            // �ı���Ϣ  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	String content=requestMap.get("Content");
                //����ͼ����Ϣ
            	NewsMessage newsMessage=new NewsMessage();
            	newsMessage.setToUserName(fromUserName);
            	newsMessage.setFromUserName(toUserName);
            	newsMessage.setCreateTime(new Date().getTime());
            	newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            	newsMessage.setFuncFlag(0);
                
                List<Article> articleList=new ArrayList<Article>();
                
                if("1".equals(content)){		//��ͼ����Ϣ
                	String picUrl="http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg";
                	Article article=new Article();
                	article.setTitle("΢�Ź��ںſ����̳�java��");
                	article.setDescription("java�����̳̣���ʶ�������");
                	article.setPicUrl(picUrl);
                	article.setUrl("http://blog.csdn.net/lyq8479");
                	articleList.add(article);
                	//����ͼ����Ϣ����
                	newsMessage.setArticleCount(articleList.size());
                	//����ͼ����Ϣ������ͼ�ļ���
                	newsMessage.setArticles(articleList);
                	//��ͼ����Ϣ����ת����xml�ַ���
                	respMessage=MessageUtil.newsMessageToXml(newsMessage);
                }else if("2".equals(content)){		//��ͼ����Ϣ ---����ͼƬ
                	Article article=new Article();
                	article.setTitle("΢�Ź��ںſ����̳�java��");
                	//ͼ����Ϣ����ʹ��QQ���顢���ű���
                	article.setDescription(emoji(0x1F6B9)+"΢�Ź��ں�java�濪���̳�1111111");
                	article.setPicUrl("");
                	article.setUrl("http://blog.csdn.net/lyq8479");
                	articleList.add(article);
                	newsMessage.setArticleCount(articleList.size());
                	newsMessage.setArticles(articleList);
                	respMessage=MessageUtil.newsMessageToXml(newsMessage);
                }else if("3".equals(content)){		//��ͼ����Ϣ
                	 Article article1 = new Article();  
                     article1.setTitle("΢�Ź����ʺſ����̳�\n����");  
                     article1.setDescription("");  
                     article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                     article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");  
   
                     Article article2 = new Article();  
                     article2.setTitle("��2ƪ\n΢�Ź����ʺŵ�����");  
                     article2.setDescription("");  
                     article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                     article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");  
   
                     Article article3 = new Article();  
                     article3.setTitle("��3ƪ\n����ģʽ���ü��ӿ�����");  
                     article3.setDescription("");  
                     article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                     article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");  
   
                     articleList.add(article1);  
                     articleList.add(article2);  
                     articleList.add(article3);  
                     newsMessage.setArticleCount(articleList.size());  
                     newsMessage.setArticles(articleList);  
                     respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }else if("4".equals(content)){		//��ͼ����Ϣ---������Ϣ����ͼƬ
                	Article article1 = new Article();  
                    article1.setTitle("΢�Ź����ʺſ����̳�Java��");  
                    article1.setDescription("");  
                    // ��ͼƬ��Ϊ��  
                    article1.setPicUrl("");  
                    article1.setUrl("http://blog.csdn.net/lyq8479");  
  
                    Article article2 = new Article();  
                    article2.setTitle("��4ƪ\n��Ϣ����Ϣ�����ߵķ�װ");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");  
  
                    Article article3 = new Article();  
                    article3.setTitle("��5ƪ\n������Ϣ�Ľ�������Ӧ");  
                    article3.setDescription("");  
                    article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");  
  
                    Article article4 = new Article();  
                    article4.setTitle("��6ƪ\n�ı���Ϣ�����ݳ������ƽ���");  
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
                	
                }else if("5".equals(content)){		//��ͼ����Ϣ---���һ����Ϣ����ͼƬ
                	Article article1 = new Article();  
                    article1.setTitle("��7ƪ\n�ı���Ϣ�л��з���ʹ��");  
                    article1.setDescription("");  
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");  
  
                    Article article2 = new Article();  
                    article2.setTitle("��8ƪ\n�ı���Ϣ��ʹ����ҳ������");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");  
  
                    Article article3 = new Article();  
                    article3.setTitle("����������¶���������������ͨ���������Ի��ע΢�Ź����ʺ�xiaoqrobot");  
                    article3.setDescription("");  
                    // ��ͼƬ��Ϊ��  
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
                      //���ı���Ϣ����ת����xml�ַ���
                      respMessage=MessageUtil.textMessageToXml(textMessage);
                }
            }  
            // ͼƬ��Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "�����͵���ͼƬ��Ϣ��";  
            }  
            // ����λ����Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "�����͵��ǵ���λ����Ϣ��";  
            }  
            // ������Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "�����͵���������Ϣ��";  
            }  
            // ��Ƶ��Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "�����͵�����Ƶ��Ϣ��";  
            }  
            // �¼�����  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // �¼�����  
                String eventType = requestMap.get("Event");  
                // ����  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "лл���Ĺ�ע��";  
                }  
                // ȡ������  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ  
                }  
                // �Զ���˵�����¼�  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ  
                    String eventKey = requestMap.get("EventKey");  
  
                    if (eventKey.equals("11")) {  
                        respContent = "�����˵�������";  
                    } else if (eventKey.equals("12")) {  
                        respContent = "�۲ͻ�˵�������";  
                    } else if (eventKey.equals("13")) {  
                        respContent = "������˵�������";  
                    } else if (eventKey.equals("14")) {  
                        respContent = "���⹥�Բ˵�������";  
                    } else if (eventKey.equals("21")) {  
                        respContent = "Ц����ƪ�˵�������";  
                    } else if (eventKey.equals("22")) {  
                        respContent = "������²˵�������";  
                    } else if (eventKey.equals("23")) {  
                        respContent = "��Ϸ���Ĳ˵�������";  
                    } else if (eventKey.equals("31")) {  
                        respContent = "����֮�Ҳ˵�������";  
                    } else if (eventKey.equals("32")) {  
                        respContent = "�������߲˵�������";  
                    } else if (eventKey.equals("33")) {  
                        respContent = "��ϵ���ǲ˵�������";  
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