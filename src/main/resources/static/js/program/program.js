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
// ################################################################################################
// ################################################################################################
// ################################################################################################
// ################################################################################################
// ################################################################################################

// let programStatus // 프로그램 상태 (모집예정, 모집중, 모집완료, 마감)
// let keyword // 검색어
// let count // 프로그램 수
//
// /* 전체 program List _ Ajax */
//
// show(); // program.html 들어오면 바로 실행되는 함수
//
// /* '전체' 클릭시 실행되는 함수  */
// $(".programAllList").on('click', function () {
//     show(); // show 실행
// });
//
// /* 원하는 programs로 programList를 실행하는 함수 */
// function show() {
//     $.ajax({
//         url: "/pro/list", //전체 programAllList()를 실행한 programs 를 가지고 작동
//         type: "get",
//         success: function (programs) {
//             if (programs.length != 0) {
//                 programList(programs);
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             } else {
//                 noProgramList(); // 해당 programs가 없을 경우 실행되는 함수 추가 필요 (돋보기)
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             }
//         }
//     });
// }
//
// /* '프로그램 상태' 클릭시 실행되는 함수 */
// $(".programStatus").on('click', function () {
//     programStatus = $(this).text(); //programStatus class 의 누른 text로 실행
//     $.ajax({
//         url: "/pro/list/" + programStatus, // 경로 뒤에 programStatus 붙여서 해당 restController 사용
//         type: "get",
//         success: function (programs) {
//             if (programs.length != 0) {
//                 programList(programs);
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             } else {
//                 noProgramList(); // 해당 programs가 없을 경우 실행되는 함수 추가 필요 (돋보기)
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             }
//         }
//     });
// });
//
//
// /* keyword 입력시 실행되는 함수 */
// function searchList(theForm) {
//     keyword = theForm.enter.value; // keyword = enter키로 작동한 form 태그 안에 있는 값
//
//     // 빈칸 입력시
//     if (keyword == "") {
//         alert("검색어를 입력하세요");
//         $keywordBox.hide(); // 추천서비스 닫기
//         $category.removeClass("router-link-exact-active");
//         $(".programAllList").addClass("router-link-exact-active");
//
//         return false;
//     }
//     // 글자 입력시
//     $.ajax({
//         url: "/pro/list/" + keyword, // 경로 뒤에 keyword 붙여서 해당 restController 사용
//         type: "post",
//         success: function (programs) {
//             $category.removeClass("router-link-exact-active");
//             $(".programAllList").addClass("router-link-exact-active");
//             $(".keyword-box").hide(); // 엔터 입력시 keyword 박스 닫힘.
//             if (programs.length != 0) {
//                 programList(programs);
//                 programCountNum(programs); // 프로그램 수 Count 함수
//
//             } else {
//                 noProgramList();// 해당 programs가 없을 경우 실행되는 함수 추가 필요 (돋보기)
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             }
//             /* 검색 후 다른 상태 누른 경우 text 내용 없애기 */
//             $(".programStatus").on('click', function () {
//                 $searchArea.val("");
//             });
//         }
//     });
// }
//
// /* 해당 프로그램 개수에 따라 article을 반복해서 html에 넣어줄 함수 */
// function programList(programs) {
//     let text = "";
//
//     programs.forEach(program => {
//         text += "<article data-v-85c61b32='' data-v-18e85706='' class='product-list-item responsive'>";
//         text += "<input type='hidden' id='programId' href='" + program.programId + "'>"
//         /* a태그 안에 link 수정 필요 */
//         text += "<a data-v-85c61b32='' onclick='moveDetail(this)' data-testid='product-list-item' style='cursor: pointer;'>";
//         text += "<div data-v-85c61b32='' class='item-image'>";
//         text += "<article data-v-1a98b297='' data-v-85c61b32='' class='preview-image'>";
//         text += "<div data-v-1a98b297='' class='image-wrap' style='padding-top: 66.6667%;'>";
//         /* 해당 이미지 경로 */
//         text += "<img data-v-1a98b297='' src='" + program.programFileProfilePath + "'>";
//         text += "</div>";
//         text += "</article>";
//         text += "</div>";
//         text += "<div data-v-85c61b32='' class='service-name'>";
//         /* 프로그램 상태 : 모집예정 greenIcon , 모집중 blueIcon, 모집완료 redIcon, 마감 grayIcon / class 다르게 줘야함, 수정필요 */
//         if(program.programStatus == '모집예정'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon greenIcon'>" + program.programStatus + "</span>";
//         } else if(program.programStatus == '모집중'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon blueIcon'>" + program.programStatus + "</span>";
//         } else if(program.programStatus == '모집완료'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon redIcon'>" + program.programStatus + "</span>";
//         } else if(program.programStatus == '마감'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon grayIcon'>" + program.programStatus + "</span>";
//         }
//         text += "</div>";
//         text += "<div data-v-85c61b32='' class='item-title'>";
//         text += "<div data-v-a3afae98='' data-v-85c61b32='' class='collapsed'>";
//         text += "<div data-v-a3afae98='' class='line-clamp'\n" +
//             "            style='line-height: 1.5; max-height: 3rem; -webkit-line-clamp: 2;'>";
//         /* 프로그램 TITLE 들어가는 자리 */
//         text += "<h3 data-v-85c61b32='' data-v-a3afae98=''> " + program.programName + "</h3>";
//         text += "</div>";
//         text += "</div>";
//         text += "</div>";
//         text += "</a>";
//         text += "</article>";
//
//     });
//     $("#programListId").html(text);// #programListId 밑에 반복 해서 text들을 넣어줌
//     $("#noArticle1").html("");// 프로그램이 없는 경우 ""로 div를 채워줌
// }
//
// /* 해당 프로그램이 없는 경우 보여줄 돋보기 함수 */
// function noProgramList() {
//     let text = "";
//     text += "<div class='infinite-status-prompt'>"
//     text += "<article class='no-items align-self-center text-center no-items'>"
//     text += "<i data-v-414f00e5=''><img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI4MCIgaGVpZ2h0PSI4MCIgdmlld0JveD0iMCAwIDgwIDgwIj4KICAgIDxkZWZzPgogICAgICAgIDxsaW5lYXJHcmFkaWVudCBpZD0icHJlZml4X19hIiB4MT0iMCUiIHgyPSIxMDAlIiB5MT0iNTAlIiB5Mj0iNTAlIj4KICAgICAgICAgICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzAwQzdBRSIvPgogICAgICAgICAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiM0Q0M4RTUiLz4KICAgICAgICA8L2xpbmVhckdyYWRpZW50PgogICAgPC9kZWZzPgogICAgPGcgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4KICAgICAgICA8cGF0aCBmaWxsPSIjRkZGIiBkPSJNMCAwSDM3NVY2NjdIMHoiIHRyYW5zZm9ybT0idHJhbnNsYXRlKC00NCAtMTQ2KSIvPgogICAgICAgIDxnIHRyYW5zZm9ybT0idHJhbnNsYXRlKC00NCAtMTQ2KSB0cmFuc2xhdGUoNDQgMTQ2KSI+CiAgICAgICAgICAgIDxjaXJjbGUgY3g9IjQwIiBjeT0iNDAiIHI9IjQwIiBmaWxsPSJ1cmwoI3ByZWZpeF9fYSkiIGZpbGwtb3BhY2l0eT0iLjUiIGZpbGwtcnVsZT0ibm9uemVybyIvPgogICAgICAgICAgICA8cGF0aCBkPSJNMTYgNjRMNjQgNjQgNjQgMTYgMTYgMTZ6Ii8+CiAgICAgICAgICAgIDxwYXRoIHN0cm9rZT0iI0ZGRiIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBzdHJva2Utd2lkdGg9IjMiIGQ9Ik00NS40ODYgMjguNTE1YzQuNjg1IDQuNjg3IDQuNjg1IDEyLjI4MyAwIDE2Ljk3LTQuNjg3IDQuNjg3LTEyLjI4NiA0LjY4Ny0xNi45NyAwLTQuNjg4LTQuNjg3LTQuNjg4LTEyLjI4MyAwLTE2Ljk3IDQuNjg0LTQuNjg3IDEyLjI4My00LjY4NyAxNi45NyAwek00NiA0Nmw5IDkiLz4KICAgICAgICA8L2c+CiAgICA8L2c+Cjwvc3ZnPgo=' alt='empty'></i>"
//     text += "<h3 data-v-414f00e5=''></h3>"
//     text += "<p data-v-414f00e5='' class='help-block p2'>"
//     text += "<strong> 선택하신 조건으로 <br> 진행중인 프로그램이 없습니다. </strong><br> 다른 쓰담 프로그램을 탐색해보세요.</p>"
//     text += "</article>"
//     text += "</div>"
//     text += "<div class='infinite-status-prompt noArticle2'>"
//     text += "</div>"
//     text += "<div data-v-644ea9c9='' class='infinite-status-prompt noArticle2'>"
//     text += "<button class='btn-try-infinite'></button>"
//     text += "</div>"
//     $("#noArticle1").html(text); // #noArticleProgram 밑에 text들을 넣어줌
//     $("#programListId").html(""); // 프로그램이 없는 경우 ""로 div를 채워줌
// }
//
// /* 프로그램 수 Count */
// function programCountNum(programs) {
//     count = programs.length;
//     let text = "";
//     text +="<strong data-v-219a3136=''>" + count + "</strong>"
//     text +="<span data-v-219a3136=''> 개 서비스 </span>"
//
//     $("#totalCountPlace").html(text);
// }
//
// /* 프로그램 클릭시 programId를 이용하여 해당 program 선택 */
// function moveDetail(obj) {
//     // alert($(obj).closest(".responsive").find('#programId').attr("href"));
//     location.href = "/program/detail" + "?programId=" + $(obj).closest(".responsive").find('#programId').attr("href");
// }

