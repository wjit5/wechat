package com.wjit.course.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjit.course.message.pojo.AccessToken;
import com.wjit.course.message.pojo.Button;
import com.wjit.course.message.pojo.CommonButton;
import com.wjit.course.message.pojo.ComplexButton;
import com.wjit.course.message.pojo.Menu;
import com.wjit.course.util.WeixinUtil;
/**
 * 菜单管理器类
 * 
 * @author WANGJIAN
 * @date 2015-12-11
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);  
	public static void main(String[] args) {
		// 第三方用户唯一凭证  
        String appId = "wx04c8e8a0c1a77962";  
        // 第三方用户唯一凭证密钥  
        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";  
  
        // 调用接口获取access_token  
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
  
        if (null != at) {  
            // 调用接口创建菜单  
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());  
  
            // 判断菜单创建结果  
            if (0 == result)  
                log.info("菜单创建成功！");  
            else  
                log.info("菜单创建失败，错误码：" + result);  
        }  
    }
	/**
	 * 组装菜单数据
	 * @return
	 *
	 */
	private static Menu getMenu() {
		// TODO Auto-generated method stub
		//二级菜单
		CommonButton btn11 = new CommonButton();
		btn11.setName("户外活动");
		btn11.setType("click");
		btn11.setKey("11");
		CommonButton btn12 = new CommonButton();
		btn11.setName("聚餐活动");
		btn11.setType("click");
		btn11.setKey("12");
		CommonButton btn13 = new CommonButton();
		btn11.setName("活动报名");
		btn11.setType("click");
		btn11.setKey("13");
		CommonButton btn14 = new CommonButton();
		btn11.setName("户外攻略");
		btn11.setType("click");
		btn11.setKey("14");
		CommonButton btn21 = new CommonButton();
		btn11.setName("笑话连篇");
		btn11.setType("click");
		btn11.setKey("21");
		CommonButton btn22 = new CommonButton();
		btn11.setName("推理故事");
		btn11.setType("click");
		btn11.setKey("22");
		CommonButton btn23 = new CommonButton();
		btn11.setName("游戏中心");
		btn11.setType("click");
		btn11.setKey("23");
		CommonButton btn31 = new CommonButton();
		btn11.setName("火星之家");
		btn11.setType("click");
		btn11.setKey("31");
		CommonButton btn32 = new CommonButton();
		btn11.setName("关于作者");
		btn11.setType("click");
		btn11.setKey("32");
		CommonButton btn33 = new CommonButton();
		btn11.setName("联系我们");
		btn11.setType("click");
		btn11.setKey("33");
		//一级菜单
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("活动中心");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn1.setName("休闲一刻");
		mainBtn1.setSub_button(new CommonButton[] { btn21, btn22, btn23 });	
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn1.setName("关于我们");
		mainBtn1.setSub_button(new CommonButton[] { btn31, btn32, btn33 });
		/**
		 * 这些菜单是建立在一级菜单下面有二级菜单
		 * 如果没有二级菜单，应该这样
		 * menu.setButton(new Button[]{mainBtn1,mainBtn2,btn31});
		 * 
		 */
		Menu menu=new Menu();
		menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
		
		return menu;
	}

}
