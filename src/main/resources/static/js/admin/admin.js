// 리스트 체크박스
var $all = $(".form-check-input.total");
var $checkboxes = $(".form-check-input.checkbox");
$(".app_sidebar_menu_item").on('click', function() {
	var $value = $(this).find('.sidebar_menu_arrow').css('transform');
	var angle = $value == 'matrix(1, 0, 0, 1, 0, 0)' ? 180 : 0;
	$(this).find('.sidebar_menu_arrow').css('transform', 'rotatez(' + angle + 'deg)');
	$(this).next().slideToggle();
});

$(".app_sidebar_menu_item").on('mouseover', function() {
	if ($(this).children().eq(1).children().text() === "사이트 바로가기") {
		$(this).css('background-color', '#00c7ae');
	} else {
		$(this).css('background-color', 'rgb(69,88,88)');
	}
});

$(".app_sidebar_menu_item").on('mouseout', function() {
	if ($(this).children().eq(1).children().text() === "사이트 바로가기") {
		$(this).css('background-color', 'rgb(69,88,88)');
	} else {
		$(this).css('background-color','rgb(59,56,62)' );
	}
});

/* ############################################################################# */
/* ########### 이미지 첨부파일 썸네일 변경 ########################################## */
/* ############################################################################# */

// 이미지 1개 _ 이미지 첨부 시 썸네일 변경
$("#icon_img").on('change', function(e) {
	var reader = new FileReader();
	let type = e.target.files[0].type;
	reader.readAsDataURL(e.target.files[0]);

	reader.onload = function(e) {
		let url = e.target.result;
		if (type.includes('image')) {
			$("#thumbnail").attr('src', url);
		} else {
			$("#thumbnail").attr('src', "../images/no-image.JPG");
		}
	}
});

// 이미지 2개 _ 이미지1 첨부 시 썸네일 변경
$("#icon_img1").on('change', function(e) {
	var reader1 = new FileReader();
	let type1 = e.target.files[0].type;
	reader1.readAsDataURL(e.target.files[0]);

	reader1.onload = function(e) {
		let url1 = e.target.result;
		if (type1.includes('image')) {
			$("#thumbnail1").attr('src', url1);
		} else {
			$("#thumbnail1").attr('src', "../images/no-image.JPG");
		}
	}
});

// 이미지 2개_ 이미지2 첨부 시 썸네일 변경
$("#icon_img2").on('change', function(e) {
	var reader2 = new FileReader();
	let type2 = e.target.files[0].type;
	reader2.readAsDataURL(e.target.files[0]);

	reader2.onload = function(e) {
		let url2 = e.target.result;
		if (type2.includes('image')) {
			$("#thumbnail2").attr('src', url2);
		} else {
			$("#thumbnail2").attr('src', "../images/no-image.JPG");
		}
	}
});

/* ############################################################################# */
/* ########### 리스트 체크 박스 ################################################### */
/* ############################################################################# */

// 리스트 체크박스 전체 선택
$all.on("click", function() {
	$checkboxes.prop("checked", $all.is(":checked"));
});

// 리스트 체크박스 각각 선택
$checkboxes.on("click", function() {
	$all.prop("checked", $checkboxes.filter(":checked").length == $checkboxes.length);
	console.log($checkboxes.filter(":checked").length)
});

/* ############################################################################# */
/* ########### 검색 Enter로 submit 가능 / 빈칸 입력시 alert ######################## */
/* ############################################################################# */

function searchList(theForm) {
	if (theForm.enter.value==""){
		alert("검색어를 입력하세요");
		return false;
	}
	theForm.submit();
}


/* ############################################################################# */
/* ########### 목록에서 삭제 버튼 ################################################# */
/* ############################################################################# */

// 회원 목록 - 회원 삭제
$("#deleteMemberButton").on('click', function () {
	if ($checkboxes.filter(":checked").length == 0) {
		alert("삭제할 회원을 선택하세요.");
		return;
	}
});

// 주문 관리 - 제품 목록 + 제품 삭제
$("#deleteProductBtn").on('click', function () {
	if ($checkboxes.filter(":checked").length == 0) {
		alert("삭제할 제품을 선택하세요.");
		return;
	}
});

// 주문 관리 - 주문 목록 + 주문 삭제
$("#deleteOrderBtn").on('click', function () {
	if ($checkboxes.filter(":checked").length == 0) {
		alert("삭제할 주문을 선택하세요.");
		return;
	}
});

// 수거서비스 + 신청내역 삭제
$("#deleteCollectServiceBtn").on('click', function () {
	if ($checkboxes.filter(":checked").length == 0) {
		alert("삭제할 신청내역을 선택하세요.");
		return;
	}
});