/* ###########################   무한스크롤 연습   ############################################################################  */

// let programStatus // 프로그램 상태 (모집예정, 모집중, 모집완료, 마감)
// let keyword // 검색어
// let count // 프로그램 수
// let page = 1;

/* ================================== Infinite Scroll ==================================*/
// $(window).scroll(function(){
//     console.log("$(window).scrollTop():"+ $(window).scrollTop());
//     console.log("$(document).height():"+$(document).height());
//     console.log("$(window).height():"+$(window).height());
//     if($(window).scrollTop() == $(document).height() - $(window).height()){
//         infiniteScroll(page, programList);
//         page++;
//         // alert(page);
//     }
// });
//
// function infiniteScroll(page, callback, error){
//     // alert(page);
//     $.ajax({
//         url: "/prod/scroll/" + page,
//         type: "get",
//         success: function(programs, status, xhr){
//             if(callback){
//                 callback(programs);
//             }
//         },
//         error: function(xhr, status, err){
//             if(error){
//                 error(err);
//             }
//         }
//     });
// }

// id 로 input 객체를 가져온다.
// var keyword = document.getElementById("input");
//
//
// // 가져온 객체에 EventListener 를 추가한다. keypress = 키입력 감지
// keyword.addEventListener('keypress', function(key){
//
//     // key.key 의 값이 Enter 일 경우 코드 실행
//     // key.keyCode == 13 도 동일한 기능을 한다.
//     if(key.key == 'Enter'){
//         var inputValue = input.value;
//         console.log(inputValue);
//     }
// })
// alert(keyword);

