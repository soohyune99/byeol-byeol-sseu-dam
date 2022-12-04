/* communityMain.html */

let $writeBtn = $(".write-button");
let $closeBtn = $(".swal2-cancel");
let $loginModal = $(".swal2-container")
let $categoryForm = $("form#categoryForm");
let $categoryList = $("ul.topic.fixed > li.fixed");
let $searchBar = $("input#__BVID__883");
let $searchForm = $("form#searchForm");


selectedCategory();


$writeBtn.on('click', function(){
    openLoginModal();
});

$closeBtn.on('click', function(){
    closeLoginModal();
});


/* 로그인하지 않고 글쓰기 작성 버튼 클릭 시 로그인 모달 띄우기 */
function openLoginModal(){
    $loginModal.css('display', 'block');
}

/* 로그인 모달 닫기 버튼 */
function closeLoginModal(){
    $loginModal.css('display', 'none');
}

/* 카테고리 클릭 시 form 태그 submit 시키기 */
function submitCategoryForm(category){
    $categoryForm.attr('action', '/community/' + category);
    $categoryForm.submit();
}

/* 검색하기 */
function searchKeyword(keyword){
    $searchForm.attr('action', '/community/search/' + keyword);
    $searchForm.submit();
}

/* 클릭한 카테고리에 활성화시키는 클래스 넣어주기 */
function selectedCategory(){
    let url = decodeURI(window.location.href).split("/");
    let category = url[url.length - 1];

    $.each($categoryList, function(i){
        $category = $categoryList.children().eq(i).text().trim();

        if(category != 'community'){
            $categoryList.eq(0).eq(0).removeClass("selected");

            if($category == category){
                $categoryList.eq(i).eq(0).addClass("selected");
            }
        }
    });
}
