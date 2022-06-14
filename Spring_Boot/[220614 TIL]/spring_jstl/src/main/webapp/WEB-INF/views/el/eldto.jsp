<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="com.study.jstl.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>
EL 사용 안한 경우(스크립틀릿 사용)<br><br>
<%
	ELDTO eldto = (ELDTO)request.getAttribute("dto");
%>
	영화명: <%=eldto.getMovie() %> <br><br>
	주 연: <%=eldto.getName() %> <br><br>
<hr>
EL을 사용하는 경우 <br><br>
영화명: ${dto.movie} <br>
주 연: ${dto.name} <br><br>
<hr>
Type 2 : 주연 : ${dto.movie} - ${dto.name} <br><br>
<hr>
Type 3 : 주연(X) : ${requestScope.movie} <br><br>
<hr>
Type 4 : 주연(X) : ${movie}<br><br>
</h2>
</body>
</html>