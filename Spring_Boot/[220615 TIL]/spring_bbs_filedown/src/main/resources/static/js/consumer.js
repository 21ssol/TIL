$(function() {//페이지가 로딩될때
	showList();
	showPage();
}); //page loading function end  

let replyUL = $(".chat");  // 클래스가 chat인거를 찾는다.(read.jsp에 있음)
let replyPageFooter = $(".panel-footer");

function showList() {
	// 비동기 통신인데 파라미터 형식이 json. 값은 read.jsp에 ${}있음
	getList({ bbsno: bbsno, sno: sno, eno: eno })
		.then(list => {  // 프로미스
			let str = ""

			for (var i = 0; i < list.length; i++) {
				str += "<li class='list-group-item' data-rnum='" + list[i].rnum + "'>";
				str += "<div><div class='header'><strong class='primary-font'>" + list[i].id + "</strong>";
				str += "<small class='pull-right text-muted'>" + list[i].regdate + "</small></div>";
				str += replaceAll(list[i].content, '\n', '<br>') + "</div></li>";
			}

			replyUL.html(str);
		});

}//showList() end

function replaceAll(str, searchStr, replaceStr) { // replaceAll 함수 
	return str.split(searchStr).join(replaceStr); // split으로 나눠서 배열로 만들고 join으로 하나의 문자열로 만듦
}

let param = '';
param = "nPage=" + nPage;
param += "&nowPage=" + nowPage;
param += "&bbsno=" + bbsno;
param += "&col=" + colx;
param += "&word=" + wordx;

function showPage() {
	getPage(param)
		.then(paging => {
			console.log(paging);
			let str = "<div><small class='text-muted'>" + paging + "</small></div>";

			replyPageFooter.html(str);
		});
}

let modal = $(".modal");
let modalInputContent = modal.find("textarea[name='content']");

let modalModBtn = $("#modalModBtn");
let modalRemoveBtn = $("#modalRemoveBtn");
let modalRegisterBtn = $("#modalRegisterBtn");

$("#modalCloseBtn").on("click", function(e) {
	modal.modal('hide');
});

$("#addReplyBtn").on("click", function(e) {
	modalInputContent.val("");
	modal.find("button[id !='modalCloseBtn']").hide();

	modalRegisterBtn.show();

	$(".modal").modal("show");

});

modalRegisterBtn.on("click", function(e) {

	if (modalInputContent.val() == '') {
		alert("댓글을 입력하세요")
		return;
	}

	let reply = {   // json 객체로 만듦
		content: modalInputContent.val(),
		id: 'user1',
		bbsno: bbsno
	};
	add(reply)
		.then(result => {
			modal.find("input").val("");
			modal.modal("hide");

			showList();
			showPage();

		}); //end add

}); //end modalRegisterBtn.on

//댓글 조회 클릭 이벤트 처리 
$(".chat").on("click", "li", function(e) {

	let rnum = $(this).data("rnum");

	get(rnum)  // 비동기통신 요청
		.then(reply => {

			modalInputContent.val(reply.content);
			modal.data("rnum", reply.rnum);

			modal.find("button[id !='modalCloseBtn']").hide();

			modalModBtn.show();
			modalRemoveBtn.show();

			$(".modal").modal("show");

		});
});

// 댓글 수정
modalModBtn.on("click", function(e) {

	let reply = { rnum: modal.data("rnum"), content: modalInputContent.val() };
	update(reply)
		.then(result => {
			modal.modal("hide");
			showList();
			showPage();
		});

});//modify

//댓글 삭제
modalRemoveBtn.on("click", function(e) {

	let rnum = modal.data("rnum");
	remove(rnum)
		.then(result => {
			modal.modal("hide");
			showList();
			showPage();
		});

});//remove

