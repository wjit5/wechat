package com.wjit.course.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class TulingApiProcess {
	 /** 
     * ����ͼ�������api�ӿڣ���ȡ���ܻظ����ݣ�������ȡ�Լ������� 
     * @param content 
     * @return 
     */  
    public String getTulingResult(String content){  
        /** �˴�Ϊͼ��api�ӿڣ�����key��Ҫ�Լ�ȥע�����룬����11111111���� */  
        String apiUrl = "http://www.tuling123.com/openapi/api?key=11111111&info=";  
        String param = "";  
        try {  
            param = apiUrl+URLEncoder.encode(content,"utf-8");  
        } catch (UnsupportedEncodingException e1) {  
            // TODO Auto-generated catch block  
            e1.printStackTrace();  
        } //������תΪurl����  
          
        /** ����httpget���� */  
        HttpGet request = new HttpGet(param);  
        String result = "";  
        try {  
            HttpResponse response = HttpClients.createDefault().execute(request);  
            if(response.getStatusLine().getStatusCode()==200){  
                result = EntityUtils.toString(response.getEntity());  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        /** ����ʧ�ܴ��� */  
        if(null==result){  
            return "�Բ�����˵�Ļ�����̫�����ˡ���";  
        }  
          
        try {  
            JSONObject json = new JSONObject(result);  
            //��code=100000Ϊ�����ο�ͼ�������api�ĵ�  
            if(100000==json.getInt("code")){  
                result = json.getString("text");  
            }  
        } catch (JSONException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return result;  
    }  
}  