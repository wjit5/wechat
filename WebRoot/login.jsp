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
		<title>jquery弹出层登录和全屏注册素材</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=0.95, user-scalable=no" />
		<link rel="stylesheet" type="text/css" media="all" href="style/index/css/style.css">
		<link rel="stylesheet" type="text/css" href="style/login/images/ue_grid.css" />
		<link rel="stylesheet" type="text/css" href="style/login/images/style.css" />
		<link rel="stylesheet" type="text/css" href="style/login/css/style.css" />
		<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
 		<script type="text/javascript" charset="utf-8" src="style/index/js/jquery.leanModal.min.js"></script>
		<script language="javascript" src="style/login/js/jquery.easing.min.js"></script>
		<script language="javascript" src="style/login/js/custom.js"></script>
 		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
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
		<div id="header">
  <div class="common"> 
    <div class="login fr">
      <div class="loginmenu"><a title="登录或注册"></a></div>
      <ul>
        <li class="openlogin"><a href="location=assets.jsp" onclick="return false;">登录</a></li>
        <li class="reg"><a href="http://www.baidu.com/" onclick="return false;">注册</a></li>
      </ul>
    </div>   
    <div class="clear"></div>
  </div>
</div>
<div class="clear"></div>
<div class="loginmask"></div>
<div id="loginalert">
  <div class="pd20 loginpd">
    <h3><i class="closealert fr"></i>
      <div class="clear"></div>
    </h3>
    <div class="loginwrap">
      <div class="loginh">
        <div class="fl">会员登录</div>
        <div class="fr">还没有账号<a id="sigup_now" href="http://www.baidu.com/" onclick="return false;">立即注册</a></div>
        <div class="clear"></div>
      </div>
      <h3><span>邮箱登录</span><span class="login_warning">用户名或密码错误</span>
        <div class="clear"></div>
      </h3>
      <div class="clear"></div>
      <form action="" method="post" id="login_form">
        <div class="logininput">
          <input type="text" name="username" class="loginusername" value="邮箱/用户名" />
          <input type="text" class="loginuserpasswordt" value="密码" />
          <input type="password" name="password" class="loginuserpasswordp" style="display:none" />
        </div>
        <div class="loginbtn">
          <div class="loginsubmit fl">
            <input type="submit" value="登录" />
            <div class="loginsubmiting">
              <div class="loginsubmiting_inner"></div>
            </div>
          </div>
          <div class="logcheckbox fl">
            <input id="bcdl" type="checkbox" checked="true" />
            保持登录</div>
          <div class="fr"><a href="http://www.baidu.com/">忘记密码?</a></div>
          <div class="clear"></div>
        </div>
      </form>
    </div>
  </div>
  <div class="thirdlogin">
    <div class="pd50">
      <h4>用第三方帐号直接登录</h4>
      <ul>
        <li id="sinal"><a href="http://www.baidu.com/">微博账号登录</a></li>
        <li id="qql"><a href="assets.jsp">QQ账号登录</a></li>
        <div class="clear"></div>
      </ul>
      <div class="clear"></div>
    </div>
  </div>
</div>
<div id="reg_setp">
  <div class="back_setp">返回</div>
  <div class="blogo"></div>
  <div id="setp_quicklogin">
    <h3>您可以选择以下第三方帐号直接登录jQuery插件库，一分钟完成注册</h3>
    <ul class="quicklogin_socical">
      <li class="quicklogin_socical_weibo"><a href="http://www.baidu.com/">微博帐号注册</a></li>
      <li class="quicklogin_socical_qq"><a href="assets.jsp">QQ帐号注册</a></li>
    </ul>
  </div>
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