/* ================================== / Infinite Scroll ==================================*/
// let keyword
/* 전체 program List _ Ajax */
// let input = document.getElementById("__BVID__183");
// // alert(input);//[object HTMLInputElement]
// // 가져온 객체에 EventListener 를 추가한다. keypress = 키입력 감지
// input.addEventListener('keypress', function(key){
//
//     // key.key 의 값이 Enter 일 경우 코드 실행
//     // key.keyCode == 13 도 동일한 기능을 한다.
//     if(key.key == 'Enter'){
//         // 입력창에 키워드 입력
//         keyword = input.value;
//         // console.log(inputValue);
//     }
// })
// let search
// let programStatus =
// $(".programStatus").on('click', function () { (this).text()});
// $(".programStatus").on('click', function () { alert((this).text());});
// let keyword = searchList(theForm){ theForm.enter.value};

// console.log(programStatus);
// console.log(keyword);
// show(); // program.html 들어오면 바로 실행되는 함수
// // alert("f");
// /* '전체' 클릭시 실행되는 함수  */
// $(".programAllList").on('click', function () {
//     show(); // show 실행
//     // alert("faeeragergareg");
// });

// /* 원하는 programs로 programList를 실행하는 함수 */
// function show(keyword) {
//     $.ajax({
//         url: "/prod/list" + search, //전체 programAllList()를 실행한 programs 를 가지고 작동
//         type: "get",
//         success: function (programs) {
//             if (programs.length != 0) {
//                 programList(programs);
//                 // programCountNum(programs); // 프로그램 수 Count 함수
//             } else {
//                 noProgramList(); // 해당 programs가 없을 경우 실행되는 함수 추가 필요 (돋보기)
//                 // programCountNum(programs); // 프로그램 수 Count 함수
//             }
//         }
//     });
// }

// /* '프로그램 상태' 클릭시 실행되는 함수 */
// $(".programStatus").on('click', function () {
//     programStatus = $(this).text(); //programStatus class 의 누른 text로 실행
//     $.ajax({
//         url: "/prod/list/" + programStatus, // 경로 뒤에 programStatus 붙여서 해당 restController 사용
//         type: "get",
//         success: function (programs) {
//             if (programs.length != 0) {
//                 programList(programs);
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             } else {
//                 noProgramList(); // 해당 programs가 없을 경우 실행되는 함수 추가 필요 (돋보기)
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             }
//         }
//     });
// });


// /* keyword 입력시 실행되는 함수 */
// function searchList(theForm) {
//     keyword = theForm.enter.value; // keyword = enter키로 작동한 form 태그 안에 있는 값
//
//     // 빈칸 입력시
//     if (keyword == "") {
//         alert("검색어를 입력하세요");
//         $keywordBox.hide(); // 추천서비스 닫기
//         $category.removeClass("router-link-exact-active");
//         $(".programAllList").addClass("router-link-exact-active");
//         return false;
//     }
//     // 글자 입력시
//     $.ajax({
//         url: "/pro/list/" + keyword, // 경로 뒤에 keyword 붙여서 해당 restController 사용
//         type: "post",
//         success: function (programs) {
//             $category.removeClass("router-link-exact-active");
//             $(".programAllList").addClass("router-link-exact-active");
//             $(".keyword-box").hide(); // 엔터 입력시 keyword 박스 닫힘.
//
//             if (programs.length != 0) {
//                 programList(programs);
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             } else {
//                 noProgramList();// 해당 programs가 없을 경우 실행되는 함수 추가 필요 (돋보기)
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             }
//             /* 검색 후 다른 상태 누른 경우 text 내용 없애기 */
//             $(".programStatus").on('click', function () {
//                 $searchArea.val("");
//             });
//         }
//     });
// }

