package com.wjit.course.test;

import java.util.ArrayList;
import java.util.List;

import com.wjit.course.util.WeixinUtil;

public class testResp {

	/**
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a="²Ëµ¥";
		
		List<Test> list=new ArrayList<Test>();
		if(a!=null){
			list=TestUtil.getTest(a);
			boolean b=TestUtil.isQqFace(a);
			System.out.println(list.get(0).name);
			System.out.println(b);
		}
	}

}
