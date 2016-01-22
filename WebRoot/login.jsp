<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=0.95, user-scalable=no" />
<title>jquery弹出层登录和全屏注册素材</title>
<link rel="stylesheet" type="text/css" href="style/login/images/ue_grid.css" />
<link rel="stylesheet" type="text/css" href="style/login/images/style.css" />
<link rel="stylesheet" type="text/css" href="style/login/css/style.css" />
<script src="js/jquery-1.9.1.min.js"></script>
<script language="javascript" src="style/login/js/jquery.easing.min.js"></script>
<script language="javascript" src="style/login/js/custom.js"></script>

</head>
<body style="padding-top:90px">
<div id="header">
  <div class="common"> 
    <div class="login fr">
      <div class="loginmenu"><a title="登录或注册"></a></div>
      <ul>
        <li class="openlogin"><a href="http://www.baidu.com/" onclick="return false;">登录</a></li>
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
        <li id="qql"><a href="http://www.baidu.com/">QQ账号登录</a></li>
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
      <li class="quicklogin_socical_qq"><a href="http://www.baidu.com/">QQ帐号注册</a></li>
    </ul>
  </div>
</div>

</body>
</html>

