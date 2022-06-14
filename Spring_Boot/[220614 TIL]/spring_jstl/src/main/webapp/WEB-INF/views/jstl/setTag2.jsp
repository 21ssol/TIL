<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="map" value="<%=new java.util.HashMap() %>"> </c:set>

<!-- hashMap은 키와 밸류값으로 지정된다. -->
<c:set target="${map}" property="name" value="왕눈이"></c:set>
<c:set target="${map}" property="aromi" value="아로미"></c:set>
<h2>
변수 map에 저장된 name 값 : ${map.name} <br>
변수 map에 저장된 aromi 값 : ${map.aromi}
</h2>
</body>
</html>