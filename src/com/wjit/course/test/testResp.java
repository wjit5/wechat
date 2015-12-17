package com.wjit.course.test;

import java.util.ArrayList;
import java.util.List;

import com.wjit.course.message.pojo.Picture;
import com.wjit.course.util.MySQLUtil;
import com.wjit.course.util.WeixinUtil;

public class testResp {

	/**
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String picid="1";
		String flag="查询";
		List<Picture> list=MySQLUtil.getaAllPicture(picid,flag);
		System.out.println(list.get(0).getDescription());
	}

}
