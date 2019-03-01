<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="./BootStrap/js/jquery/2.0.0/jquery.min.js"></script>
<link href="./BootStrap/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="./BootStrap/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script src="./js/jquery.md5.js"></script>
<body style="background-color:#D5D5D5">
<div style="margin-top:40px;margin-left:550px;width:600px">
    <form method="post" action="addUser">
  <div class="form-group" style="margin-bottom:0px">
    <input name="name" type="text" style="width:400px;height:60px;font-size:20px" class="form-control" placeholder="Username">
  </div>
  <div class="form-group" style="margin-top:0px">
    <input id="user_pwd" type="password" onblur="mdjia()" style="width:400px;height:60px;font-size:20px" class="form-control" id="exampleInputPassword1" placeholder="Password">
    <!--保存的是明码  -->
    <input name="password" id="pwd" type="hidden">
    <!-- 保存密码 -->
  </div>
  <button type="submit" class="btn btn-default" style="width:400px;height:60px;font-size:25px;background-color:#2B6FD5">Register</button>
</form>
        <div>${tip}</div>
		</div>
		<script type="text/javascript">
    function mdjia(){
    var password=$("#user_pwd").val();//得到输入值
    var pwd=$.md5(password);//对输入值进行加密
    $("#pwd").val(pwd);
}
</script>
</body>