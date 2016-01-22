<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>Animated Background Headers | Demo 3</title>

<link rel="stylesheet" type="text/css" href="style/sideshow/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="style/sideshow/css/demo.css" />

<!--必要样式-->
<link rel="stylesheet" type="text/css" href="style/sideshow/css/component.css" />

<!--[if IE]>
	<script src="js/html5.js"></script>
<![endif]-->
</head>
<body>
<div class="container demo-3">
	<div class="content">
		<div id="large-header" class="large-header">
			<canvas id="demo-canvas"></canvas>
			<h1 class="main-title">Collab</span></h1>
		</div>
		<nav class="codrops-demos">
			<a href="index.jsp">Demo 1</a>
			<a href="index2.jsp">Demo 2</a>
			<a class="current-demo" href="index3.jsp">Demo 3</a>
			<a href="index4.jsp">Demo 4</a>
		</nav>
	</div>
</div><!-- /container -->
<script src="style/sideshow/js/TweenLite.min.js"></script>
<script src="style/sideshow/js/EasePack.min.js"></script>
<script src="style/sideshow/js/rAF.js"></script>
<script src="style/sideshow/js/demo-3.js"></script>
</body>
</html>