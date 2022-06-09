<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.study.model.* " %> 
<%
 	AddrDTO dto = (AddrDTO)request.getAttribute("dto"); //한건의 레코드 조회
%>
<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
  <script>
  	function update() {  //수정페이지로 이동
  		let url = '/addr/update/<%=dto.getAddressnum() %>';
  		location.href = url;
  	}
  	
  	function del() {  //삭제페이지로 이동
  		let url = '/addr/delete/<%=dto.getAddressnum() %>';
  		location.href = url;
  	}
  	
  </script>
</head>
<body> 
<div class="container">
<h1>주소록 조회</h1>
<div class="panel panel-default">
	<div class="panel-heading">작성자</div>
	<div class="panel-body"><%=dto.getName() %></div>
	
	<div class="panel-heading">전화번호</div>
	<div class="panel-body"><%=dto.getHandphone() %></div>
	
	<div class="panel-heading">우편번호</div>
	<div class="panel-body"><%=dto.getZipcode() %></div>
	
	<div class="panel-heading">주소</div>
	<div class="panel-body"><%=dto.getAddress() %></div>

	<div class="panel-heading">상세주소</div>
	<div class="panel-body" style='height:170px'>
	<%=dto.getAddress2() %></div>
	
	
</div>
<div>
	<button onclick = "location.href='/addr/create'">등록</button>
	<button onclick = "update()">수정</button>
	<button onclick = "del()">삭제</button>
	<button onclick = "location.href='/addr/list'">목록</button>
</div>
<br>
</div>
</body> 
</html>