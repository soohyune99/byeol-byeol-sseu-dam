/* program.html */

const $marketSearch = $("section.market-keyword-search-form");
const $searchArea = $("input.pro-form-control");
const $keywordBox = $(".keyword-box");
let check = false;
let $category = $("ul.tab li a.btn.btn-link");

/* 검색바 click했을 때 keyword-box 열기 */
$searchArea.on("click", function () {
    $marketSearch.addClass("open");
    $keywordBox.css('display', 'block');
    check = !check;
});

/* 외부 영역 클릭시 keyword-box 닫기 */
$(document).mouseup(function (e){
    if($keywordBox.has(e.target).length === 0){
        $keywordBox.hide();
    }
});

/* ESC 키 누를시 keyword-box 닫기 */
$(document).keydown(function(e){
    //keyCode 구 브라우저, which 현재 브라우저
    var code = e.keyCode || e.which;

    if (code == 27) { // 27은 ESC 키번호
        $keywordBox.hide(); //
        $searchArea.val(""); // input에 입력된 글자 빈칸으로 초기화
    }
});

/* 검색어 입력 시 x버튼 생성 */
$searchArea.on('keyup', function () {
    if ($searchArea.val() != "") {
        $(".input-group-append").css('display', 'block');
    } else {
        $(".input-group-append").css('display', 'none');
    }
});

/* x버튼 클릭 시 검색어 삭제 */
$(".btn-keyword-del").on('click', function () {
    $searchArea.val("");
    $(".input-group-append").css('display', 'none');
});

/* STATUS 카테고리 클릭 */
$category.on("click", function () {
    $category.removeClass("router-link-exact-active");
    $(this).addClass("router-link-exact-active");
});

/* 클릭 > 블러 먼저 진행되도록 하고 안에 클릭한 text 넣기 */

function selectKeyword(e){
    $marketSearch.removeClass("open");
    $keywordBox.css('display', 'none');

    /* enter로 submit 되도록 */
    $("input[name='enter']").val($(e).text());

    /* 검색 text 뒷부분에 커서 깜빡임  */
    $searchArea.focus();
    /* 검색창에 text가 입력되면 */
    if ($searchArea.val() != "") {
        $(".input-group-append").css('display', 'block');
    } else {
        $(".input-group-append").css('display', 'none');
    }
}
