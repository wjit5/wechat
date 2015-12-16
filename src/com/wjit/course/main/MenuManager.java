package com.wjit.course.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjit.course.message.pojo.AccessToken;
import com.wjit.course.message.pojo.Button;
import com.wjit.course.message.pojo.CommonButton;
import com.wjit.course.message.pojo.ComplexButton;
import com.wjit.course.message.pojo.Menu;
import com.wjit.course.message.pojo.ViewButton;
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
		ViewButton btn11=new ViewButton();
		btn11.setName("户外活动");
		btn11.setType("view");
		btn11.setUrl("www.baidu.com");
		ViewButton btn12=new ViewButton();
		btn12.setName("聚餐活动");
		btn12.setType("view");
		btn12.setUrl("www.baidu.com");
		ViewButton btn13=new ViewButton();
		btn13.setName("活动报名");
		btn13.setType("view");
		btn13.setUrl("www.baidu.com");
		ViewButton btn14=new ViewButton();
		btn14.setName("户外攻略");
		btn14.setType("view");
		btn14.setUrl("http://www.lvye.cn/knowledge/");
		CommonButton btn21 = new CommonButton();
		btn21.setName("笑话连篇");
		btn21.setType("click");
		btn21.setKey("21");
		CommonButton btn22 = new CommonButton();
		btn22.setName("推理故事");
		btn22.setType("click");
		btn22.setKey("22");
		CommonButton btn23 = new CommonButton();
		btn23.setName("游戏中心");
		btn23.setType("click");
		btn23.setKey("23");
		CommonButton btn24 = new CommonButton();
		btn24.setName("视频中心");
		btn24.setType("click");
		btn24.setKey("24");
		CommonButton btn31 = new CommonButton();
		btn31.setName("火星之家");
		btn31.setType("click");
		btn31.setKey("31");
		CommonButton btn32 = new CommonButton();
		btn32.setName("关于作者");
		btn32.setType("click");
		btn32.setKey("32");
		CommonButton btn33 = new CommonButton();
		btn33.setName("联系我们");
		btn33.setType("click");
		btn33.setKey("33");
		//一级菜单
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("活动中心");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("休闲一刻");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23,btn24 });	
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("关于我们");
		mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });
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
