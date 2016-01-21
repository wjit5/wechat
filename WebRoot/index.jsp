<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 一个使用jQuery创建模态窗口登陆效果 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>Hello</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" media="all" href="css/style.css">
		<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
 		<script type="text/javascript" charset="utf-8" src="js/jquery.leanModal.min.js"></script>
 		<!-- jQuery plugin leanModal under MIT License http://leanmodal.finelysliced.com.au/ -->
	</head>

	<body>
		<a target="_blank"
			href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=pcbNxMrcxJKdkJSUlZXl1NSLxsrI"
			style="text-decoration: none;"><img
				src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_11.png" />
		</a>
		<div id="w">
			<div id="content">
				<h1>
					Welcome to the Site!
				</h1>
				<p>
					Just click the login link below to expand the modal window. This is
					only a demo so the input form will not load anything, but it is
					easy to connect into a backend system.
				</p>
				<p>
					<a href="#loginmodal" class="flatbtn" id="modaltrigger" >Modal
						Login</a>
				</p>
			</div>
		</div>
		<div id="loginmodal" style="display: none;">
			<h1>
				User Login
			</h1>
			<form id="loginform" name="loginform" method="post"
				action="index.html">
				<label for="username">
					Username:
				</label>
				<input type="text" name="username" id="username" class="txtfield"
					tabindex="1">

				<label for="password">
					Password:
				</label>
				<input type="password" name="password" id="password"
					class="txtfield" tabindex="2">

				<div class="center">
					<input type="submit" name="loginbtn" id="loginbtn"
						class="flatbtn-blu hidemodal" value="Log In" tabindex="3">
				</div>
			</form>
		</div>
		<script type="text/javascript">
	$(function() {
		$('#loginform').submit(function(e) {
			return false;
		});

		$('#modaltrigger').leanModal( {
			top : 110,
			overlay : 0.45,
			closeButton : ".hidemodal"
		});
	});
	
</script>
	</body>
</html>
