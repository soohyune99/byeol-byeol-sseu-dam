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


/* 검색 Enter로 submit 가능 / 빈칸 입력시 alert */
// function searchList(theForm) {
//     if (theForm.enter.value == "") {
//         alert("검색어를 입력하세요");
//         return false;
//     }
//     // theForm.submit();
//     location.href = "/program/list/search" + "?keyword=" + $("#__BVID__183").val();
// }
//
//
// $(".programAllList").on('click', function () {
//     location.href = "/program/list";
// });
//
// $(".programStatus").on('click', function () {
//     location.href = "/program/list/status" + "?programStatus=" + $(this).text();
//     // $(this).css('font-weight', 700);
//     // $(this).css('border-bottom-color', '#00C7AE');
//     // $(this).css('border-radius', 0);
//     // $(this).css('color', '#2d2d2d');
//     $(this).css('display', 'none');
// });


/* 검색창 추천 검색어 클릭시 검색 칸으로 입력 */

// const inputBox = document.querySelector("input");
// const recommendBox = document.querySelector("#recommend");
// const texts = document.querySelectorAll(".text");
//
// inputBox.addEventListener("keyup", (e) => {
//     if (e.target.value.length > 0) {
//         recommendBox.classList.remove('invisible');
//         texts.forEach((textEl) => {
//             textEl.textContent = e.target.value;
//         })
//     } else {
//         recommendBox.classList.add('invisible');
//     }
// })

// 삭제
// /* 검색바 블러했을 때 드롭박스 닫히기 */
// $searchArea.on("blur", function(){
//     $marketSearch.removeClass("open");
//     $keywordBox.css('display', 'none');
// });

// $("li.sub").on("click", function () {
//     $marketSearch.removeClass("open");
//     $keywordBox.css('display', 'none');
//     $("input[name='enter']").val($(this).text());
//     /* 뒷부분 커서 */
//     $searchArea.focus();
//     /*  */
//     if ($searchArea.val() != "") {
//         $(".input-group-append").css('display', 'block');
//     } else {
//         $(".input-group-append").css('display', 'none');
//     }
//
//
// });

// $searchArea.on("blur", function () {
//     $marketSearch.removeClass("open");
//     $keywordBox.css('display', 'none');
// })


// $searchArea.on("fo");
// document.getElementById("ID").value


// $selectSub = $keywordBox($(this).text());
// if ($keywordBox($(this).text()).on('click', function () {
//     $('input[name=enter]').attr('value', $selectSub);
// }));

// $('input[name=enter]').attr('value',$selectSub);
// $('input[id=enterInput]').attr('value',$selectSub);
//
// $searchArea.val($keywordBox($(this).text()));

// var body = document.querySelector("body");
// body.addEventListener('click', clickBodyEvent);
//
// function clickBodyEvent(event) {
//     var target = event.target;
//     // console.log(target);
//
//     if(target == event.currentTarget.querySelector(".keyword-box") ){
//         return ;}
//     if(target == event.currentTarget.querySelector(".input-group") ){
//         return ;}
//     if(target == event.currentTarget.querySelector("body") ){
//         $marketSearch.removeClass("open");
//         $keywordBox.css('display', 'none');
//     }
//
// }