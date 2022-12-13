/* market.html */

const $marketSearch = $("section.market-keyword-search-form");
const $searchArea = $("input#__BVID__183");
const $keywordBox = $(".keyword-box");
let $categoryList = $("a.btn-category");
let $reset = $(".reset-keyword-btn");
let check = false;
globalThis.category = "";

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


/* x버튼 클릭 시 검색어 삭제 */
function resetKeyword(){
    $searchBar.val('');
    $reset.css('display', 'none');
}


/*/!* x버튼 클릭 시 검색어 삭제 *!/
$(".btn-keyword-del").on('click', function () {
    $searchArea.val("");
    $(".input-group-append").css('display', 'none');
});*/

/* 클릭 > 블러 먼저 진행 되도록 하고 안에 클릭한 text 넣기 */

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

/*/!* 검색바 포커스했을 때 드롭박스 열기 *!/
$searchArea.on("focus", function(){
    $marketSearch.addClass("open");
    $keywordBox.css('display', 'block');
});

/!* 검색바 블러했을 때 드롭박스 닫히기 *!/
$searchArea.on("blur", function(){
    $marketSearch.removeClass("open");
    $keywordBox.css('display', 'none');
});


/*$(".keyword-box").hide(); // 엔터 입력시 keyword 박스 닫힘.

/!* 외부 영역 클릭시 keyword-box 닫기 *!/
$(document).mouseup(function (e){
    if($keywordBox.has(e.target).length === 0){
        $keywordBox.hide();
    }
});

/!* ESC 키 누를시 keyword-box 닫기 *!/
$(document).keydown(function(e){
    //keyCode 구 브라우저, which 현재 브라우저
    var code = e.keyCode || e.which;

    if (code == 27) { // 27은 ESC 키번호
        $keywordBox.hide(); //
        $searchArea.val(""); // input에 입력된 글자 빈칸으로 초기화
    }
});*/


/* ================================== Market ==================================*/

let $searchBar = $("input#__BVID__183"); // 검색창
let keyword = $searchArea.val() || ""; //검색어
let category;   // 카테고리
let $url;
/* 해당 컨트롤러로 보낼 url */
function url() {
    if (keyword == "" && category == undefined) {
        // 검색 X , 상태 선택 X
        $url = "/product/dynamic";
    } else if (keyword == "") {
        // 검색 X , 상태 선택 O
        $url = "/product/dynamic" + "/" + "status" + "/" + category;
    } else if (category == undefined) {
        // 검색 O , 상태 선택 X
        $url = "/product/dynamic" + "/" + keyword;
    } else {
        // 검색 O , 상태 선택 O
        $url = "/product/dynamic" + "/" + keyword + "/" + category;
    }
}

url();
show();

/* 최신순 전체 조회 */
function show(){
    marketService.getProductList({
    }, showMarketProduct);
}

/* 카테고리 클릭 시 해당 마켓 부분 조회 */
$categoryList.on('click', function(){
    globalThis.category = $(this).text().trim();
    $categoryList.removeClass("router-link-exact-active");
    $(this).addClass("router-link-exact-active");


    marketService.getCategoryMarkets(
        globalThis.category, showSearchProduct);
});

/* 검색어 입력 시 해당 상품 조회 */
function searchKeyword(){
    if(!$searchArea.val()){
        alert("검색어를 입력하세요");
        return;
    }

    $categoryList.removeClass("selected");
    marketService.getSearchProducts(
        $searchArea.val()
        ,showSearchProduct);
}



