<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="util" uri="/ELFunctions"%>
<!DOCTYPE html>
<html>
<head>
<title>공지사항 조회</title>
<meta charset="utf-8">

<script type="text/javascript">
	function updateM() {
		var url = "update";
		url += "?noticeno=${dto.noticeno}";
		location.href = url;
	}
	function deleteM() {
		var url = "delete";
		url += "?noticeno=${dto.noticeno}";
		location.href = url;
	}

	function listM() {
		var url = "list";
		url += "?nowPage=${param.nowPage}";
		url += "&col=${param.col}";
		url += "&word=${param.word}";
		location.href = url;
	}
</script>

</head>
<body>
	<div class="container">

		<h2>공지사항 조회</h2>
		<div class="panel panel-default">
			<div class="panel-heading">작성자</div>
			<div class="panel-body">${dto.wname}</div>

			<div class="panel-heading">제목</div>
			<div class="panel-body">${dto.title}</div>

			<div class="panel-heading">내용</div>
			<div class="panel-body">${dto.content}</div>

			<div class="panel-heading">조회수</div>
			<div class="panel-body">${dto.cnt}</div>

			<div class="panel-heading">등록일</div>
			<div class="panel-body">${dto.rdate}</div>

		</div>
		<div>
			<button type="button" class="btn" onclick="listM()">목록</button>
				<c:if
					test="${not empty sessionScope.id && sessionScope.grade == 'A'}">
					<button type="button" class="btn"
						onclick="location.href='./create'">등록</button>
					<button type="button" class="btn" onclick="updateM()">수정</button>
					<button type="button" class="btn" onclick="deleteM()">삭제</button>
				</c:if>
		</div>
	</div>
</body>
</html>