// 배너 관리 - 배너 목록 + 배너 삭제
$("#deleteBannerBtn").on('click', function () {
	if ($checkboxes.filter(":checked").length == 0) {
		alert("삭제할 배너를 선택하세요.");
		return;
	}
});

// 게시판 관리 - 댓글 목록 + 댓글 삭제
$("#deleteCommentBtn").on('click', function () {
	if ($checkboxes.filter(":checked").length == 0) {
		alert("삭제할 댓글을 선택하세요.");
		return;
	}
});
// 게시판 관리 - 게시판 목록 + 게시글 삭제
$("#deleteCommunityBtn").on('click', function () {
	if ($checkboxes.filter(":checked").length == 0) {
		alert("삭제할 게시글을 선택하세요.");
		return;
	}
});

// 줍깅 관리 - 배지 목록 + 배지 삭제
$("#deleteBadgeBtn").on('click', function () {
	if ($checkboxes.filter(":checked").length == 0) {
		alert("삭제할 배지을 선택하세요.");
		return;
	}
});



/* ############################################################################# */
/* ########### submit 유효성 검사 ################################################ */
/* ############################################################################# */

// 주문 관리 - 제품 목록 + 제품 추가
function addProduct() {
	if($("input[name=productName]").val() == ""){
		alert("제품명을 입력하세요!");
		$("input[name=productName]").focus();
		return;
	}

	if ($('#productCategory option:selected').val() == "CategoryStatus") {
		alert("카테고리를 선택하세요!");
		$("#productCategory").focus();
		return;
	}

	if($("input[name=productPrice]").val() == ""){
		alert("가격을 입력하세요!");
		$("input[name=productPrice]").focus();
		return;
	}

	if($("input[name=productCount]").val() == ""){
		alert("초기 입고 수량을 입력하세요!");
		$("input[name=productCount]").focus();
		return;
	}

	if($("input[name=productFileProfile]").next().attr("src") == "/images/admin/06.png"){
		alert("대표 이미지를 등록하세요!");
		return;
	}

	if($("input[name=productFileDetail]").next().attr("src") == "/images/admin/05.png"){
		alert("상세 설명 이미지를 등록하세요!");
		return;
	}

	productForm.submit();

}

// 주문 관리 - 제품 목록 + 제품 수정
function modifyProduct() {
	if($("input[name=productName]").val() == ""){
		alert("제품명을 입력하세요!");
		$("input[name=productName]").focus();
		return;
	}

	if ($('#productCategory option:selected').val() == "CategoryStatus") {
		alert("카테고리를 선택하세요!");
		$("#productCategory").focus();
		return;
	}

	if($("input[name=productPrice]").val() == ""){
		alert("가격을 입력하세요!");
		$("input[name=productPrice]").focus();
		return;
	}

	if($("input[name=productCount]").val() == ""){
		alert("입고 수량을 입력하세요!");
		$("input[name=productCount]").focus();
		return;
	}

	if($("input[name=productFileProfile]").next().attr("src") == "/images/admin/06.png"){
		alert("대표 이미지를 등록하세요!");
		return;
	}

	if($("input[name=productFileDetail]").next().attr("src") == "/images/admin/05.png"){
		alert("상세 설명 이미지를 등록하세요!");
		return;
	}

	productForm.submit();

}


// 프로그램 관리 - 프로그램 목록 + 프로그램 추가

function addProgram() {
	if($("input[name=programName]").val() == ""){
		alert("강좌명을 입력하세요!");
		$("input[name=programName]").focus();
		return;
	}

	if($("input[name=programPlace]").val() == ""){
		alert("장소를 입력하세요!");
		$("input[name=programPlace]").focus();
		return;
	}

	if($("input[name=programTime]").val() == ""){
		alert("수강시간을 입력하세요!");
		$("input[name=programTime]").focus();
		return;
	}

	if($("input[name=programLimitCount]").val() == ""){
		alert("인원수를 입력하세요!");
		$("input[name=programLimitCount]").focus();
		return;
	}

	if($('#programStatus option:selected').val() == "status"){
		alert("프로그램 상태를 선택하세요!");
		$("#programStatus").focus();
		return;
	}

	if($("input[name=programOpeningDate]").val() == "2022-07-22T12:00"){
		alert("시작일을 입력하세요!");
		$("input[name=programOpeningDate]").focus();
		return;
	}

	if($("input[name=programClosingDate]").val() == "2022-07-22T12:00"){
		alert("마감일을 입력하세요!");
		$("input[name=programClosingDate]").focus();
		return;
	}

	if($("textarea[name=programDetail]").val() == ""){
		alert("상세설명을 입력하세요!");
		$("textarea[name=programDetail]").focus();
		return;
	}

	if($("input[name=programFile]").next().attr("src") == "/images/admin/001.png"){
		alert("첨부파일을 등록하세요!");
		return;
	}

	programForm.submit();

}

