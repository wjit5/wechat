package com.wjit.course.util.main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * ����У�鹤����
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class SignUtil {
	//�뿪��ģʽ�ӿ�������Ϣ�е�Token����һ��
	private static String token="weixinCourse";
	
	/**
	 * У��ǩ��
	 * @param signature ΢�ż���ǩ��
	 * @param timestamp ʱ���
	 * @param nonce �����
	 * @return
	 */
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		//��token��timestamp��nonce ���ֵ�����
		String[] paramArr=new String[]{token,timestamp,nonce};
		Arrays.sort(paramArr);
		
		//������ǰ��Ľ��ƴ�ӳ�һ���ַ���
		String content=paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
		
		String ciphertext=null;
		try {
			MessageDigest md=MessageDigest.getInstance("SHA-1");
			byte[] digest=md.digest(content.toString().getBytes());
			ciphertext=byteToStr(digest);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��sha1���ܺ���ַ�����signature���бȶ�
		return ciphertext !=null ? ciphertext.equals(signature.toUpperCase()) : false;
	}
	/**
	 * ���ֽ�����ת����ʮ�������ַ���
	 * @param byteArray
	 * @return
	 *
	 */
	private static String byteToStr(byte[] byteArray) {
		// TODO Auto-generated method stub
		String strDigest="";
		for(int i=0;i<byteArray.length;i++){
			strDigest +=byteToHexStr(byteArray[i]);
		}
		
		return strDigest;
	}
	/**
	 * ���ֽ�ת����ʮ�������ַ���
	 * @param b
	 * @return
	 *
	 */
	private static String byteToHexStr(byte mByte) {
		// TODO Auto-generated method stub
		char[] Digit={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tempArr=new char[2];
		tempArr[0]=Digit[(mByte >>>4) & 0X0F];
		tempArr[1]=Digit[mByte & 0X0F];
		
		String s=new String(tempArr);
		return s;
	}

}
