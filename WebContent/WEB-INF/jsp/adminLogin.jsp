<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="./BootStrap/js/jquery/2.0.0/jquery.min.js"></script>
<link href="./BootStrap/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="./BootStrap/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>
       <form style="margin-left:550px;margin-top:100px" method="post" action="back_Login">
       <div>
       <input name="name" type="text"  class="form-control" style="width:400px;height:60px;font-size:20px">
       </div>
       <div>
       <input name="password" type="text"  class="form-control" style="width:400px;height:60px;font-size:20px">
       </div>
       <div>
       <input type="submit" value="登录" class="btn btn-default" style="width:400px;height:60px;font-size:25px;background-color:#2B6FD5;margin-top:15px">
       </div>
       </form>
       <div>${tip}</div>
</body>
</html>