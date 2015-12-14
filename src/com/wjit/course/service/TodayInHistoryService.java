package com.wjit.course.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��ʷ�ϵĽ����ѯ����
 * 
 * @author WANGJIAN
 * @date 2015-12-9
 */
public class TodayInHistoryService {
	/** 
     * ����http get�����ȡ��ҳԴ���� 
     *  
     * @param requestUrl 
     * @return 
     */  
    private static String httpRequest(String requestUrl) {  
        StringBuffer buffer = null;  
  
        try {  
            // ��������  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
  
            // ��ȡ������  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            // ��ȡ���ؽ��  
            buffer = new StringBuffer();  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
  
            // �ͷ���Դ  
            bufferedReader.close();  
            inputStreamReader.close();  
            inputStream.close();  
            httpUrlConn.disconnect();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return buffer.toString();  
    }  
  
    /** 
     * ��html�г�ȡ����ʷ�ϵĽ�����Ϣ 
     *  
     * @param html 
     * @return 
     */  
    private static String extract(String html) {  
        StringBuffer buffer = null;  
        // ���ڱ�ǩ�����������컹�ǽ���  
        String dateTag = getMonthDay(0);  
  
        Pattern p = Pattern.compile("(.*)(<div class=\"listren\">)(.*?)(</div>)(.*)");  
        Matcher m = p.matcher(html);  
        if (m.matches()) {  
            buffer = new StringBuffer();  
            if (m.group(3).contains(getMonthDay(-1)))  
                dateTag = getMonthDay(-1);  
  
            // ƴװ����  
            buffer.append("�ԡ� ").append("��ʷ�ϵ�").append(dateTag).append(" �ԡ�").append("\n\n");  
  
            // ��ȡ��Ҫ������  
            for (String info : m.group(3).split("  ")) {  
                info = info.replace(dateTag, "").replace("��ͼ��", "").replaceAll("</?[^>]+>", "").trim();  
                // ��ÿ��ĩβ׷��2�����з�  
                if (!"".equals(info)) {  
                    buffer.append(info).append("\n\n");  
                }  
            }  
        }  
        // ��buffer����������з��Ƴ�������  
        return (null == buffer) ? null : buffer.substring(0, buffer.lastIndexOf("\n\n"));  
    }  
  
    /** 
     * ��ȡǰ/��n������(M��d��) 
     *  
     * @return 
     */  
    private static String getMonthDay(int diff) {  
        DateFormat df = new SimpleDateFormat("M��d��");  
        Calendar c = Calendar.getInstance();  
        c.add(Calendar.DAY_OF_YEAR, diff);  
        return df.format(c.getTime());  
    }  
  
    /** 
     * ��װ��ʷ�ϵĽ����ѯ���������ⲿ���� 
     *  
     * @return 
     */  
    public static String getTodayInHistoryInfo() {  
        // ��ȡ��ҳԴ����  
        String html = httpRequest("www");  
        // ����ҳ�г�ȡ��Ϣ  
        String result = extract(html);  
  
        return result;  
    }  
  
    /** 
     * ͨ��main�ڱ��ز��� 
     *  
     * @param args 
     */  
    public static void main(String[] args) {  
    	 String html = httpRequest("http://www.taobao.com/"); 
        String info = getTodayInHistoryInfo();  
        System.out.println(html);  
    }  
}  