<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
</head>
<body>
<h1>Sign In</h1>
<form action = "${path}/auth/login.do" method = "post">
	<label>사용자 번호</label>
	<input type = "text" name = "userid" value = "jmy0302"><br>
	<label>비밀번호</label>
	<input type = "password" name = "userpass" value = "1234">
	<input type = "submit" value="로그인">
</form>
</body>
</html>