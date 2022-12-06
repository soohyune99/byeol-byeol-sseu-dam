/* market.html */

const $marketSearch = $("section.market-keyword-search-form");
const $searchArea = $("input#__BVID__183");
const $keywordBox = $(".keyword-box");
// let $categoryList = $("ul.tab > li.fixed > a.btn.btn-category");
let $categoryList = $("ul.tab > li > a.fixed");

// let $category = $("ul.tab li a.btn.btn-category");

/* 검색바 포커스했을 때 드롭박스 열기 */
$searchArea.on("focus", function(){
    $marketSearch.addClass("open");
    $keywordBox.css('display', 'block');
});

/* 검색바 블러했을 때 드롭박스 닫히기 */
$searchArea.on("blur", function(){
    $marketSearch.removeClass("open");
    $keywordBox.css('display', 'none');
});

/* 검색어 입력 시 x버튼 생성 */
$searchArea.on('keyup', function(){
    if($searchArea.val() != ""){
        $(".input-group-append").css('display', 'block');
    }else {
        $(".input-group-append").css('display', 'none');
    }
});

/* x버튼 클릭 시 검색어 삭제 */
$(".btn-keyword-del").on('click', function(){
    $searchArea.val("");
    $(".input-group-append").css('display', 'none');
});

/* 카테고리 클릭 */
// $category.on("click", function(){
//     $category.removeClass("router-link-exact-active");
//     console.log("들어옴");
//     $(this).addClass("router-link-exact-active");
// });

$("group-0").on("click", function (){
    // console.log("들어옴");
    $("group-0").addClass("router-link-exact-active");
    $("group-1").removeClass("router-link-exact-active");
    $("group-2").removeClass("router-link-exact-active");
    $("group-3").removeClass("router-link-exact-active");
    $("group-4").removeClass("router-link-exact-active");
    $("group-5").removeClass("router-link-exact-active");
    $("group-6").removeClass("router-link-exact-active");
    $("group-7").removeClass("router-link-exact-active");
})

// function selectedCategory(){
//     let url = window.location.href.split("/");
//     let category = url[url.length - 1];
//
//     $.each($categoryList, function(i){
//         $category = $categoryList.children().eq(i).text().trim();
//
//         if(category != 'all'){
//             $categoryList.eq(0).eq(0).removeClass("selected");
//
//             if($category == category){
//                 $categoryList.eq(i).eq(0).addClass("selected");
//             }
//         }
//     });
// }