/* 해당 프로그램 개수에 따라 article을 반복해서 html에 넣어줄 함수 */
// function programList(programs) {
//     let text = "";
//     console.log(programs.length);
//
//     programs.forEach(program => {
//         text += "<article data-v-85c61b32='' data-v-18e85706='' class='product-list-item responsive'>";
//         text += "<input type='hidden' id='programId' href='" + program.programId + "'>"
//         /* a태그 안에 link 수정 필요 */
//         text += "<a data-v-85c61b32='' onclick='moveDetail(this)' data-testid='product-list-item' style='cursor: pointer;'>";
//         text += "<div data-v-85c61b32='' class='item-image'>";
//         text += "<article data-v-1a98b297='' data-v-85c61b32='' class='preview-image'>";
//         text += "<div data-v-1a98b297='' class='image-wrap' style='padding-top: 66.6667%;'>";
//         /* 해당 이미지 경로 */
//         text += "<img data-v-1a98b297='' src='" + program.programFileProfilePath + "'>";
//         text += "</div>";
//         text += "</article>";
//         text += "</div>";
//         text += "<div data-v-85c61b32='' class='service-name'>";
//         /* 프로그램 상태 : 모집예정 greenIcon , 모집중 blueIcon, 모집완료 redIcon, 마감 grayIcon / class 다르게 줘야함, 수정필요 */
//         if(program.programStatus == '모집예정'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon greenIcon'>" + program.programStatus + "</span>";
//         } else if(program.programStatus == '모집중'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon blueIcon'>" + program.programStatus + "</span>";
//         } else if(program.programStatus == '모집완료'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon redIcon'>" + program.programStatus + "</span>";
//         } else if(program.programStatus == '마감'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon grayIcon'>" + program.programStatus + "</span>";
//         }
//         text += "</div>";
//         text += "<div data-v-85c61b32='' class='item-title'>";
//         text += "<div data-v-a3afae98='' data-v-85c61b32='' class='collapsed'>";
//         text += "<div data-v-a3afae98='' class='line-clamp'\n" +
//             "            style='line-height: 1.5; max-height: 3rem; -webkit-line-clamp: 2;'>";
//         /* 프로그램 TITLE 들어가는 자리 */
//         text += "<h3 data-v-85c61b32='' data-v-a3afae98=''> " + program.programName + "</h3>";
//         text += "</div>";
//         text += "</div>";
//         text += "</div>";
//         text += "</a>";
//         text += "</article>";
//
//     });
//     $("#programListId").html(text);// #programListId 밑에 반복 해서 text들을 넣어줌
//     // $("#programListId").append(text); //무한스크롤때 쓰기....하지만 다른기능들이 안됨.....ㅠㅠㅠㅠㅠ
//     $("#noArticle1").html("");// 프로그램이 없는 경우 ""로 div를 채워줌
// }

// /* 해당 프로그램이 없는 경우 보여줄 돋보기 함수 */
// function noProgramList() {
//     let text = "";
//     text += "<div class='infinite-status-prompt'>"
//     text += "<article class='no-items align-self-center text-center no-items'>"
//     text += "<i data-v-414f00e5=''><img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI4MCIgaGVpZ2h0PSI4MCIgdmlld0JveD0iMCAwIDgwIDgwIj4KICAgIDxkZWZzPgogICAgICAgIDxsaW5lYXJHcmFkaWVudCBpZD0icHJlZml4X19hIiB4MT0iMCUiIHgyPSIxMDAlIiB5MT0iNTAlIiB5Mj0iNTAlIj4KICAgICAgICAgICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzAwQzdBRSIvPgogICAgICAgICAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiM0Q0M4RTUiLz4KICAgICAgICA8L2xpbmVhckdyYWRpZW50PgogICAgPC9kZWZzPgogICAgPGcgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4KICAgICAgICA8cGF0aCBmaWxsPSIjRkZGIiBkPSJNMCAwSDM3NVY2NjdIMHoiIHRyYW5zZm9ybT0idHJhbnNsYXRlKC00NCAtMTQ2KSIvPgogICAgICAgIDxnIHRyYW5zZm9ybT0idHJhbnNsYXRlKC00NCAtMTQ2KSB0cmFuc2xhdGUoNDQgMTQ2KSI+CiAgICAgICAgICAgIDxjaXJjbGUgY3g9IjQwIiBjeT0iNDAiIHI9IjQwIiBmaWxsPSJ1cmwoI3ByZWZpeF9fYSkiIGZpbGwtb3BhY2l0eT0iLjUiIGZpbGwtcnVsZT0ibm9uemVybyIvPgogICAgICAgICAgICA8cGF0aCBkPSJNMTYgNjRMNjQgNjQgNjQgMTYgMTYgMTZ6Ii8+CiAgICAgICAgICAgIDxwYXRoIHN0cm9rZT0iI0ZGRiIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBzdHJva2Utd2lkdGg9IjMiIGQ9Ik00NS40ODYgMjguNTE1YzQuNjg1IDQuNjg3IDQuNjg1IDEyLjI4MyAwIDE2Ljk3LTQuNjg3IDQuNjg3LTEyLjI4NiA0LjY4Ny0xNi45NyAwLTQuNjg4LTQuNjg3LTQuNjg4LTEyLjI4MyAwLTE2Ljk3IDQuNjg0LTQuNjg3IDEyLjI4My00LjY4NyAxNi45NyAwek00NiA0Nmw5IDkiLz4KICAgICAgICA8L2c+CiAgICA8L2c+Cjwvc3ZnPgo=' alt='empty'></i>"
//     text += "<h3 data-v-414f00e5=''></h3>"
//     text += "<p data-v-414f00e5='' class='help-block p2'>"
//     text += "<strong> 선택하신 조건으로 <br> 진행중인 프로그램이 없습니다. </strong><br> 다른 쓰담 프로그램을 탐색해보세요.</p>"
//     text += "</article>"
//     text += "</div>"
//     text += "<div class='infinite-status-prompt noArticle2'>"
//     text += "</div>"
//     text += "<div data-v-644ea9c9='' class='infinite-status-prompt noArticle2'>"
//     text += "<button class='btn-try-infinite'></button>"
//     text += "</div>"
//     $("#noArticle1").html(text); // #noArticleProgram 밑에 text들을 넣어줌
//     $("#programListId").html(""); // 프로그램이 없는 경우 ""로 div를 채워줌
// }

