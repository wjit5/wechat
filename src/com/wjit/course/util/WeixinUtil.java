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
 * ����ƽ̨ͨ�ýӿڹ�����
 * 
 * @author WANGJIAN
 * @date 2015-12-11
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	// ��ȡaccess_token�Ľӿڵ�ַ��GET�� ��200����/�죩
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// �˵�������POST�� ��100����/�죩
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * ����https ���󲢻�ȡ���
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
			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// ������SSLContext�����еõ�SSLSocketFactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// ��������ʽ��GET/POST��
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// ����������Ҫ�ύʱ
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// ע������ʽ����ֹ��������
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// �����ص�������ת�����ַ���
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
			// �ͷ���Դ
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
	 * ��ȡaccess_token
	 * 
	 * @param appid
	 *            ƾ֤
	 * @param appsecret
	 *            ��Կ
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// �������ɹ�
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// ��ȡtokenʧ��
				log.error("��ȡtokenʧ�� errcode:{} errmsg:{}", jsonObject
						.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	/**
	 * �����˵�
	 * 
	 * @param menu
	 * @param accessToken
	 * @return 0��ʾ�ɹ�������ֵ��ʾʧ��
	 * 
	 */

	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// ƴװ�����˵���url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// ���˵�����ת����json�ַ���
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// ���ýӿڴ����˵�
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("�����˵�ʧ�� errcode:{} errmsg:{}", jsonObject
						.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	/**
	 * �ж��Ƿ���QQ����
	 * 
	 * @param content
	 * @return
	 *
	 */
	public static boolean isQqFace(String content) {  
	    boolean result = false;  
	  
	    // �ж�QQ�����������ʽ  
	    String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";  
	    Pattern p = Pattern.compile(qqfaceRegex);  
	    Matcher m = p.matcher(content);  
	    if (m.matches()) {  
	        result = true;  
	    }  
	    return result;  
	} 
	/**
	 * �ж��Ƿ��ǻظ��ؼ���
	 * @param content
	 * @return
	 *
	 */
	public static boolean isMenuNumer(String content){
		boolean result=false;
		String MenuNumber="1|2|3|4|5|6|7|8|9|�˵�|?|��|����|����|��б�|�۲ͻ|�����μ�|�μ�|����|���ⰲȫ|���⹥��|����|Ц��|����һ��|����|��������|��Ƶ|��Ƶ����|��Ϸ|��Ϸ����|��ϵ����|��ϵ|";
		 Pattern p = Pattern.compile(MenuNumber);  
		    Matcher m = p.matcher(content);  
		    if (m.matches()) {  
		        result = true;  
		    }  
		    return result;  
	}
	/**
	 * �ظ�req���ı���Ϣ
	 * 
	 * @param content
	 * @return
	 *
	 */
	public static List<Article> getTextResp(String content){
		List<Article> articleList=new ArrayList<Article>();
		if("1".equals(content.trim()) || "����".equals(content.trim())|| "����".equals(content.trim()) || "�б�".equals(content.trim())){
			
			Article article1 = new Article();
			article1.setTitle("�������");
			article1.setDescription("����");
			article1.setPicUrl("��Ƭ��ַ");
			article1.setUrl("�ĵ���ַ");
			
			articleList.add(article1);
		}else if ("2".equals(content) || "�۲�".equals(content)
				|| "�Է�".equals(content) || "�۲ͻ".equals(content)) { // ��ͼ����Ϣ
			// ---����ͼƬ
			// �۲ͻ�б�
			Article article1 = new Article();
			article1.setTitle("�۲ͱ���");
			article1.setDescription("����");
			article1.setPicUrl("ͼƬ��ַ");
			article1.setUrl("�ĵ���ַ");
			articleList.add(article1);
			
		} else if ("3".equals(content) || "����".equals(content)
				|| "�����μ�".equals(content) || "�μ�".equals(content)) { // ��ͼ����Ϣ
			// �����μ�
			Article article1 = new Article();
			article1.setTitle("�����μ�");
			article1.setDescription("����");
			article1.setPicUrl("ͼƬ");
			article1.setUrl("��ַ");

			articleList.add(article1);
			
		} else if ("4".equals(content) || "���ⰲȫ".equals(content)
				|| "��ȫ".equals(content) || "����".equals(content)) { // ��ͼ����Ϣ
			// ���ⰲȫ
			Article article1 = new Article();
			article1.setTitle("���⹥��");
			article1.setDescription("�������,��ȫ��������Ҫ");
			article1.setPicUrl("ͼƬ��ַ");
			article1.setUrl("http://www.lvye.cn/knowledge/");
			articleList.add(article1);
			
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
			
		} else if ("6".equals(content) || "����".equals(content)
				|| "��������".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("��������");
			article1.setDescription("���������ǿ���԰ɣ�����");
			article1
					.setPicUrl("http://cdn.sinacloud.net/marsimage/image/1b4c510fd9f9d72afea09412d42a2834359bbbfd.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172862&ssig=SvNw8gVLBa");
			article1.setUrl("http://www.linquan.info/");

			articleList.add(article1);
			
		} else if ("7".equals(content)||"��Ϸ".equals(content)||"��Ϸ����".equals(content)) { // ��ͼ����Ϣ
			// ��Ϸ����
			Article article1 = new Article();
			article1.setTitle("��2048��");
			article1.setDescription("��2048��");
			article1.setPicUrl("http://cdn.sinacloud.net/marsimage/image/a08b87d6277f9e2f5bd0900a1c30e924b999f3cf.jpg?KID=sina,2nflrznN0mp5MsuSndwC&Expires=1450172863&ssig=UIjvrXQE%2FC");
			article1.setUrl("http://123.57.92.114/2048/");
			
			Article article2 = new Article();
			article2.setTitle("������������Ρ�");
			article2.setDescription("������������Ρ�");
			article2.setPicUrl("");
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
		
		} else if ("9".equals(content)) { // ��ͼ����Ϣ
			// ��ϵ����
			Article article1 = new Article();
			article1.setTitle("��ϵ����");
			article1.setDescription("ͨ��QQȺ��ϵ����΢��Ⱥ��ϵ����");
			article1.setPicUrl("");
			article1.setUrl("");
			articleList.add(article1);
			
		} 
		return articleList;
		
	}
}
