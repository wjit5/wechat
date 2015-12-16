package com.wjit.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wjit.course.message.resp.Article;
import com.wjit.course.message.resp.NewsMessage;
import com.wjit.course.message.resp.TextMessage;
import com.wjit.course.util.MessageUtil;
import com.wjit.course.util.WeixinUtil;

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
			textMessage.setContent("��ӭ��עС��������\n"+getMainMenu());
			textMessage.setFuncFlag(0);
			// ���ı���Ϣ����ת����xml�ַ���
			respMessage = MessageUtil.textMessageToXml(textMessage);
			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				// ����ͼ����Ϣ
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);
				List<Article> articleList = new ArrayList<Article>();

				if ("1".equals(content) || "����".equals(content)
						|| "����".equals(content) || "�б�".equals(content)) {
					// �����б�
					Article article1 = new Article();
					article1.setTitle("�������");
					article1.setDescription("����");
					article1.setPicUrl("��Ƭ��ַ");
					article1.setUrl("�ĵ���ַ");

					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("2".equals(content) || "�۲�".equals(content)
						|| "�Է�".equals(content) || "�۲ͻ".equals(content)) { // ��ͼ����Ϣ
					// ---����ͼƬ
					// �۲ͻ�б�
					Article article1 = new Article();
					article1.setTitle("�۲ͱ���");
					article1.setDescription("����");
					article1.setPicUrl("ͼƬ��ַ");
					article1.setUrl("�ĵ���ַ");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("3".equals(content) || "����".equals(content)
						|| "�����μ�".equals(content) || "�μ�".equals(content)) { // ��ͼ����Ϣ
					// �����μ�
					Article article1 = new Article();
					article1.setTitle("�����μ�");
					article1.setDescription("����");
					article1.setPicUrl("ͼƬ");
					article1.setUrl("��ַ");

					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("4".equals(content) || "���ⰲȫ".equals(content)
						|| "��ȫ".equals(content) || "����".equals(content)) { // ��ͼ����Ϣ
					// ���ⰲȫ
					Article article1 = new Article();
					article1.setTitle("���⹥��");
					article1.setDescription("�������,��ȫ��������Ҫ");
					article1.setPicUrl("ͼƬ��ַ");
					article1.setUrl("http://www.lvye.cn/knowledge/");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("5".equals(content) || "����һ��".equals(content)
						|| "Ц��".equals(content)) { // ��ͼ����Ϣ
					// Ц����ƪ
					Article article1 = new Article();
					article1.setTitle("����һ��");
					article1.setDescription("����һ��");
					article1
							.setPicUrl("http://cdn.sinacloud.net/marsimage/image/82d6493729.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172359&ssig=ozEfIS7DQk");
					article1.setUrl("http://www.ihuopo.com/");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("6".equals(content) || "����".equals(content)
						|| "��������".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("��������");
					article1.setDescription("���������ǿ���԰ɣ�����");
					article1
							.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1b4c510fd9f9d72afea09412d42a2834359bbbfd.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172862&ssig=SvNw8gVLBa");
					article1.setUrl("http://www.linquan.info/");

					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("7".equals(content)||"��Ϸ".equals(content)||"��Ϸ����".equals(content)) { // ��ͼ����Ϣ
					// ��Ϸ����
					Article article1 = new Article();
					article1.setTitle("��2048��");
					article1.setDescription("��2048��");
					article1
							.setPicUrl("http://cdn.sinacloud.net/marsimage/image/a08b87d6277f9e2f5bd0900a1c30e924b999f3cf.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172863&ssig=UIjvrXQE%2FC");
					article1.setUrl("http://123.57.92.114/2048/");
					Article article2 = new Article();
					article2.setTitle("������������Ρ�");
					article2.setDescription("������������Ρ�");
					article2
							.setPicUrl("");
					article1.setUrl("http://engine.zuoyouxi.com/demo/game/hexagon/index.php");
					
					Article article3 = new Article();
					article3.setTitle("���߾�����");
					article3.setDescription("���߾�����");
					article3
							.setPicUrl("");
					article1.setUrl("http://engine.zuoyouxi.com/game/snake/index.php?from=timeline&isappinstalled=0");
					
					Article article4 = new Article();
					article4.setTitle(" ����Ծ�ķ��顷 ");
					article4.setDescription(" ����Ծ�ķ��顷 ");
					article4
							.setPicUrl("");
					article1.setUrl("http://engine.zuoyouxi.com/game/JumpingBrick/index.php");
					
					Article article5 = new Article();
					article5.setTitle("��2187��");
					article5.setDescription("��2187����");
					article5
							.setPicUrl("");
					article1.setUrl("http://engine.zuoyouxi.com/game/2187/index.html");
					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					articleList.add(article4);
					articleList.add(article5);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("8".equals(content)) { // ��ͼ����Ϣ
					// ��Ƶ����
					Article article1 = new Article();
					article1.setTitle("��Ƶ����");
					article1.setDescription("�����Ҷ�ʮ�߰�");
					article1
							.setPicUrl("http://cdn.sinacloud.net/marsimage/image/14ce36d3d539b6009d428e45e950352ac75cb7b0.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450173129&ssig=sVfxTndzbo");
					article1
							.setUrl("http://v.qq.com/boke/page/p/0/g/p0157rg55yg.html");

					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("9".equals(content)) { // ��ͼ����Ϣ
					// ��ϵ����
					Article article1 = new Article();
					article1.setTitle("��ϵ����");
					article1.setDescription("ͨ��QQȺ��ϵ����΢��Ⱥ��ϵ����");
					article1.setPicUrl("");
					article1.setUrl("");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if(WeixinUtil.isQqFace(content)){
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
				} else if("�˵�".equals(content)||"����".equals(content)||"?".equals(content)||"��".equals(content)){
					TextMessage textMessage3 = new TextMessage();
					textMessage3.setToUserName(fromUserName);
					textMessage3.setFromUserName(toUserName);
					textMessage3.setCreateTime(new Date().getTime());
					textMessage3.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage3.setContent("��ӭ��עС��������\n"+getMainMenu());
					textMessage3.setFuncFlag(0);
					// ���ı���Ϣ����ת����xml�ַ���
					respMessage = MessageUtil.textMessageToXml(textMessage3);
				} else {
					// ��������
					TextMessage textMessage3 = new TextMessage();
					String apiresult = new TulingService()
							.getTulingResult(content);
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
					respContent = getMainMenu();
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
						article1.setPicUrl("��Ƭ��ַ");
						article1.setUrl("�ĵ���ַ");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
					} else if (eventKey.equals("12")) {
						// �۲ͻ�б�
						// �۲ͻ�б�
						Article article1 = new Article();
						article1.setTitle("�۲ͱ���");
						article1.setDescription("����");
						article1.setPicUrl("ͼƬ��ַ");
						article1.setUrl("�ĵ���ַ");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "�۲ͻ�˵�������";
					} else if (eventKey.equals("13")) {
						Article article1 = new Article();
						article1.setTitle("�����μ�");
						article1.setDescription("����");
						article1.setPicUrl("ͼƬ");
						article1.setUrl("��ַ");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "������˵�������";
					} else if (eventKey.equals("14")) {
						Article article1 = new Article();
						article1.setTitle("���⹥��");
						article1.setDescription("�������,��ȫ��������Ҫ");
						article1.setPicUrl("ͼƬ��ַ");
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
						article1
								.setPicUrl("http://cdn.sinacloud.net/marsimage/image/82d6493729.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172359&ssig=ozEfIS7DQk");
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
						article1
								.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1b4c510fd9f9d72afea09412d42a2834359bbbfd.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172862&ssig=SvNw8gVLBa");
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
						article1
								.setPicUrl("http://cdn.sinacloud.net/marsimage/image/a08b87d6277f9e2f5bd0900a1c30e924b999f3cf.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172863&ssig=UIjvrXQE%2FC");
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
						article1
								.setPicUrl("http://cdn.sinacloud.net/marsimage/image/14ce36d3d539b6009d428e45e950352ac75cb7b0.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450173129&ssig=sVfxTndzbo");
						article1
								.setUrl("http://v.qq.com/boke/page/p/0/g/p0157rg55yg.html");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "��Ƶ���Ĳ˵�������";
					} else if (eventKey.equals("31")) {
						Article article1 = new Article();
						article1.setTitle("��ϵ����");
						article1.setDescription("ͨ��QQȺ��ϵ����΢��Ⱥ��ϵ����");
						article1.setPicUrl("");
						article1.setUrl("");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "����֮�Ҳ˵�������";
					} else if (eventKey.equals("32")) {
						Article article1 = new Article();
						article1.setTitle("��ϵ����");
						article1.setDescription("ͨ��QQȺ��ϵ����΢��Ⱥ��ϵ����");
						article1.setPicUrl("");
						article1.setUrl("");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "�������߲˵�������";
					} else if (eventKey.equals("33")) {
						Article article1 = new Article();
						article1.setTitle("��ϵ����");
						article1.setDescription("ͨ��QQȺ��ϵ����΢��Ⱥ��ϵ����");
						article1.setPicUrl("");
						article1.setUrl("");
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

	private static String getMainMenu() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("���ã�����С������ظ�����ѡ�����").append("\n\n");
		buffer.append("1     ����(δ����)").append("\n");
		buffer.append("2     �۲ͻ(δ����)").append("\n");
		buffer.append("3     �����μ�(δ����)").append("\n");
		buffer.append("4     ���⹥��").append("\n");
		buffer.append("5     Ц����ƪ").append("\n");
		buffer.append("6     �������").append("\n");
		buffer.append("7     ��Ϸ����").append("\n");
		buffer.append("8     ��Ƶ����").append("\n");
		buffer.append("9     ��ϵ���� ").append("\n");
		buffer.append("10  ��Ҳ����ͨ���˵���ѡ����Ҫ�ķ��� ").append("\n\n");
		buffer.append("�ظ���?����ʾ�˰����˵�");
		return buffer.toString();

	}

}