// /* 프로그램 수 Count */
// function programCountNum(programs) {
//     count = programs.length;
//     let text = "";
//     text +="<strong data-v-219a3136=''>" + count + "</strong>"
//     text +="<span data-v-219a3136=''> 개 서비스 </span>"
//
//     $("#totalCountPlace").html(text);
// }

// /* 프로그램 클릭시 programId를 이용하여 해당 program 선택 */
// function moveDetail(obj) {
//     // alert($(obj).closest(".responsive").find('#programId').attr("href"));
//     location.href = "/program/detail" + "?programId=" + $(obj).closest(".responsive").find('#programId').attr("href");
// }
//
//
//


/* ###########################   무한스크롤 연습   ############################################################################  */
//
//
/*  강사님 강의 코드 */
// $(window).scroll(function() { //스크롤 이벤트가 발생될 때 실행
//     if ($(window).scrollTop() == $(document).height() - $(window).height()) { // 맨 밑에 닿았다. 판단 - 하는 이유는 실제 스크롤이 마지막에 닿았을때를 의미
//
//     }
// });


// text += "<p id='result'>" + page++ +"</p>";
// $(document.body).append(text);
// $("#programListId").append(text);


/*  ###########################   무한스크롤 TEST   ############################################################################    */
//
// // 원하는 수만큼 프로그램을 가지고 오고
// // 그 후에 바닥에 닿을때마다 저장되어있던 정보를 보여주고싶음
//
// // 처음 스크롤이 전체 정보를 다 보여줌 (일부분만 보여주고 스크롤이 닿게 진행이 안됨)
// // 바닥에 닿는 순간 새로고침 됨
// // 상태버튼을 누르면 다시 원상복구됨,,,,,,
//
// // 내생각 - 이미 다 가져온 것,,,,...ㅣ,.,.,..,.,.,.,.,.,.,
//
// let programStatus // 프로그램 상태 (모집예정, 모집중, 모집완료, 마감)
// let keyword // 검색어
// let count // 프로그램 수
// let isEnd = false;
// let page = 1;
//
// /* 전체 program List _ Ajax */
//
// show(); // program.html 들어오면 바로 실행되는 함수
//
// /* '전체' 클릭시 실행되는 함수  */
// $(".programAllList").on('click', function () {
//     show(); // show 실행
// });
//
// /* 원하는 programs로 programList를 실행하는 함수 */
// function show() {
//     // 없애니까 모집예정이나 다른 상태 눌렀다가 전체로 이동했을때 기능 잘됨
//     // if(isEnd == true){
//     //     return;
//     // }
//     $.ajax({
//         url: "/pro/list", //전체 programAllList()를 실행한 programs 를 가지고 작동
//         type: "get",
//         success: function (programs) {
//
//             if (programs.length != 0) {
//                 programList(programs);
//                 programCountNum(programs); // 프로그램 수 Count 함수
//
//             } else {
//                 noProgramList();// 해당 programs가 없을 경우 실행되는 함수 추가 필요 (돋보기)
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             }
//         }
//     });
// }
//
// /* '프로그램 상태' 클릭시 실행되는 함수 */
// $(".programStatus").on('click', function () {
//     programStatus = $(this).text(); //programStatus class 의 누른 text로 실행
//     $.ajax({
//         url: "/pro/list/" + programStatus, // 경로 뒤에 programStatus 붙여서 해당 restController 사용
//         type: "get",
//         success: function (programs) {
//             if (programs.length != 0) {
//                 programList(programs);
//                 programCountNum(programs); // 프로그램 수 Count 함수
//
//             } else {
//                 noProgramList();// 해당 programs가 없을 경우 실행되는 함수 추가 필요 (돋보기)
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             }
//         }
//     });
// });
//
//
// /* keyword 입력시 실행되는 함수 */
// function searchList(theForm) {
//     keyword = theForm.enter.value; // keyword = enter키로 작동한 form 태그 안에 있는 값
//
//     // 빈칸 입력시
//     if (keyword == "") {
//         alert("검색어를 입력하세요");
//         $keywordBox.hide(); // 추천서비스 닫기
//         $category.removeClass("router-link-exact-active");
//         $(".programAllList").addClass("router-link-exact-active");
//
//         return false;
//     }
//     // 글자 입력시
//     $.ajax({
//         url: "/pro/list/" + keyword, // 경로 뒤에 keyword 붙여서 해당 restController 사용
//         type: "post",
//         success: function (programs) {
//             $category.removeClass("router-link-exact-active");
//             $(".programAllList").addClass("router-link-exact-active");
//             $(".keyword-box").hide(); // 엔터 입력시 keyword 박스 닫힘.
//
//             if (programs.length != 0) {
//                 programList(programs);
//                 programCountNum(programs); // 프로그램 수 Count 함수
//
//             } else {
//                 noProgramList();// 해당 programs가 없을 경우 실행되는 함수 추가 필요 (돋보기)
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             }
//
//             /* 검색 후 다른 상태 누른 경우 text 내용 없애기 */
//             $(".programStatus").on('click', function () {
//                 $searchArea.val("");
//             });
//         }
//     });
// }
//
// /* 해당 프로그램 개수에 따라 article을 반복해서 html에 넣어줄 함수 */
// function programList(programs) {
//     let text = "";
//
//     programs.forEach(program => {
//         text += "<article data-v-85c61b32='' data-v-18e85706='' class='product-list-item responsive'>";
//         text += "<input type='hidden' id='programId' href='" + program.programId + "'>"
//         /* a태그 안에 link 수정 필요 */
//         text += "<a data-v-85c61b32='' onclick='moveDetail(this)' data-testid='product-list-item' style='cursor: pointer;'>";
//         text += "<div data-v-85c61b32='' class='item-image'>";
//         text += "<article data-v-1a98b297='' data-v-85c61b32='' class='preview-image'>";
//         text += "<div data-v-1a98b297='' class='image-wrap' style='padding-top: 66.6667%;'>";
//         /* 해당 이미지 경로 */
//         text += "<img data-v-1a98b297='' src='" + program.programFileProfilePath + "'>";
//         text += "</div>";
//         text += "</article>";
//         text += "</div>";
//         text += "<div data-v-85c61b32='' class='service-name'>";
//         /* 프로그램 상태 : 모집예정 greenIcon , 모집중 blueIcon, 모집완료 redIcon, 마감 grayIcon / class 다르게 줘야함, 수정필요 */
//         if(program.programStatus == '모집예정'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon greenIcon'>" + program.programStatus + "</span>";
//         } else if(program.programStatus == '모집중'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon blueIcon'>" + program.programStatus + "</span>";
//         } else if(program.programStatus == '모집완료'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon redIcon'>" + program.programStatus + "</span>";
//         } else if(program.programStatus == '마감'){
//             text += "<span data-v-85c61b32='' class='programStatusIcon grayIcon'>" + program.programStatus + "</span>";
//         }
//         text += "</div>";
//         text += "<div data-v-85c61b32='' class='item-title'>";
//         text += "<div data-v-a3afae98='' data-v-85c61b32='' class='collapsed'>";
//         text += "<div data-v-a3afae98='' class='line-clamp'\n" +
//             "            style='line-height: 1.5; max-height: 3rem; -webkit-line-clamp: 2;'>";
//         /* 프로그램 TITLE 들어가는 자리 */
//         text += "<h3 data-v-85c61b32='' data-v-a3afae98=''> " + program.programName + "</h3>";
//         text += "</div>";
//         text += "</div>";
//         text += "</div>";
//         text += "</a>";
//         text += "</article>"
//         // text += "<p id='result'>" + page++ +"</p>";
//         // $(document.body).append(text);
//         // $("#programListId").append(text);
//     });
//
//     $("#programListId").append(text);
//     // $("#programListId").html(text);// #programListId 밑에 반복 해서 text들을 넣어줌
//     $("#noArticle1").html("");// 프로그램이 없는 경우 ""로 div를 채워줌
// }
//
// /* 해당 프로그램이 없는 경우 보여줄 돋보기 함수 */
// function noProgramList() {
//     let text = "";
//     text += "<div class='infinite-status-prompt'>"
//     text += "<article class='no-items align-self-center text-center no-items'>"
//     text += "<i data-v-414f00e5=''><img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI4MCIgaGVpZ2h0PSI4MCIgdmlld0JveD0iMCAwIDgwIDgwIj4KICAgIDxkZWZzPgogICAgICAgIDxsaW5lYXJHcmFkaWVudCBpZD0icHJlZml4X19hIiB4MT0iMCUiIHgyPSIxMDAlIiB5MT0iNTAlIiB5Mj0iNTAlIj4KICAgICAgICAgICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzAwQzdBRSIvPgogICAgICAgICAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiM0Q0M4RTUiLz4KICAgICAgICA8L2xpbmVhckdyYWRpZW50PgogICAgPC9kZWZzPgogICAgPGcgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4KICAgICAgICA8cGF0aCBmaWxsPSIjRkZGIiBkPSJNMCAwSDM3NVY2NjdIMHoiIHRyYW5zZm9ybT0idHJhbnNsYXRlKC00NCAtMTQ2KSIvPgogICAgICAgIDxnIHRyYW5zZm9ybT0idHJhbnNsYXRlKC00NCAtMTQ2KSB0cmFuc2xhdGUoNDQgMTQ2KSI+CiAgICAgICAgICAgIDxjaXJjbGUgY3g9IjQwIiBjeT0iNDAiIHI9IjQwIiBmaWxsPSJ1cmwoI3ByZWZpeF9fYSkiIGZpbGwtb3BhY2l0eT0iLjUiIGZpbGwtcnVsZT0ibm9uemVybyIvPgogICAgICAgICAgICA8cGF0aCBkPSJNMTYgNjRMNjQgNjQgNjQgMTYgMTYgMTZ6Ii8+CiAgICAgICAgICAgIDxwYXRoIHN0cm9rZT0iI0ZGRiIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBzdHJva2Utd2lkdGg9IjMiIGQ9Ik00NS40ODYgMjguNTE1YzQuNjg1IDQuNjg3IDQuNjg1IDEyLjI4MyAwIDE2Ljk3LTQuNjg3IDQuNjg3LTEyLjI4NiA0LjY4Ny0xNi45NyAwLTQuNjg4LTQuNjg3LTQuNjg4LTEyLjI4MyAwLTE2Ljk3IDQuNjg0LTQuNjg3IDEyLjI4My00LjY4NyAxNi45NyAwek00NiA0Nmw5IDkiLz4KICAgICAgICA8L2c+CiAgICA8L2c+Cjwvc3ZnPgo=' alt='empty'></i>"
//     text += "<h3 data-v-414f00e5=''></h3>"
//     text += "<p data-v-414f00e5='' class='help-block p2'>"
//     text += "<strong> 선택하신 조건으로 <br> 진행중인 프로그램이 없습니다. </strong><br> 다른 쓰담 프로그램을 탐색해보세요.</p>"
//     text += "</article>"
//     text += "</div>"
//     text += "<div class='infinite-status-prompt noArticle2'>"
//     text += "</div>"
//     text += "<div data-v-644ea9c9='' class='infinite-status-prompt noArticle2'>"
//     text += "<button class='btn-try-infinite'></button>"
//     text += "</div>"
//     $("#noArticle1").html(text); // #noArticleProgram 밑에 text들을 넣어줌
//     $("#programListId").html(""); // 프로그램이 없는 경우 ""로 div를 채워줌
// }
//
// /* 프로그램 수 Count */
// function programCountNum(programs) {
//     count = programs.length;
//     let text = "";
//     text +="<strong data-v-219a3136=''>" + count + "</strong>"
//     text +="<span data-v-219a3136=''> 개 서비스 </span>"
//
//     $("#totalCountPlace").html(text);
// }
//
// /* 프로그램 클릭시 programId를 이용하여 해당 program 선택 */
// function moveDetail(obj) {
//     // alert($(obj).closest(".responsive").find('#programId').attr("href"));
//     location.href = "/program/detail" + "?programId=" + $(obj).closest(".responsive").find('#programId').attr("href");
// }
//
// /* 스크롤 위치 확인  */
// // $(function(){
// //     $(window).scroll(function(){
// //         let $window = $(this);
// //         let scrollTop = $window.scrollTop();
// //         let windowHeight = $window.height();
// //         let documentHeight = $(document).height();
// //
// //         console.log("documentHeight:" + documentHeight + " | scrollTop:" + scrollTop + " | windowHeight: " + windowHeight );
// //
// //         // scrollbar의 thumb가 바닥 전 30px까지 도달 하면 리스트를 가져온다.
// //         // if( scrollTop + windowHeight + 30 > documentHeight ){
// //
// //         if( scrollTop == documentHeight - windowHeight ){
// //             // fetchList();
// //              alert("바닥이다");
// //             show();
// //         }
// //     })
// //     // fetchList();
// //     show();
// // })
//
// /* 은지언니 코드 */
//
// $(window).scroll(function(){
//     if($(window).scrollTop() + 1 >= $(document).height() - $(window).height()){
//         communityService.infiniteScroll(
//             page, showCommunityBoard
//         );
//         page++;
//     }
// });
//
//
//
//
//


















