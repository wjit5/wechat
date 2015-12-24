package com.wjit.course.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wjit.course.service.main.CoreService;
import com.wjit.course.util.main.SignUtil;

/**
 * ������ĺ�����
 * 
 * @author WANGJIAN
 * @date 2015-12-8
 */
public class CoreServlet extends HttpServlet {

	private static final long serialVersionUID = 4440739483644821986L;
	
	
	/**
	 * ����У��
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//΢�ż���ǩ��
		String signature=request.getParameter("signature");
		//ʱ���
		String timestamp=request.getParameter("timestamp");
		//�����
		String nonce=request.getParameter("nonce");
		//����ַ���
		String echostr=request.getParameter("echostr");
		
		PrintWriter out=response.getWriter();
		//����У�飬��У��ɹ���ԭ������echostr,��ʾ����ɹ����������ʧ��
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
		out.close();
		out=null;
		
	}

	/**
	 * ����У���봦��
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// ���ղ���΢�ż���ǩ���� ʱ����������
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		PrintWriter out = response.getWriter();
		// ����У��
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			// ���ú��ķ�������մ�������
			String respXml = CoreService.processRequest(request);
			out.print(respXml);
		}
		out.close();
		out = null;
	}
}
