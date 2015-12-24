package com.wjit.course.util.main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 请求校验工具类
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class SignUtil {
	//与开发模式接口配置信息中的Token保持一致
	private static String token="weixinCourse";
	
	/**
	 * 校验签名
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return
	 */
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		//对token、timestamp和nonce 按字典排序
		String[] paramArr=new String[]{token,timestamp,nonce};
		Arrays.sort(paramArr);
		
		//将排序前后的结果拼接成一个字符串
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
		//将sha1加密后的字符串与signature进行比对
		return ciphertext !=null ? ciphertext.equals(signature.toUpperCase()) : false;
	}
	/**
	 * 将字节数组转换成十六进制字符串
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
	 * 将字节转换成十六进制字符串
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
