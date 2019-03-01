<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="./BootStrap/js/jquery/2.0.0/jquery.min.js"></script>
<link href="./BootStrap/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="./BootStrap/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
     <div style="margin-left:350px;margin-top:100px;margin-right:350px">
     <form action="save_user" method="post">
     <table class="table table-hover">
    <tr style="text-align:center">
    <td>用户名</td>
    <td>密码</td>
    <td>状态</td>
    </tr>
    <c:forEach items="${us}" var="u" varStatus="st">
	        <tr style="text-align:center">
	            <td>${u.name}</td>
	            <td>${u.password}</td>
	            <td>${u.statue}</td>
	        </tr>
	</c:forEach>
     </table>
     </form>
     </div>
</body>
</html>