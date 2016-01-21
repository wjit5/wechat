<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>类似于Jquery的JS下拉菜单</title>
<style type="text/css">
* { margin: 0; padding: 0; font-style: normal; font-family: 宋体; }
body { text-align: center; font-size: 12px; }
#content { margin: 0 auto; width: 600px; }
#content #nav { height: 32px; margin-top: 60px; background-color: #464749; }
#content #nav ul { list-style: none; }
#content #nav ul li { float: left; width: 100px; line-height: 32px; position: relative; }
#nav div { width: 100px; position: absolute; left: 0px; padding-bottom: 0px; float: left; height: 0; overflow: hidden; background-color: #23abf1; }
#content #nav li .a { text-decoration: none; color: #FFFFFF; line-height: 32px; display: block; border-right-width: 1px; border-right-style: solid; border-right-color: #393A3C; }
#nav div a { text-decoration: none; color: #FFFFFF; line-height: 26px; display: block; }
#nav div a:hover { background-color: #0C7DBA; }
</style>
</head>
<body>
<div id="content">
  <div id="nav">
    <ul id="supnav">
      <li><a href="#" class="a">导航菜单</a>
        <div>
        	<a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
        </div>
      </li>
      <li><a href="#" class="a">导航菜单</a>
        <div> 
        	<a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
        </div>
      </li>
      <li><a href="#" class="a">导航菜单</a>
        <div> 
        	<a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
        </div>
      </li>
      <li><a href="#" class="a">导航菜单</a>
        <div> 
        	<a href="#">导航菜单</a> 
        	<a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
        </div>
      </li>
      <li><a href="#" class="a">导航菜单</a>
        <div> 
        	<a href="#">导航菜单</a> 
            <a href="#">导航菜单</a>
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
        </div>
      </li>
      <li><a href="#" class="a">导航菜单</a>
        <div> 
        	<a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
            <a href="#">导航菜单</a> 
         </div>
      </li>
    </ul>
  </div>
</div>
<script type="text/javascript">
	var supnav = document.getElementById("supnav");
	var nav = document.getElementById("nav");
	var btns = document.getElementsByTagName("li");
	var subnavs = nav.getElementsByTagName("div");
	var paddingbottom = 20;
	var defaultHeight = 0;
	function drop(obj, ivalue) {
		var a = obj.offsetHeight;
		var speed = (ivalue - obj.offsetHeight) / 8;
		a += Math.floor(speed);
		obj.style.height = a + "px";
	}
	window.onload = function() {
		for (var i = 0; i < btns.length; i++) {
			btns[i].index = i;
			btns[i].onmouseover = function() {
				var osubnav = subnavs[this.index];
				var sublinks = osubnav.getElementsByTagName("a");
				if (osubnav.firstChild.tagName == undefined) {
					var itarheight = parseInt(osubnav.childNodes[1].offsetHeight) * sublinks.length + paddingbottom;
				} else {
					var itarheight = parseInt(osubnav.firstChild.offsetHeight) * sublinks.length + paddingbottom;
				}
				clearInterval(this.itimer);
				this.itimer = setInterval(function() {
					drop(osubnav, itarheight);
				},
				30);
			}
			btns[i].onmouseout = function() {
				var osubnav = subnavs[this.index];
				clearInterval(this.itimer);
				this.itimer = setInterval(function() {
					drop(osubnav, defaultHeight);
				},
				30);
			}
		}
	}
</script>
</body>
</html>