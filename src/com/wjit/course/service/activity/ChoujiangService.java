package com.wjit.course.service.activity;

import java.util.List;
import java.util.Random;

import com.wjit.course.message.pojo.Prize;

public class ChoujiangService {
	
	public String choujMethod(String fromUserName){
		String respResult="��û���н�";
		Random rand=new Random(100);
		String priid=Integer.toString(rand.nextInt(10));
		String prizename=(new PrizeService().getAllPrize(priid)).get(0).getPri_name();
	
		List<Prize> list= new PrizeService().getAllPrize(priid);
		if(list.size()!=0){
			respResult="���н��ˣ���Ʒ�ǣ�"+list.get(0).getPri_name();
		}else {
			respResult="������˼����û���н�������";
		}
		return respResult;
	}
}
