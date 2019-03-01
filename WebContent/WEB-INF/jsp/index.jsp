<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" scope="application"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="./BootStrap/js/jquery/2.0.0/jquery.min.js"></script>
<link href="./BootStrap/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="./BootStrap/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script src="./js/jquery.md5.js"></script>
<script>
    function reloadImage(){
    document.getElementById('imgCode').src="safecode?"+Math.random();//验证码后要加一个随机数
   }
</script>
<script type="text/javascript">
var paths="${contextPath}";
    function mdjia(){
    var password=$("#user_pwd").val();//得到输入值
    var pwd=$.md5(password);//对输入值进行加密
    $("#pwd").val(pwd);
}
    function submitLogin(){
    		 var name=$.trim($("#nameQ").val());
    		 var psw=$.trim($("#pwd").val());
    		 var code=$.trim($("#code").val());
    		 var paras={"name":name,"password":psw,"code":code};//这里传递的是一个JSON对象
    		 /* paras.name调用 */
    		 $.ajax({
    			   type: "POST",
    			   url: paths+"/login_ajax",
    			   data: paras,
    			   dataType: "json", 
    			   async: false,
    			   success: function(data){
    				   alert(data.flag);
    				   if(data.flag=="1")//调用JSON数据
    					   {
    				         window.location.href=paths+"/defaultjsp";	/* 成功之后返回首页 */
    					   }
    				   else
    					   {
    					    $("#tip").text("failed!");
    					   }
                       }
    			   }
    		 );
    }
</script>
</head>
<body style="background-color:#D5D5D5">
<div><a href="adminLogin" style="float:right">后台登录</a></div>
<div style="margin-top:40px;margin-left:550px;width:600px">
    <!-- <form method="post" action=""> -->
  <div class="form-group" style="margin-bottom:0px">
    <input id="nameQ" name="name" type="text" style="width:400px;height:60px;font-size:20px" class="form-control" placeholder="Username">
  </div>
  <div class="form-group" style="margin-top:0px;margin-bottom:0">
    <input id="user_pwd" onblur="mdjia()" type="password" style="width:400px;height:60px;font-size:20px" class="form-control" id="exampleInputPassword1" placeholder="Password">
    <input name="password" id="pwd" type="hidden">
  </div>
 <div style="margin-top:0">
 <input id="code" name="validatecode" type="text" style="width:250px;height:60px;font-size:20px;display: inline-block;margin-right:30px" class="form-control">
 <img id="imgCode" src="safecode" style="width:120px;height:50px;display: inline-block"/>
<a onclick="reloadImage()">看不清,换一张</a>
 </div>
  <button type="submit" onclick="submitLogin()" class="btn btn-default" style="width:400px;height:60px;font-size:25px;background-color:#2B6FD5;margin-top:15px">Submit</button>
<!-- </form> -->
        <div id="tip">${tip}</div>
		<div><a href="register" style="font-size:16px">还没有账号,注册</a></div>
		</div>
		
</body>		
</html>