// 프로그램 관리 - 프로그램 목록 + 프로그램 수정

function modifyProgram() {
	if($("input[name=programName]").val() == ""){
		alert("강좌명을 입력하세요!");
		$("input[name=programName]").focus();
		return;
	}

	if($("input[name=programPlace]").val() == ""){
		alert("장소를 입력하세요!");
		$("input[name=programPlace]").focus();
		return;
	}

	if($("input[name=programTime]").val() == ""){
		alert("수강시간을 입력하세요!");
		$("input[name=programTime]").focus();
		return;
	}

	if($("input[name=programLimitCount]").val() == ""){
		alert("인원수를 입력하세요!");
		$("input[name=programLimitCount]").focus();
		return;
	}

	if($('#programStatus option:selected').val() == "status"){
		alert("프로그램 상태를 선택하세요!");
		$("#programStatus").focus();
		return;
	}

	if($("input[name=programOpeningDate]").val() == "2022-07-22T12:00"){
		alert("시작일을 입력하세요!");
		$("input[name=programOpeningDate]").focus();
		return;
	}

	if($("input[name=programClosingDate]").val() == "2022-07-22T12:00"){
		alert("마감일을 입력하세요!");
		$("input[name=programClosingDate]").focus();
		return;
	}

	if($("textarea[name=programDetail]").val() == ""){
		alert("상세설명을 입력하세요!");
		$("textarea[name=programDetail]").focus();
		return;
	}

	if($("input[name=programFile]").next().attr("src") == "/images/admin/001.png"){
		alert("첨부파일을 등록하세요!");
		return;
	}

	programForm.submit();

}

// 줍깅 관리 - 코스 목록 + 코스 추가

function addJubJubCourse() {
	if ($("input[name=courseName]").val() == "") {
		alert("코스명을 입력하세요!");
		$("input[name=courseName]").focus();
		return;
	}

	if ($("input[name=courseArea]").val() == "") {
		alert("코스 지역을 입력하세요!");
		$("input[name=courseArea]").focus();
		return;
	}

	if ($("input[name=courseDistance]").val() == "") {
		alert("코스 거리을 입력하세요!");
		$("input[name=courseDistance]").focus();
		return;
	}

	if ($("input[name=courseTime]").val() == "") {
		alert("코스 소요 시간을 입력하세요!");
		$("input[name=courseTime]").focus();
		return;
	}

	if ($('#courseRank option:selected').val() == "grade") {
		alert("코스 난이도를 선택하세요!");
		$("#courseRank").focus();
		return;
	}

	if ($('#courseRank option:selected').val() == "Special") {

		if ($("input[name=courseOpeningDate]").val() == "2022-07-22T12:00") {
			alert("시작일을 입력하세요!");
			$("input[name=courseOpeningDate]").focus();
			return;
		}
		if ($("input[name=courseClosingDate]").val() == "2022-07-22T12:00") {
			alert("마감일을 입력하세요!");
			$("input[name=courseClosingDate]").focus();
			return;
		}
		if ($("input[name=courseStart]").val() == "") {
			alert("출발지를 입력하세요!");
			$("input[name=courseStart]").focus();
			return;
		}
		if ($("input[name=courseFinish]").val() == "") {
			alert("도착지를 입력하세요!");
			$("input[name=courseFinish]").focus();
			return;
		}
		if ($("input[name=courseFile]").next().attr("src") == "/images/admin/001.png") {
			alert("첨부파일을 등록하세요!");
			return;
		}

	} else {

		if ($("input[name=courseStart]").val() == "") {
			alert("출발지를 입력하세요!");
			$("input[name=courseStart]").focus();
			return;
		}
		if ($("input[name=courseFinish]").val() == "") {
			alert("도착지를 입력하세요!");
			$("input[name=courseFinish]").focus();
			return;
		}
		if ($("input[name=courseFile]").next().attr("src") == "/images/admin/001.png") {
			alert("첨부파일을 등록하세요!");
			return;
		}
	}
	addJubJubCourseForm.submit();
}


// 줍깅 관리 - 스팟 목록 + 스팟 추가

