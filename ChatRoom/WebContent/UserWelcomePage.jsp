<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>用户主页</title>
		<script type="text/javascript">
		var target = 2;
		var request = new XMLHttpRequest();
		
		function Interval(){
			window.setInterval(run,1000);
		}
		function run(){
			if(target == 1){
				intputMes();
				target = 2;
			}else{
				reload();
			}
		}
		function onClick(){
			target = 1;
		}
		function reload(){
			request.open("post","ChatRoomServlet",true);
			request.setRequestHeader("content-type","application/x-www-form-urlencoded");
			request.send("mes=");
			request.onreadystatechange = callBack;
		}
		function intputMes(){
			var mes = document.getElementById("input");
			request.open("post","ChatRoomServlet",true);
			request.setRequestHeader("content-type","application/x-www-form-urlencoded");
			request.send("mes="+mes.value);
			request.onreadystatechange = callBack;
		}
		function callBack(){
			if(request.readyState == 4 && request.status == 200){
				var textarea = document.getElementById("textarea");
				textarea.innerHTML = request.responseText;
			}else{
				return;
			}
		}
	</script>
	<style	type="text/css">
		textarea{
			border:1px solid #0BF;
			width:400px;
			height:400px;
		}
	</style>
	</head>
	<body onload="Interval()">
		<textarea id="textarea" readonly="readonly"></textarea><br/>
		<input type="text" id="input"><br/>
		<input type="button" value="发送" onclick="onClick()"><br/>
		<a href="DownloadServlet">下载聊天记录</a><br/>
		<a href="http://localhost:8888/ChatRoom/MainPage.html">退出登录</a>
	</body>
</html>





