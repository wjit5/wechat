package com.wjit.course.service.main;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wjit.course.message.resp.Music;

public class BaiduMusicService {
	/**
	 * �������ƺ�������������
	 * 
	 * @param musicTitle
	 * @param musicAuthor
	 * @return
	 *
	 */
	public static Music searchMusic(String musicTitle,String musicAuthor){
		// �ٶ�����������ַ
		String requestUrl = "http://box.zhangmen.baidu.com/x?op=12&count=1&title={TITLE}$${AUTHOR}$$$$";
		// ���������ơ����߽�URL����
		requestUrl = requestUrl.replace("{TITLE}", urlEncodeUTF8(musicTitle));
		requestUrl = requestUrl.replace("{AUTHOR}", urlEncodeUTF8(musicAuthor));
		// �������ơ������м�Ŀո�
		requestUrl = requestUrl.replaceAll("\\+", "%20");

		// ��ѯ����ȡ���ؽ��
		InputStream inputStream = httpRequest(requestUrl);
		// �ӷ��ؽ���н�����Music
		Music music = parseMusic(inputStream);

		// ���music��Ϊnull�����ñ��������
		if (null != music) {
			music.setTitle(musicTitle);
			// ������߲�Ϊ""������������Ϊ����
			if (!"".equals(musicAuthor))
				music.setDescription(musicAuthor);
			else
				music.setDescription("���԰ٶ�����");
		}
		return music;
	}
	/**
	 * UTF-8����
	 * 
	 * @param source
	 * @return
	 */
	private static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * ����http����ȡ�÷��ص�������
	 * 
	 * @param requestUrl �����ַ
	 * @return InputStream
	 */
	private static InputStream httpRequest(String requestUrl) {
		InputStream inputStream = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			// ��÷��ص�������
			inputStream = httpUrlConn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;
	}
	/**
	 * �������ֲ���
	 * 
	 * @param inputStream �ٶ���������API���ص�������
	 * @return Music
	 */
	@SuppressWarnings("unchecked")
	private static Music parseMusic(InputStream inputStream) {
		Music music = null;
		try {
			// ʹ��dom4j����xml�ַ���
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// �õ�xml��Ԫ��
			Element root = document.getRootElement();
			// count��ʾ�������ĸ�����
			String count = root.element("count").getText();
			// ���������ĸ���������0ʱ
			if (!"0".equals(count)) {
				// ��ͨƷ��
				List<Element> urlList = root.elements("url");
				// ��Ʒ��
				List<Element> durlList = root.elements("durl");

				// ��ͨƷ�ʵ�encode��decode
				String urlEncode = urlList.get(0).element("encode").getText();
				String urlDecode = urlList.get(0).element("decode").getText();
				// ��ͨƷ�����ֵ�URL
				String url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode;
				if (-1 != urlDecode.lastIndexOf("&"))
					url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode.substring(0, urlDecode.lastIndexOf("&"));

				// Ĭ������£����������ֵ�URL ���� ��ͨƷ�����ֵ�URL
				String durl = url;

				// �жϸ�Ʒ�ʽڵ��Ƿ����
				Element durlElement = durlList.get(0).element("encode");
				if (null != durlElement) {
					// ��Ʒ�ʵ�encode��decode
					String durlEncode = durlList.get(0).element("encode").getText();
					String durlDecode = durlList.get(0).element("decode").getText();
					// ��Ʒ�����ֵ�URL
					durl = durlEncode.substring(0, durlEncode.lastIndexOf("/") + 1) + durlDecode;
					if (-1 != durlDecode.lastIndexOf("&"))
						durl = durlEncode.substring(0, durlEncode.lastIndexOf("/") + 1) + durlDecode.substring(0, durlDecode.lastIndexOf("&"));
				}
				music = new Music();
				// ������ͨƷ����������
				music.setMusicUrl(url);
				// ���ø�Ʒ����������
				music.setHQMusicUrl(durl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return music;
	}
	// ���Է���
	public static void main(String[] args) {
		Music music = searchMusic("�����Լ�", "����ֶ�");
		System.out.println("�������ƣ�" + music.getTitle());
		System.out.println("����������" + music.getDescription());
		System.out.println("��ͨƷ�����ӣ�" + music.getMusicUrl());
		System.out.println("��Ʒ�����ӣ�" + music.getHQMusicUrl());
	}
		
}