function addJubJubCourseSpot() {
	if ($("input[name=spotName]").val() == "") {
		alert("스팟명을 입력하세요!");
		$("input[name=spotName]").focus();
		return;
	}
	if ($('#courseSpot option:selected').val() == "course") {
		alert("해당 코스를 선택하세요!");
		$("#courseSpot").focus();
		return;
	}
	if ($("input[name=spotAddress]").val() == "") {
		alert("스팟 주소를 입력하세요!");
		$("input[name=spotAddress]").focus();
		return;
	}

	if ($('#spotNumber option:selected').val() == "spot") {
		alert("스팟 번호를 선택하세요!");
		$("#spotNumber").focus();
		return;
	}

	if ($("input[name=spotFile]").next().attr("src") == "/images/admin/002.png") {
		alert("QR코드를 생성하세요!");
		return;
	}

	addJubJubCourseSpotForm.submit();
}

// 줍깅 관리 - 스팟 목록 + 스팟 수정

function modifyJubJubCourseSpot() {
	if ($("input[name=spotName]").val() == "") {
		alert("스팟명을 입력하세요!");
		$("input[name=spotName]").focus();
		return;
	}
	if ($('#courseSpot option:selected').val() == "course") {
		alert("해당 코스를 선택하세요!");
		$("#courseSpot").focus();
		return;
	}
	if ($("input[name=spotAddress]").val() == "") {
		alert("스팟 주소를 입력하세요!");
		$("input[name=spotAddress]").focus();
		return;
	}

	if ($('#spotNumber option:selected').val() == "spot") {
		alert("스팟 번호를 선택하세요!");
		$("#spotNumber").focus();
		return;
	}

	if ($("input[name=spotFile]").next().attr("src") == "/images/admin/002.png") {
		alert("QR코드를 생성하세요!");
		return;
	}

	modifyJubJubCourseSpotForm.submit();
}

// 줍깅 관리 - 배지 목록 + 배지 추가

function addJubJubBadge() {
	if ($("input[name=BadgeName]").val() == "") {
		alert("배지이름을 입력하세요!");
		$("input[name=BadgeName]").focus();
		return;
	}
	if ($("input[name=badgeFile]").next().attr("src") == "/images/admin/003.png") {
		alert("배지사진을 업로드하세요!");
		return;
	}

	addJubJubBadgeForm.submit();
}

// 줍깅 관리 - 배지 목록 + 배지 수정

function modifyJubJubBadge() {
	if ($("input[name=BadgeName]").val() == "") {
		alert("배지이름을 입력하세요!");
		$("input[name=BadgeName]").focus();
		return;
	}
	if ($("input[name=badgeFile]").next().attr("src") == "/images/admin/003.png") {
		alert("배지사진을 업로드하세요!");
		return;
	}

	modifyJubJubBadgeForm.submit();
}


// 고객센터 - 공지사항 + 공지사항 추가

function addAdminNotice() {
	if ($("input[name=noticeTitle]").val() == "") {
		alert("제목을 입력하세요!");
		$("input[name=noticeTitle]").focus();
		return;
	}
	if ($('#noticeImportant option:selected').val() == "noticeSelect") {
		alert("중요 공지 여부를 선택하세요!");
		$("#noticeImportant").focus();
		return;
	}

	if($("textarea[name=noticeContent]").val() == ""){
		alert("공지 내용을 입력하세요!");
		$("textarea[name=noticeContent]").focus();
		return;
	}
	addAdminNoticeForm.submit();
}

// 고객센터 - 공지사항 + 공지사항 수정

function modifyAdminNotice() {
	if ($("input[name=noticeTitle]").val() == "") {
		alert("제목을 입력하세요!");
		$("input[name=noticeTitle]").focus();
		return;
	}
	if ($('#noticeImportant option:selected').val() == "noticeSelect") {
		alert("중요 공지 여부를 선택하세요!");
		$("#noticeImportant").focus();
		return;
	}

	if($("textarea[name=noticeContent]").val() == ""){
		alert("공지 내용을 입력하세요!");
		$("textarea[name=noticeContent]").focus();
		return;
	}
	modifyAdminNoticeForm.submit();
}


// 고객센터 - 배지 목록 + 배지 추가

function addBanner() {
	if ($("input[name=bannerName]").next().attr("src") == "/images/admin/02.png") {
		alert("배너 사진을 업로드하세요!");
		return;
	}

	addBannerForm.submit();
}

// 고객센터 - 배지목록 + 배지 수정

function modifyBanner() {
	if ($("input[name=bannerName]").next().attr("src") == "/images/admin/02.png") {
		alert("배너 사진을 업로드하세요!");
		return;
	}

	modifyBannerForm.submit();
}