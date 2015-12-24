package com.wjit.course.service.activity;

import java.util.List;
import java.util.Random;

import com.wjit.course.message.pojo.Prize;

public class ChoujiangService {
	
	public String choujMethod(String fromUserName){
		String respResult="你没有中奖";
		Random rand=new Random(100);
		String priid=Integer.toString(rand.nextInt(10));
		String prizename=(new PrizeService().getAllPrize(priid)).get(0).getPri_name();
	
		List<Prize> list= new PrizeService().getAllPrize(priid);
		if(list.size()!=0){
			respResult="你中奖了，奖品是："+list.get(0).getPri_name();
		}else {
			respResult="不好意思，你没有中奖！！！";
		}
		return respResult;
	}
}
