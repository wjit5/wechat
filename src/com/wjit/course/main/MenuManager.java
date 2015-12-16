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
 * �˵���������
 * 
 * @author WANGJIAN
 * @date 2015-12-11
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);  
	public static void main(String[] args) {
		// �������û�Ψһƾ֤  
        String appId = "wx04c8e8a0c1a77962";  
        // �������û�Ψһƾ֤��Կ  
        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";  
  
        // ���ýӿڻ�ȡaccess_token  
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
  
        if (null != at) {  
            // ���ýӿڴ����˵�  
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());  
  
            // �жϲ˵��������  
            if (0 == result)  
                log.info("�˵������ɹ���");  
            else  
                log.info("�˵�����ʧ�ܣ������룺" + result);  
        }  
    }
	/**
	 * ��װ�˵�����
	 * @return
	 *
	 */
	private static Menu getMenu() {
		// TODO Auto-generated method stub
		//�����˵�
		ViewButton btn11=new ViewButton();
		btn11.setName("����");
		btn11.setType("view");
		btn11.setUrl("www.baidu.com");
		ViewButton btn12=new ViewButton();
		btn12.setName("�۲ͻ");
		btn12.setType("view");
		btn12.setUrl("www.baidu.com");
		ViewButton btn13=new ViewButton();
		btn13.setName("�����");
		btn13.setType("view");
		btn13.setUrl("www.baidu.com");
		ViewButton btn14=new ViewButton();
		btn14.setName("���⹥��");
		btn14.setType("view");
		btn14.setUrl("http://www.lvye.cn/knowledge/");
		CommonButton btn21 = new CommonButton();
		btn21.setName("Ц����ƪ");
		btn21.setType("click");
		btn21.setKey("21");
		CommonButton btn22 = new CommonButton();
		btn22.setName("�������");
		btn22.setType("click");
		btn22.setKey("22");
		CommonButton btn23 = new CommonButton();
		btn23.setName("��Ϸ����");
		btn23.setType("click");
		btn23.setKey("23");
		CommonButton btn24 = new CommonButton();
		btn24.setName("��Ƶ����");
		btn24.setType("click");
		btn24.setKey("24");
		CommonButton btn31 = new CommonButton();
		btn31.setName("����֮��");
		btn31.setType("click");
		btn31.setKey("31");
		CommonButton btn32 = new CommonButton();
		btn32.setName("��������");
		btn32.setType("click");
		btn32.setKey("32");
		CommonButton btn33 = new CommonButton();
		btn33.setName("��ϵ����");
		btn33.setType("click");
		btn33.setKey("33");
		//һ���˵�
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("�����");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("����һ��");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23,btn24 });	
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("��������");
		mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });
		/**
		 * ��Щ�˵��ǽ�����һ���˵������ж����˵�
		 * ���û�ж����˵���Ӧ������
		 * menu.setButton(new Button[]{mainBtn1,mainBtn2,btn31});
		 * 
		 */
		Menu menu=new Menu();
		menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
		
		return menu;
	}

}
