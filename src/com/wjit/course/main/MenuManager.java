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
		CommonButton btn11 = new CommonButton();
		btn11.setName("����");
		btn11.setType("click");
		btn11.setKey("11");
		CommonButton btn12 = new CommonButton();
		btn11.setName("�۲ͻ");
		btn11.setType("click");
		btn11.setKey("12");
		CommonButton btn13 = new CommonButton();
		btn11.setName("�����");
		btn11.setType("click");
		btn11.setKey("13");
		CommonButton btn14 = new CommonButton();
		btn11.setName("���⹥��");
		btn11.setType("click");
		btn11.setKey("14");
		CommonButton btn21 = new CommonButton();
		btn11.setName("Ц����ƪ");
		btn11.setType("click");
		btn11.setKey("21");
		CommonButton btn22 = new CommonButton();
		btn11.setName("�������");
		btn11.setType("click");
		btn11.setKey("22");
		CommonButton btn23 = new CommonButton();
		btn11.setName("��Ϸ����");
		btn11.setType("click");
		btn11.setKey("23");
		CommonButton btn31 = new CommonButton();
		btn11.setName("����֮��");
		btn11.setType("click");
		btn11.setKey("31");
		CommonButton btn32 = new CommonButton();
		btn11.setName("��������");
		btn11.setType("click");
		btn11.setKey("32");
		CommonButton btn33 = new CommonButton();
		btn11.setName("��ϵ����");
		btn11.setType("click");
		btn11.setKey("33");
		//һ���˵�
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("�����");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn1.setName("����һ��");
		mainBtn1.setSub_button(new CommonButton[] { btn21, btn22, btn23 });	
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn1.setName("��������");
		mainBtn1.setSub_button(new CommonButton[] { btn31, btn32, btn33 });
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
