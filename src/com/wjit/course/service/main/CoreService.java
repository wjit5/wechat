package com.wjit.course.service.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wjit.course.message.resp.Article;
import com.wjit.course.message.resp.NewsMessage;
import com.wjit.course.message.resp.TextMessage;
import com.wjit.course.service.activity.ChoujiangService;
import com.wjit.course.util.main.MessageUtil;
import com.wjit.course.util.main.WeixinUtil;

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
			String respImage = "";

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
			textMessage.setContent("��ӭ��ע�����ˡ�����\n"+WeixinUtil.getMainMenu());
			textMessage.setFuncFlag(0);
			// ���ı���Ϣ����ת����xml�ַ���
			respMessage = MessageUtil.textMessageToXml(textMessage);
			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				List<Object> list=new ArrayList<Object>();
				list.add(fromUserName);
				list.add(toUserName);
				list.add(content);
				// ����ͼ����Ϣ
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);
				List<Article> articleList = new ArrayList<Article>();
				if (WeixinUtil.isMenuNumer(content)){//�ظ��˵�
					articleList=WeixinUtil.getTextResp(list);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if(WeixinUtil.isQqFace(content)){//�ظ�����
					// �ظ��ı���Ϣ  
			        TextMessage textMessage2 = new TextMessage();  
			        textMessage2.setToUserName(fromUserName);  
			        textMessage2.setFromUserName(toUserName);  
			        textMessage2.setCreateTime(new Date().getTime());  
			        textMessage2.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
			        textMessage2.setFuncFlag(0);  
			        // �û���ʲôQQ���飬�ͷ���ʲôQQ����  
			        textMessage.setContent(content);  
			        // ���ı���Ϣ����ת����xml�ַ���  
			        respMessage = MessageUtil.textMessageToXml(textMessage); 
				}else if("�˵�".equals(content)||"����".equals(content)||"?".equals(content)||"��".equals(content)){
					TextMessage textMessage3 = new TextMessage();
					textMessage3.setToUserName(fromUserName);
					textMessage3.setFromUserName(toUserName);
					textMessage3.setCreateTime(new Date().getTime());
					textMessage3.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage3.setContent("��ӭ��עС��������\n"+WeixinUtil.getMainMenu());
					textMessage3.setFuncFlag(0);
					// ���ı���Ϣ����ת����xml�ַ���
					respMessage = MessageUtil.textMessageToXml(textMessage3);
				}else if("�齱".equals(content)||"����".equals(content)||"ʥ������".equals(content)){
					TextMessage textMessage4 = new TextMessage();
					String choujResult=new ChoujiangService().choujMethod(content);
					textMessage4.setToUserName(fromUserName);
					textMessage4.setFromUserName(toUserName);
					textMessage4.setCreateTime(new Date().getTime());
					textMessage4.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage4.setContent("");
					textMessage4.setFuncFlag(0);
					// ���ı���Ϣ����ת����xml�ַ���
					respMessage = MessageUtil.textMessageToXml(textMessage4);
				} else {
					// ��������
					TextMessage textMessage3 = new TextMessage();
					String apiresult = new TulingService().getTulingResult(content);
					textMessage3.setToUserName(fromUserName);
					textMessage3.setFromUserName(toUserName);
					textMessage3.setCreateTime(new Date().getTime());
					textMessage3.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage3.setContent(apiresult);
					textMessage3.setFuncFlag(0);
					// ���ı���Ϣ����ת����xml�ַ���
					respMessage = MessageUtil.textMessageToXml(textMessage3);
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
					respContent = "��ӭ��ӭ";
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ
					String eventKey = requestMap.get("EventKey");
					// ����ͼ����Ϣ
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setFuncFlag(0);
					List<Article> articleList1 = new ArrayList<Article>();
					if (eventKey.equals("11")) {
						// �����б�
						Article article1 = new Article();
						article1.setTitle("�������");
						article1.setDescription("����");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://www.baidu.com");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
					} else if (eventKey.equals("12")) {
						// �۲ͻ�б�
						Article article1 = new Article();
						article1.setTitle("�۲ͱ���");
						article1.setDescription("����");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://www.baidu.com");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "�۲ͻ�˵�������";
					} else if (eventKey.equals("13")) {
						Article article1 = new Article();
						article1.setTitle("�����μ�");
						article1.setDescription("����");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://www.baidu.com");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "������˵�������";
					} else if (eventKey.equals("14")) {
						Article article1 = new Article();
						article1.setTitle("���⹥��");
						article1.setDescription("�������,��ȫ��������Ҫ");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://www.lvye.cn/knowledge/");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "���⹥�Բ˵�������";
					} else if (eventKey.equals("21")) {
						Article article1 = new Article();
						article1.setTitle("����һ��");
						article1.setDescription("����һ��");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://www.ihuopo.com/");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "Ц����ƪ�˵�������";
					} else if (eventKey.equals("22")) {
						Article article1 = new Article();
						article1.setTitle("��������");
						article1.setDescription("���������ǿ���԰ɣ�����");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://www.linquan.info/");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "������²˵�������";
					} else if (eventKey.equals("23")) {
						// ��Ϸ����
						Article article1 = new Article();
						article1.setTitle("��Ϸ����");
						article1.setDescription("С��Ϸ");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://123.57.92.114/2048/");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "��Ϸ���Ĳ˵�������";
					} else if (eventKey.equals("24")) {
						Article article1 = new Article();
						article1.setTitle("��Ƶ����");
						article1.setDescription("�����Ҷ�ʮ�߰�");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://v.qq.com/boke/page/p/0/g/p0157rg55yg.html");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "��Ƶ���Ĳ˵�������";
					} else if (eventKey.equals("31")) {
						Article article1 = new Article();
						article1.setTitle("��ϵ����");
						article1.setDescription("ͨ��QQȺ��ϵ����΢��Ⱥ��ϵ����");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "����֮�Ҳ˵�������";
					} else if (eventKey.equals("32")) {
						Article article1 = new Article();
						article1.setTitle("��ϵ����");
						article1.setDescription("ͨ��QQȺ��ϵ����΢��Ⱥ��ϵ����");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "�������߲˵�������";
					} else if (eventKey.equals("33")) {
						Article article1 = new Article();
						article1.setTitle("��ϵ����");
						article1.setDescription("ͨ��QQȺ��ϵ����΢��Ⱥ��ϵ����");
						article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						article1.setUrl("http://cdn.sinacloud.net/marsimage/image/1045206887.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450838516&ssig=NDTcKRKchz");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "��ϵ���ǲ˵�������";
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}


}