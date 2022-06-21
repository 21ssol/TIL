<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<link rel="stylesheet" href="/css/style.css">
<script type="text/javascript">
	function cart() {
		if ('${sessionScope.id}' == '') { //로그인이 안된거
			alert('먼저 로그인을 하세요');
			location.href = '/member/login';
			return;
		}
		// 카트 테이블에 등록확인 창 보여주기(비동기로 구현)
	}

	function order() {
		if ('${sessionScope.id}' == '') { //로그인이 안된거
			alert('먼저 로그인을 하세요');
			location.href = '/member/login';
			return;
		}
		// 주문서 작성으로 이동 주문생성(비동기 통신)
	}
</script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<h4>
					<img src="/svg/box2-heart.svg"> 상품 정보
				</h4>
				<img class="img-rounded" src="/contents/storage/${dto.filename}"
					style="width: 250px">
			</div>
			<div class="col-sm-6">
				<h4>
					<img src="/svg/rulers.svg"> 사이즈 및 수량
				</h4>
				<ul class="list-group">
					<li class="list-group-item">사이즈 : <c:choose>
							<c:when test="${dto.cateno == 1 }">
								<select class="form-select" aria-label="Default select example">
									<option selected>사이즈 선택</option>
									<option value="1">L</option>
									<option value="2">M</option>
									<option value="3">S</option>
								</select>
							</c:when>
							<c:when test="${dto.cateno == 2 }">
								<select class="form-select" aria-label="Default select example"
									disabled="disabled">
									<!-- disabled="disabled" 비활성화 -->
									<option selected>사이즈 선택</option>
								</select>
							</c:when>
							<c:when test="${dto.cateno == 3 }">
								<!-- 신발일때 -->
								<select class="form-select" aria-label="Default select example">
									<option selected>사이즈 선택</option>
									<option value="220">220</option>
									<option value="230">230</option>
									<option value="240">240</option>
									<option value="250">250</option>
									<option value="260">260</option>
								</select>
							</c:when>
						</c:choose>
					<li class="list-group-item">가격 : ${dto.price }
					<li class="list-group-item">재고 : ${dto.stock }
					<li class="list-group-item">수량 : 
					<input type="number" name="quantity" min=0 max=20 value="1">
					<li class="list-group-item"><a href="javascript:cart()"> 
					<img class='btn' src="/svg/cart4.svg" /></a> 
							<a href="javascript:order()">
							<img class='btn' src="/svg/bag-heart-fill.svg" /> </a> 
							<a href="javascript:history.back()"> 
					<img class='btn' src="/svg/arrow-return-left.svg" /></a>
						<li class="list-group-item">
						<h3>상품 정보</h3>
						${dto.pname }<br><br>
						${dto.detail }
						</ul>
<div class='row'>
<div class="col-lg-12">
<!-- panel start-->
<div class="panel panel-default">
<div class="panel-heading">
        <i class="fa fa-comments fa-fw"></i> 댓글
        <button id='addReplyBtn'class='btn btn-primary btn-xs pull-right'>New Reply</button>
</div>
<div class="panel-body">
        <ul class="chat list-group">
                <li class="left clearfix" data-rno="12">
                        <div>
                                <div class="header">
                                        <strong class="primary-font">user1</strong> 
                                        <small class="pull-right text-muted">2019-05-12</small>
                                </div>
                           <p>Good job!</p>
                     </div>
                </li>
        	</ul>
        </div>
 
<div class="panel-footer"></div>
</div>
<!-- panel end-->
</div>
<!--  col-lg-12 end -->
</div>
<!-- row end -->
 
</div>
</div>
</div>
</body>
</html>