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
			String respImage = "";

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
			textMessage.setContent("欢迎关注小剑。。。\n"+getMainMenu());
			textMessage.setFuncFlag(0);
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				// 创建图文消息
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);
				List<Article> articleList = new ArrayList<Article>();

				if ("1".equals(content) || "户外".equals(content)
						|| "户外活动".equals(content) || "列表".equals(content)) {
					// 户外活动列表
					Article article1 = new Article();
					article1.setTitle("户外标题");
					article1.setDescription("描述");
					article1.setPicUrl("照片地址");
					article1.setUrl("文档地址");

					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("2".equals(content) || "聚餐".equals(content)
						|| "吃饭".equals(content) || "聚餐活动".equals(content)) { // 单图文消息
					// ---不含图片
					// 聚餐活动列表
					Article article1 = new Article();
					article1.setTitle("聚餐标题");
					article1.setDescription("描述");
					article1.setPicUrl("图片地址");
					article1.setUrl("文档地址");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("3".equals(content) || "报名".equals(content)
						|| "报名参加".equals(content) || "参加".equals(content)) { // 多图文消息
					// 报名参加
					Article article1 = new Article();
					article1.setTitle("报名参加");
					article1.setDescription("描述");
					article1.setPicUrl("图片");
					article1.setUrl("地址");

					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("4".equals(content) || "户外安全".equals(content)
						|| "安全".equals(content) || "攻略".equals(content)) { // 多图文消息
					// 户外安全
					Article article1 = new Article();
					article1.setTitle("户外攻略");
					article1.setDescription("户外出行,安全出行最重要");
					article1.setPicUrl("图片地址");
					article1.setUrl("http://www.lvye.cn/knowledge/");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("5".equals(content) || "开心一刻".equals(content)
						|| "笑话".equals(content)) { // 多图文消息
					// 笑话连篇
					Article article1 = new Article();
					article1.setTitle("开心一刻");
					article1.setDescription("开心一刻");
					article1
							.setPicUrl("http://cdn.sinacloud.net/marsimage/image/82d6493729.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172359&ssig=ozEfIS7DQk");
					article1.setUrl("http://www.ihuopo.com/");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("6".equals(content) || "推理".equals(content)
						|| "推理中心".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("推理中心");
					article1.setDescription("发动你的最强大脑吧！！！");
					article1
							.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1b4c510fd9f9d72afea09412d42a2834359bbbfd.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172862&ssig=SvNw8gVLBa");
					article1.setUrl("http://www.linquan.info/");

					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("7".equals(content)||"游戏".equals(content)||"游戏中心".equals(content)) { // 多图文消息
					// 游戏中心
					Article article1 = new Article();
					article1.setTitle("《2048》");
					article1.setDescription("《2048》");
					article1
							.setPicUrl("http://cdn.sinacloud.net/marsimage/image/a08b87d6277f9e2f5bd0900a1c30e924b999f3cf.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172863&ssig=UIjvrXQE%2FC");
					article1.setUrl("http://123.57.92.114/2048/");
					Article article2 = new Article();
					article2.setTitle("《神奇的六边形》");
					article2.setDescription("《神奇的六边形》");
					article2
							.setPicUrl("");
					article1.setUrl("http://engine.zuoyouxi.com/demo/game/hexagon/index.php");
					
					Article article3 = new Article();
					article3.setTitle("《蛇精病》");
					article3.setDescription("《蛇精病》");
					article3
							.setPicUrl("");
					article1.setUrl("http://engine.zuoyouxi.com/game/snake/index.php?from=timeline&isappinstalled=0");
					
					Article article4 = new Article();
					article4.setTitle(" 《跳跃的方块》 ");
					article4.setDescription(" 《跳跃的方块》 ");
					article4
							.setPicUrl("");
					article1.setUrl("http://engine.zuoyouxi.com/game/JumpingBrick/index.php");
					
					Article article5 = new Article();
					article5.setTitle("《2187》");
					article5.setDescription("《2187》》");
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
				} else if ("8".equals(content)) { // 多图文消息
					// 视频中心
					Article article1 = new Article();
					article1.setTitle("视频中心");
					article1.setDescription("今年我二十七八");
					article1
							.setPicUrl("http://cdn.sinacloud.net/marsimage/image/14ce36d3d539b6009d428e45e950352ac75cb7b0.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450173129&ssig=sVfxTndzbo");
					article1
							.setUrl("http://v.qq.com/boke/page/p/0/g/p0157rg55yg.html");

					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if ("9".equals(content)) { // 多图文消息
					// 联系我们
					Article article1 = new Article();
					article1.setTitle("联系我们");
					article1.setDescription("通过QQ群联系或者微信群联系我们");
					article1.setPicUrl("");
					article1.setUrl("");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} else if(WeixinUtil.isQqFace(content)){
					// 回复文本消息  
			        TextMessage textMessage2 = new TextMessage();  
			        textMessage2.setToUserName(fromUserName);  
			        textMessage2.setFromUserName(toUserName);  
			        textMessage2.setCreateTime(new Date().getTime());  
			        textMessage2.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
			        textMessage2.setFuncFlag(0);  
			        // 用户发什么QQ表情，就返回什么QQ表情  
			        textMessage.setContent(content);  
			        // 将文本消息对象转换成xml字符串  
			        respMessage = MessageUtil.textMessageToXml(textMessage); 
				} else if("菜单".equals(content)||"帮助".equals(content)||"?".equals(content)||"？".equals(content)){
					TextMessage textMessage3 = new TextMessage();
					textMessage3.setToUserName(fromUserName);
					textMessage3.setFromUserName(toUserName);
					textMessage3.setCreateTime(new Date().getTime());
					textMessage3.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage3.setContent("欢迎关注小剑。。。\n"+getMainMenu());
					textMessage3.setFuncFlag(0);
					// 将文本消息对象转换成xml字符串
					respMessage = MessageUtil.textMessageToXml(textMessage3);
				} else {
					// 其他问题
					TextMessage textMessage3 = new TextMessage();
					String apiresult = new TulingService()
							.getTulingResult(content);
					textMessage3.setToUserName(fromUserName);
					textMessage3.setFromUserName(toUserName);
					textMessage3.setCreateTime(new Date().getTime());
					textMessage3.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage3.setContent(apiresult);
					textMessage3.setFuncFlag(0);
					// 将文本消息对象转换成xml字符串
					respMessage = MessageUtil.textMessageToXml(textMessage3);
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
					respContent = getMainMenu();
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					// 创建图文消息
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setFuncFlag(0);
					List<Article> articleList1 = new ArrayList<Article>();
					if (eventKey.equals("11")) {
						// 户外活动列表
						Article article1 = new Article();
						article1.setTitle("户外标题");
						article1.setDescription("描述");
						article1.setPicUrl("照片地址");
						article1.setUrl("文档地址");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
					} else if (eventKey.equals("12")) {
						// 聚餐活动列表
						// 聚餐活动列表
						Article article1 = new Article();
						article1.setTitle("聚餐标题");
						article1.setDescription("描述");
						article1.setPicUrl("图片地址");
						article1.setUrl("文档地址");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "聚餐活动菜单项被点击！";
					} else if (eventKey.equals("13")) {
						Article article1 = new Article();
						article1.setTitle("报名参加");
						article1.setDescription("描述");
						article1.setPicUrl("图片");
						article1.setUrl("地址");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "活动报名菜单项被点击！";
					} else if (eventKey.equals("14")) {
						Article article1 = new Article();
						article1.setTitle("户外攻略");
						article1.setDescription("户外出行,安全出行最重要");
						article1.setPicUrl("图片地址");
						article1.setUrl("http://www.lvye.cn/knowledge/");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "户外攻略菜单项被点击！";
					} else if (eventKey.equals("21")) {
						Article article1 = new Article();
						article1.setTitle("开心一刻");
						article1.setDescription("开心一刻");
						article1
								.setPicUrl("http://cdn.sinacloud.net/marsimage/image/82d6493729.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172359&ssig=ozEfIS7DQk");
						article1.setUrl("http://www.ihuopo.com/");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "笑话连篇菜单项被点击！";
					} else if (eventKey.equals("22")) {
						Article article1 = new Article();
						article1.setTitle("推理中心");
						article1.setDescription("发动你的最强大脑吧！！！");
						article1
								.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1b4c510fd9f9d72afea09412d42a2834359bbbfd.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172862&ssig=SvNw8gVLBa");
						article1.setUrl("http://www.linquan.info/");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "推理故事菜单项被点击！";
					} else if (eventKey.equals("23")) {
						// 游戏中心
						Article article1 = new Article();
						article1.setTitle("游戏中心");
						article1.setDescription("小游戏");
						article1
								.setPicUrl("http://cdn.sinacloud.net/marsimage/image/a08b87d6277f9e2f5bd0900a1c30e924b999f3cf.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172863&ssig=UIjvrXQE%2FC");
						article1.setUrl("http://123.57.92.114/2048/");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "游戏中心菜单项被点击！";
					} else if (eventKey.equals("24")) {
						Article article1 = new Article();
						article1.setTitle("视频中心");
						article1.setDescription("今年我二十七八");
						article1
								.setPicUrl("http://cdn.sinacloud.net/marsimage/image/14ce36d3d539b6009d428e45e950352ac75cb7b0.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450173129&ssig=sVfxTndzbo");
						article1
								.setUrl("http://v.qq.com/boke/page/p/0/g/p0157rg55yg.html");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "视频中心菜单项被点击！";
					} else if (eventKey.equals("31")) {
						Article article1 = new Article();
						article1.setTitle("联系我们");
						article1.setDescription("通过QQ群联系或者微信群联系我们");
						article1.setPicUrl("");
						article1.setUrl("");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "火星之家菜单项被点击！";
					} else if (eventKey.equals("32")) {
						Article article1 = new Article();
						article1.setTitle("联系我们");
						article1.setDescription("通过QQ群联系或者微信群联系我们");
						article1.setPicUrl("");
						article1.setUrl("");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "关于作者菜单项被点击！";
					} else if (eventKey.equals("33")) {
						Article article1 = new Article();
						article1.setTitle("联系我们");
						article1.setDescription("通过QQ群联系或者微信群联系我们");
						article1.setPicUrl("");
						article1.setUrl("");
						articleList1.add(article1);
						newsMessage.setArticleCount(articleList1.size());
						newsMessage.setArticles(articleList1);
						respContent = MessageUtil.newsMessageToXml(newsMessage);
						respContent = "联系我们菜单项被点击！";
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
		buffer.append("您好，我是小剑，请回复数字选择服务：").append("\n\n");
		buffer.append("1     户外活动(未开放)").append("\n");
		buffer.append("2     聚餐活动(未开放)").append("\n");
		buffer.append("3     报名参加(未开放)").append("\n");
		buffer.append("4     户外攻略").append("\n");
		buffer.append("5     笑话连篇").append("\n");
		buffer.append("6     推理故事").append("\n");
		buffer.append("7     游戏中心").append("\n");
		buffer.append("8     视频中心").append("\n");
		buffer.append("9     联系我们 ").append("\n");
		buffer.append("10  您也可以通过菜单栏选择需要的服务 ").append("\n\n");
		buffer.append("回复“?”显示此帮助菜单");
		return buffer.toString();

	}

}