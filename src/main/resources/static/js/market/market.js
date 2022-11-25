/* market.html */

const $marketSearch = $("section.market-keyword-search-form");
const $searchArea = $("input#__BVID__183");
const $keywordBox = $(".keyword-box");

let $category = $("ul.tab li a.btn.btn-link");

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


/* 카테고리 클릭 */
$category.on("click", function(){
    $category.removeClass("router-link-exact-active");
    $(this).addClass("router-link-exact-active");
});