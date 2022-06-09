<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, com.study.model.AddrDTO, com.study.utility.*"%>
    
<%
	String col = (String)request.getAttribute("col");
	String word = (String)request.getAttribute("word");
	int nowPage = (int)request.getAttribute("nowPage");
	List<AddrDTO> list = (List<AddrDTO>)request.getAttribute("list");
	String paging = (String)request.getAttribute("paging");
%>
<!DOCTYPE html>
<html>
<head>
<title>homepage</title>
<meta charset="UTF-8">
<script type="text/javascript">
  	function read(addressnum){
		let url  = "read/"+addressnum;
		location.href=url;
  	}
  	
  	function del(addressnum){
  		confirm("정말 삭제하시겠습니까?")
  		let url  = "/addr/delete/"+addressnum;
		location.href=url;
  	}
  </script>
</head>
<body>
<div class="container">
<h1 class="col-sm-offset-2 col-sm-10">주소록 목록</h1>
<form class="form-inline" action="list">
<div class="form-group">
<select class="form-control" name="col">
	<option value="name"<%if(col.equals("name")) out.print("selected"); %>>작성자</option>
	<option value="handphone" <%if(col.equals("handphone")) out.print("selected"); %>>연락처</option>
	<option value="address" <%if(col.equals("address")) out.print("selected"); %>>주소</option>
	<option value="total" <%if(col.equals("total")) out.print("selected"); %>>전체출력</option>
</select>
</div>
<div class="form-group">
<input type="text" class="form-control" placeholder="Enter 검색어" name='word' value="<%=word%>">
</div>
<button class='btn btn-default'>검색</button>
<button class='btn btn-default' type = "button" onclick="location.href='create'">등록</button>

</form>
<table class="table table-striped">
	<thead>
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>연락처</th>
			<th>주소</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody>
<% if(list.size()==0) { %>
<tr>
<td colspan='5'>등록된 글이 없습니다.</td></tr>
	
<% } else {
	for(int i=0; i<list.size(); i++) {
		AddrDTO dto = list.get(i);
		
		%>

		<tr>
			<td><%=dto.getAddressnum() %></td>
			<td>
			<a href="javascript:read('<%=dto.getAddressnum() %>')"><%=dto.getName() %></a>
			</td>
			<td><%=dto.getHandphone() %></td>
			<td><%=dto.getAddress() %></td>
			<td><a href="javascript:del('<%=dto.getAddressnum() %>')">삭제</a></td>
		</tr>
<% 	}  //for end
} //if end
%>
	</tbody>
</table>
<div>
	<%=paging %>
</div>
</div>
</body>
</html>