/* 마켓 전체 조회 */
function showMarketProduct(products) {

    let text = "";

    // if(products.length  0) {
        text += `<div data-v-18e85706="" data-v-72c8052a="">`;
        text += `<section data-v-219a3136="" data-v-18e85706="">`;
        text += `<div data-v-219a3136="" class="product-total-count">`;
        text += `<strong data-v-219a3136=""></strong>`;
        text += `<span data-v-219a3136="">개 서비스</span>`;
        text += `</div>`;
        text += `</section>`;
        text += `<div data-v-18e85706="" class="product-list responsive">`;
    // }

    products.forEach(product => {
        text += `<article data-v-85c61b32="" data-v-18e85706="" class="product-list-item responsive">`;
        text += `<a data-v-85c61b32="" href="/market/` + product.productId + `" class="productClick" data-testid="product-list-item">`;
        text += `<div data-v-85c61b32="" class="item-image">`;
        text += `<article data-v-1a98b297="" data-v-85c61b32="" class="preview-image">`;
        text += `<div data-v-1a98b297="" class="image-wrap" style="padding-top: 66.6667%;">`;
        text += `<img data-v-1a98b297="" src="https://cdn.imweb.me/thumbnail/20210916/9a108f95dd897.jpg">`;
        text += `</div>`;
        text += `</article>`;
        text += `</div>`;
        text += `<div data-v-85c61b32="" class="service-name">`;
        text += `<span data-v-85c61b32="">` + product.productCategory + `</span>`;
        text += `</div>`;
        text += `<div data-v-85c61b32="" class="item-title">`;
        text += `<div data-v-a3afae98="" data-v-85c61b32="" class="collapsed">`;
        text += `<div  data-v-a3afae98="" class="line-clamp" style="line-height: 1.5; max-height: 3rem; -webkit-line-clamp: 2;">`;
        text += `<h3 data-v-85c61b32="" data-v-a3afae98="">` + product.productName + `</h3>`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div data-v-85c61b32="" class="item-price">`;
        text += `<strong data-v-85c61b32="">` + product.productPrice +`</strong>`;
        text += `</div>`;
        text += `</a>`;
        text += `</article>`;
    });
    text += `</div>`;
    text += `</div>`;
    $("div.market-list").append(text);

}

/* 마켓 상품이 없을 경우 */
function noProductList() {
    let text = "";
    text += "<div class='infinite-status-prompt'>"
    text += "<article class='no-items align-self-center text-center no-items'>"
    text += "<i data-v-414f00e5=''><img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI4MCIgaGVpZ2h0PSI4MCIgdmlld0JveD0iMCAwIDgwIDgwIj4KICAgIDxkZWZzPgogICAgICAgIDxsaW5lYXJHcmFkaWVudCBpZD0icHJlZml4X19hIiB4MT0iMCUiIHgyPSIxMDAlIiB5MT0iNTAlIiB5Mj0iNTAlIj4KICAgICAgICAgICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzAwQzdBRSIvPgogICAgICAgICAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiM0Q0M4RTUiLz4KICAgICAgICA8L2xpbmVhckdyYWRpZW50PgogICAgPC9kZWZzPgogICAgPGcgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4KICAgICAgICA8cGF0aCBmaWxsPSIjRkZGIiBkPSJNMCAwSDM3NVY2NjdIMHoiIHRyYW5zZm9ybT0idHJhbnNsYXRlKC00NCAtMTQ2KSIvPgogICAgICAgIDxnIHRyYW5zZm9ybT0idHJhbnNsYXRlKC00NCAtMTQ2KSB0cmFuc2xhdGUoNDQgMTQ2KSI+CiAgICAgICAgICAgIDxjaXJjbGUgY3g9IjQwIiBjeT0iNDAiIHI9IjQwIiBmaWxsPSJ1cmwoI3ByZWZpeF9fYSkiIGZpbGwtb3BhY2l0eT0iLjUiIGZpbGwtcnVsZT0ibm9uemVybyIvPgogICAgICAgICAgICA8cGF0aCBkPSJNMTYgNjRMNjQgNjQgNjQgMTYgMTYgMTZ6Ii8+CiAgICAgICAgICAgIDxwYXRoIHN0cm9rZT0iI0ZGRiIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBzdHJva2Utd2lkdGg9IjMiIGQ9Ik00NS40ODYgMjguNTE1YzQuNjg1IDQuNjg3IDQuNjg1IDEyLjI4MyAwIDE2Ljk3LTQuNjg3IDQuNjg3LTEyLjI4NiA0LjY4Ny0xNi45NyAwLTQuNjg4LTQuNjg3LTQuNjg4LTEyLjI4MyAwLTE2Ljk3IDQuNjg0LTQuNjg3IDEyLjI4My00LjY4NyAxNi45NyAwek00NiA0Nmw5IDkiLz4KICAgICAgICA8L2c+CiAgICA8L2c+Cjwvc3ZnPgo=' alt='empty'></i>"
    text += "<h3 data-v-414f00e5=''></h3>"
    text += "<p data-v-414f00e5='' class='help-block p2'>"
    text += "<strong> 선택하신 조건으로 <br> 판매중인 상품이 없습니다. </strong><br> 다른 카테고리를 탐색해보세요.</p>"
    text += "</article>"
    text += "</div>"
    text += "<div class='infinite-status-prompt noArticle2'>"
    text += "</div>"
    text += "<div data-v-644ea9c9='' class='infinite-status-prompt noArticle2'>"
    text += "<button class='btn-try-infinite'></button>"
    text += "</div>"
    $("#noArticle1").html(text); // #noArticleProgram 밑에 text들을 넣어줌
    $("#programListId").html(""); // 프로그램이 없는 경우 ""로 div를 채워줌
}

/* 카테고리 마켓 및 검색어 마켓 */
function showSearchProduct(products) {

    let text = "";
    text += `<div data-v-18e85706="" data-v-72c8052a="">`;
    text += `<section data-v-219a3136="" data-v-18e85706="">`;
    text += `<div data-v-219a3136="" class="product-total-count">`;
    text += `<strong data-v-219a3136=""></strong>`;
    text += `<span data-v-219a3136="">개 서비스</span>`;
    text += `</div>`;
    text += `</section>`;
    text += `<div data-v-18e85706="" class="product-list responsive">`;

    products.forEach(product => {
        text += `<article data-v-85c61b32="" data-v-18e85706="" class="product-list-item responsive">`;
        text += `<a data-v-85c61b32="" href="/market/` + product.productId + `" class="productClick" data-testid="product-list-item">`;
        text += `<div data-v-85c61b32="" class="item-image">`;
        text += `<article data-v-1a98b297="" data-v-85c61b32="" class="preview-image">`;
        text += `<div data-v-1a98b297="" class="image-wrap" style="padding-top: 66.6667%;">`;
        text += `<img data-v-1a98b297="" src="https://cdn.imweb.me/thumbnail/20210916/9a108f95dd897.jpg">`;
        text += `</div>`;
        text += `</article>`;
        text += `</div>`;
        text += `<div data-v-85c61b32="" class="service-name">`;
        text += `<span data-v-85c61b32="">` + product.productCategory + `</span>`;
        text += `</div>`;
        text += `<div data-v-85c61b32="" class="item-title">`;
        text += `<div data-v-a3afae98="" data-v-85c61b32="" class="collapsed">`;
        text += `<div  data-v-a3afae98="" class="line-clamp" style="line-height: 1.5; max-height: 3rem; -webkit-line-clamp: 2;">`;
        text += `<h3 data-v-85c61b32="" data-v-a3afae98="">` + product.productName + `</h3>`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div data-v-85c61b32="" class="item-price">`;
        text += `<strong data-v-85c61b32="">` + product.productPrice +`</strong>`;
        text += `</div>`;
        text += `</a>`;
        text += `</article>`;
    });
    text += `</div>`;
    text += `</div>`;
    $keywordBox.hide();
    $("div.market-list").html(text);
}



/* ================================== Infinite Scroll ==================================*/

    let page = 1;
    $(window).scroll(function(){
        let formData = new FormData();
        let keyword = $searchArea.val() || "";

        formData.append('page', page);
        formData.append('keyword', keyword);
        formData.append('category', globalThis.category);

        if($(window).scrollTop() * 1.001 >= $(document).height() - $(window).height()){
            console.log("무한스크롤 " + page);
            marketService.infiniteScroll(
                formData, showMarketProduct
            );
            page++;
        }
    });




