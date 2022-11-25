/* market.html */

const $marketSearch = $("section.market-keyword-search-form");
const $searchArea = $("input#__BVID__183");
const $keywordBox = $(".keyword-box");

$searchArea.on("focus", function(){
    $marketSearch.addClass("open");
    $keywordBox.css('display', 'block');
});

$searchArea.on("blur", function(){
    $marketSearch.removeClass("open");
    $keywordBox.css('display', 'none');
});