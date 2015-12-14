package com.wjit.course.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
	 private static String httpRequest(String requestUrl) {  
	        StringBuffer buffer = null;  
	  
	        try {  
	            // 建立连接  
	            URL url = new URL(requestUrl);  
	            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
	            httpUrlConn.setDoInput(true);  
	            httpUrlConn.setRequestMethod("GET");  
	  
	            // 获取输入流  
	            InputStream inputStream = httpUrlConn.getInputStream();  
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
	  
	            // 读取返回结果  
	            buffer = new StringBuffer();  
	            String str = null;  
	            while ((str = bufferedReader.readLine()) != null) {  
	                buffer.append(str);  
	            }  
	  
	            // 释放资源  
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
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String html=httpRequest("http://www.baidu.com/");
		System.out.println(html);
	}

}
