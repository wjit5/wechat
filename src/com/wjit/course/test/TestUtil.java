package com.wjit.course.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtil {
	public static List<Test> getTest(String te){
		List<Test> list=new ArrayList<Test>();

		Test test=new Test();
		test.setId(1);
		test.setName(te);
		list.add(test);
		return list;
	}
	
	public static boolean isQqFace(String content) {  
	    boolean result = false;  
	    // �ж�QQ�����������ʽ  
	    String qqfaceRegex = "12|34||�˵�||";  
	    Pattern p = Pattern.compile(qqfaceRegex);  
	    Matcher m = p.matcher(content);  
	    if (m.matches()) {  
	        result = true;  
	    }  
	    return result;  
	}  
}
