<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
    function reloadImage(){
    document.getElementById('imgCode').src="safecode?"+Math.random();//验证码后要加一个随机数
   }
</script>
<script src="./js/jquery.md5.js"></script>
<script>
function display(){
    var dom=document.getElementById('testid').value;
    alert(dom);
   }
</script>
</head>
<body>
<img id="imgCode" src="safecode"/>
<input type="button" value="看不清" id="btn" onclick="reloadImage()">
<input type="text" id="testid" name="test" onblur="display()">
</body>
</html>