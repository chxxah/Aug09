<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div style="color: white;">
		<c:forEach items="${list }" var="m"> 
	번호 : ${m.no } / 아이디 : ${m.id}  / 이름 : ${m.name } / 출생일 : ${m.birth } / MBTI : ${m.mbti } / 성별 : ${m.gender } <br>
</c:forEach>
	</div>
</body>
</html>