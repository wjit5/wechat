package com.wjit.course.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjit.course.message.pojo.AccessToken;
import com.wjit.course.message.pojo.Menu;
import com.wjit.course.message.pojo.MyX509TrustManager;
import com.wjit.course.message.resp.Article;
import com.wjit.course.message.resp.TextMessage;

/**
 * 公众平台通用接口工具类
 * 
 * @author WANGJIAN
 * @date 2015-12-11
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 发起https 请求并获取结果
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 * 
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject
						.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 * @param accessToken
	 * @return 0表示成功，其他值表示失败
	 * 
	 */

	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject
						.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	/**
	 * 判断是否是QQ表情
	 * 
	 * @param content
	 * @return
	 *
	 */
	public static boolean isQqFace(String content) {  
	    boolean result = false;  
	  
	    // 判断QQ表情的正则表达式  
	    String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";  
	    Pattern p = Pattern.compile(qqfaceRegex);  
	    Matcher m = p.matcher(content);  
	    if (m.matches()) {  
	        result = true;  
	    }  
	    return result;  
	} 
	/**
	 * 判断是否是回复关键字
	 * @param content
	 * @return
	 *
	 */
	public static boolean isMenuNumer(String content){
		boolean result=false;
		String MenuNumber="1|2|3|4|5|6|7|8|9|菜单|?|？|帮助|户外活动|活动列表|聚餐活动|报名参加|参加|报名|户外安全|户外攻略|攻略|笑话|开心一刻|推理|推理中心|视频|视频中心|游戏|游戏中心|联系我们|联系|";
		 Pattern p = Pattern.compile(MenuNumber);  
		    Matcher m = p.matcher(content);  
		    if (m.matches()) {  
		        result = true;  
		    }  
		    return result;  
	}
	/**
	 * 回复req是文本消息
	 * 
	 * @param content
	 * @return
	 *
	 */
	public static List<Article> getTextResp(String content){
		List<Article> articleList=new ArrayList<Article>();
		if("1".equals(content.trim()) || "户外".equals(content.trim())|| "户外活动".equals(content.trim()) || "列表".equals(content.trim())){
			
			Article article1 = new Article();
			article1.setTitle("户外标题");
			article1.setDescription("描述");
			article1.setPicUrl("照片地址");
			article1.setUrl("文档地址");
			
			articleList.add(article1);
		}else if ("2".equals(content) || "聚餐".equals(content)
				|| "吃饭".equals(content) || "聚餐活动".equals(content)) { // 单图文消息
			// ---不含图片
			// 聚餐活动列表
			Article article1 = new Article();
			article1.setTitle("聚餐标题");
			article1.setDescription("描述");
			article1.setPicUrl("图片地址");
			article1.setUrl("文档地址");
			articleList.add(article1);
			
		} else if ("3".equals(content) || "报名".equals(content)
				|| "报名参加".equals(content) || "参加".equals(content)) { // 多图文消息
			// 报名参加
			Article article1 = new Article();
			article1.setTitle("报名参加");
			article1.setDescription("描述");
			article1.setPicUrl("图片");
			article1.setUrl("地址");

			articleList.add(article1);
			
		} else if ("4".equals(content) || "户外安全".equals(content)
				|| "安全".equals(content) || "攻略".equals(content)) { // 多图文消息
			// 户外安全
			Article article1 = new Article();
			article1.setTitle("户外攻略");
			article1.setDescription("户外出行,安全出行最重要");
			article1.setPicUrl("图片地址");
			article1.setUrl("http://www.lvye.cn/knowledge/");
			articleList.add(article1);
			
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
			
		} else if ("6".equals(content) || "推理".equals(content)
				|| "推理中心".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("推理中心");
			article1.setDescription("发动你的最强大脑吧！！！");
			article1
					.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1b4c510fd9f9d72afea09412d42a2834359bbbfd.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172862&ssig=SvNw8gVLBa");
			article1.setUrl("http://www.linquan.info/");

			articleList.add(article1);
			
		} else if ("7".equals(content)||"游戏".equals(content)||"游戏中心".equals(content)) { // 多图文消息
			// 游戏中心
			Article article1 = new Article();
			article1.setTitle("《2048》");
			article1.setDescription("《2048》");
			article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/a08b87d6277f9e2f5bd0900a1c30e924b999f3cf.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172863&ssig=UIjvrXQE%2FC");
			article1.setUrl("http://123.57.92.114/2048/");
			
			Article article2 = new Article();
			article2.setTitle("《神奇的六边形》");
			article2.setDescription("《神奇的六边形》");
			article2.setPicUrl("");
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
		
		} else if ("9".equals(content)) { // 多图文消息
			// 联系我们
			Article article1 = new Article();
			article1.setTitle("联系我们");
			article1.setDescription("通过QQ群联系或者微信群联系我们");
			article1.setPicUrl("");
			article1.setUrl("");
			articleList.add(article1);
			
		} 
		return articleList;
		
	}
}
