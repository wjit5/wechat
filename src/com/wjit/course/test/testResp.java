package com.wjit.course.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.wjit.course.message.pojo.Picture;
import com.wjit.course.message.pojo.Prize;
import com.wjit.course.service.activity.PrizeService;
import com.wjit.course.util.main.MySQLUtil;
import com.wjit.course.util.main.WeixinUtil;

public class testResp {

	/**
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand=new Random();
		String priid=Integer.toString(rand.nextInt(100));
		
		List<Prize> list= new PrizeService().getAllPrize(priid);
		if(list.size()!=0){
			System.out.println(list.get(0).getId()+list.get(0).getPri_id()+list.get(0).getPri_name());	
		}else{
			System.out.println("ÄãÃ»ÓÐÖÐ½±");
		}
		
	}

}