// let fetchList = function(){
//     if(isEnd == true){
//         return;
//     }
//
//     // 방명록 리스트를 가져올 때 시작 번호
//     // renderList 함수에서 html 코드를 보면 <li> 태그에 data-no 속성이 있는 것을 알 수 있다.
//     // ajax에서는 data- 속성의 값을 가져오기 위해 data() 함수를 제공.
//     // let startNo = $("#list-guestbook li").last().data("no") || 0;
//     $.ajax({
//         url:"/pro/list" ,
//         type: "get",
//         dataType: "json",
//         success: function(programs){
//             // 컨트롤러에서 가져온 방명록 리스트는 result.data에 담겨오도록 했다.
//             // 남은 데이터가 5개 이하일 경우 무한 스크롤 종료
//             let length = programs.length;
//             if( length < 5 ){
//                 isEnd = true;
//             }
//         }
//     });
// }
//
// /* 원하는 programs로 programList를 실행하는 함수 */
// function fetchList() {
//     if(isEnd == true){
//         return;
//     }
//     $.ajax({
//         url: "/pro/list", //전체 programAllList()를 실행한 programs 를 가지고 작동
//         type: "get",
//         success: function (programs) {
//             if (programs.length != 0) {
//                 programList(programs);
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             } if( programs.length < 5 ){
//                 isEnd = true;
//             } else {
//                 noProgramList(); // 해당 programs가 없을 경우 실행되는 함수 추가 필요 (돋보기)
//                 programCountNum(programs); // 프로그램 수 Count 함수
//             }
//         }
//     });
// }

/*
* - init : 첫 진입 시 호출 함수 입니다.
- param : ajax 통신에서 데이터를 담을 함수입니다.
- bindData : 첫 진입 시 화면에 데이터를 셋팅 하기 위한 함수입니다.
- bindEvent : 무한스크롤을 위해 생성한 함수 입니다.
- testAjax : 스크롤이 해당 화면의 맨 밑에 닿았을 경우 ajax를 호출 하여
              데이터를 가져오기 위해 사용한 ajax 호출 함수 입니다.
- setListItems : 첫 진입 및 무한 스크롤을 할 경우 데이터를 셋팅해주기위한 함수입니다.
*
* */
// $(document).ready(function(){
//     start.init();
// });
// var start = {
//     param : {
//         curPage : 1,
//         pageListSize : 15,
//     },
//
//     init : function() {
//         this.testData();
//         this.testEvent();
//     },
//     testData : function() {
//         this.setListItems("${firstData}"); // 첫 진입시 데이터 셋팅
//     },
//     testEvent : function() {
//         // 무한 스크롤
//         $(window).scroll(function() {
//             // 맨 밑으로 스크롤이 갔을 경우 if문을 탑니다.
//             if($(window).scrollTop() == $(document).height() - $(window).height()) {
//                 start.param.curPage++; // 현재 페이지에서 +1 처리.
//
//                 start.testAjax(); //ajax 호출
//             }
//         });
//     },
//     // 무한 스크롤 ajax 요청
//     testAjax : function() {
//         $.ajax({
//             type     : 'POST',
//             url      : '/test/ajax',
//             data     : JSON.stringify(start.param), // 다음 페이지 번호와 페이지 사이즈를 가지고 갑니다.
//             dataType : 'json',
//             contentType: "application/json",
//             success : successCallback,
//             error : errorCallback
//         });
//         // 성공
//         function successCallback(data) {
//             if(data.testList.totListSize == 0 ){
//                 $(".gridList").append('<div class="noList"><span>표시할 항목이 없습니다.</span></div>');
//             }
//             if(JSON.parse(data.testList).length != 0){
//                 testLoading.show(); //로딩 on(로딩바가 있을경우만 넣습니다. 없을경우 빼셔도 상관 없습니다.)
//                 start.setListItems(JSON.parse(data.testList));  //테스트 데이터 리스트 입니다.
//                 testLoading.hide(); //로딩 off(로딩바가 있을경우만 넣습니다. 없을경우 빼셔도 상관 없습니다.)
//             }
//         }
//
//         // 실패
//         function errorCallback() {
//             alert("실패");
//         }
//     },
//
//     // 테스트 데이터 setting
//     setListItems : function (list) {
//         $.each(list, function(i, testData) {
//
//             // 부모 엘리먼트에 append 할 데이터를 셋팅한다.
//             var $li = $('<li>')
//                 .append($('<div>').text(testData.name))
//                 .append($('<div>').text(testData.phoneNm))
//                 .append($('<div>')
//                     .append($('<span>').text(testData.birth));
//
//
//             // 부모 엘리먼트에 append
//             $('#test_list').append($li);
//         })
//     }
// }








// // var count = 2;
// window.onscroll = function(e) {
//     if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
//         setTimeout(function(){
//             var addContent = document.createElement("div");
//             addContent.classList.add("box")
//             // addContent.innerHTML = `<p>${++count}번째 블록</p>`
//             document.querySelector('section').appendChild(addContent);
//         }, 1000)
//     }
// }
//
// var page = 1;
//
// $(window).scroll(function() {
//     if ($(window).scrollTop() == $(document).height() - $(window).height()) {
//         console.log(++page);
//         $("#programListId").append();
//
//     }
// });