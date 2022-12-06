/* communityMain.html */

let $writeBtn = $(".write-button");
let $closeBtn = $(".swal2-cancel");
let $loginModal = $(".swal2-container")
let $categoryList = $("ul.topic.fixed > li.fixed");
let $searchBar = $("input#__BVID__883");
let $reset = $(".reset-keyword-btn");



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

// /* 카테고리 클릭 시 form 태그 submit 시키기 */
// function submitCategoryForm(category){
//     $categoryForm.attr('action', '/community/' + category);
//     $categoryForm.submit();
// }

/* 검색어 삭제 x버튼 보이기 */
$searchBar.on('keyup', function(){
    if(!$searchBar.val()){
        $reset.css('display', 'none');
        return;
    }

    $reset.css('display', 'block');
});

/* x버튼 클릭 시 검색어 삭제 */
function resetKeyword(){
    $searchBar.val('');
    $reset.css('display', 'none');
}


// /* 검색하기 */
// function searchKeyword(keyword){
//     console.log(keyword);
//     console.log($searchBar.val());
//     if(!$searchBar.val()){
//         alert("검색어를 입력하세요");
//         return;
//     }
//     $searchForm.attr('action', '/community/search/' + $searchBar.val());
//     $searchForm.submit();
// }

// /* 클릭한 카테고리에 활성화시키는 클래스 넣어주기 */
// function selectedCategory(){
//     let url = decodeURI(window.location.href).split("/");
//     let category = url[url.length - 1];
//
//     $.each($categoryList, function(i){
//         $category = $categoryList.children().eq(i).text().trim();
//
//         if(category != 'community' && category.substring(category.length, category.length - 1) != '?'){
//             $categoryList.eq(0).eq(0).removeClass("selected");
//
//             if($category == category){
//                 $categoryList.eq(i).eq(0).addClass("selected");
//             }
//         }
//     });
// }


/* ================================== Board ==================================*/

show();

/* 조회수 높은 TOP3와 최신순 게시글 조회 */
function show(){
    communityService.getTopViewList({
    }, showTopView);

    communityService.getBoardList({
    }, showCommunityBoard);
}

/* 카테고리 클릭 시 카테고리 해당 게시글 조회 */
$categoryList.on('click', function(){
    $categoryList.removeClass("selected");
    $(this).addClass("selected");

    communityService.getCategoryBoards(
        $(this).text().trim()
    , showSearchBoard);
});

/* 검색어 입력 시 해당 게시글 조회 */
function searchKeyword(){
    if(!$searchBar.val()){
        alert("검색어를 입력하세요");
        return;
    }
    communityService.getSearchBoards(
        $searchBar.val()
    , showSearchBoard);
}

/* 게시글 보기 */
function readBoard(boardId){
    communityService.getBoardDetail({
        boardId
    , function(){

        }
    });
}

/* 조회수 높은 TOP 3 */
function showTopView(topViews){
    let text = "";

    topViews.forEach(topView => {
        text += `<div data-v-e4caeaf8="" tabindex="-1" data-index="0" aria-hidden="false" class="slick-slide slick-active slick-current" style="outline: none; width: 204px;">`;
        text += `<div data-v-e4caeaf8="">`;
        text += `<a data-v-0e0856ba="" data-v-2064f17f="" href="javascript:readBoard('` + topView.boardId + `')" class="" data-testid="curation-item" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;">`;
        text += `<div data-v-0e0856ba="" class="curation-item notice">`;
        text += `<p data-v-0e0856ba="" class="topic sg-text-subhead7 sg-font-medium sg-text-gray-500">` + topView.boardCategory + `</p>`;
        text += `<h3 data-v-0e0856ba="" class="sg-text-subhead2 sg-font-bold sg-text-gray-900">` + topView.boardTitle + `</h3>`;
        text += `<div class="react-items" data-v-025ccc6a="">`;
        text += `<p class="view sg-text-description sg-font-regular sg-text-gray-300" data-v-025ccc6a="">` + topView.boardView + `</p>`;
        text += `<p class="comment sg-text-description sg-font-regular sg-text-gray-300" data-v-025ccc6a="">` + /*topView.comments.size()*/  + `</p>`;
        text += `</div>`;
        text += `</div>`;
        text += `</a>`;
        text += `</div>`;
        text += `</div>`;
    });

    $(".slick-track").append(text);
}

/* 최신순 게시글 */
function showCommunityBoard(boards){
    let text = "";

    boards.forEach(board => {
        text += `<li data-v-95718dd0="" data-v-7206b48f="" class="feed-item">`;
        text += `<a data-v-95718dd0="" href="javascript:readBoard('"` + board.boardId + `')" class="" data-testid="soomgo-life-feed-item">`;
        text += `<p data-v-95718dd0="" data-testid="soomgo-life-topic-name" class="topic-name sg-text-description sg-font-regular sg-text-gray-500">` + board.boardCategory + `</p>`;
        text += `<div data-v-95718dd0="" class="feed-content service-address-info">`;
        text += `<div data-v-95718dd0="">`;
        text += `<section data-v-95718dd0="" class="item-wrapper">`;
        text += `<h3 data-v-95718dd0="" class="sg-text-subhead5 sg-font-medium sg-text-gray-900">` + board.boardTitle + `</h3>`;
        text += `<p data-v-95718dd0="" content="맛있는 채식~~~~~~~~~~~~~~!" class="content sg-text-body2 sg-font-regular sg-text-gray-500"></p>`;
        text += `</section>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div data-v-95718dd0="" class="feed-footer">`;
        text += `<div data-v-95718dd0="" class="user-interaction">`;
        text += `<span data-v-95718dd0="" class="like sg-text-description sg-font-regular sg-text-gray-300">` + board.boardView + `</span>`;
        text += `<span data-v-95718dd0="" class="comment sg-text-description sg-font-regular sg-text-gray-300">` + /*board.comments.size() */+ `</span>`;
        text += `</div>`;
        text += `<span data-v-95718dd0="" class="sg-text-description sg-font-regular sg-text-gray-300">`
        text += board.createdDate == board.updateDate ? "작성 " + communityService.timeForToday(board.createdDate)
            : "수정 " + communityService.timeForToday(board.updateDate);
        text += `</span>`;
        text += `</div>`;
        text += `</a>`;
        text += `</li>`;
    });

    $("ul.feed-list").append(text);
}

/* 카테고리 게시글 및 검색어 게시글 */
function showSearchBoard(boards){
    let text = "";

    boards.forEach(board => {
        text += `<li data-v-95718dd0="" data-v-7206b48f="" class="feed-item">`;
        text += `<a data-v-95718dd0="" href="javascript:readBoard('"` + board.boardId + `')" class="" data-testid="soomgo-life-feed-item">`;
        text += `<p data-v-95718dd0="" data-testid="soomgo-life-topic-name" class="topic-name sg-text-description sg-font-regular sg-text-gray-500">` + board.boardCategory + `</p>`;
        text += `<div data-v-95718dd0="" class="feed-content service-address-info">`;
        text += `<div data-v-95718dd0="">`;
        text += `<section data-v-95718dd0="" class="item-wrapper">`;
        text += `<h3 data-v-95718dd0="" class="sg-text-subhead5 sg-font-medium sg-text-gray-900">` + board.boardTitle + `</h3>`;
        text += `<p data-v-95718dd0="" content="맛있는 채식~~~~~~~~~~~~~~!" class="content sg-text-body2 sg-font-regular sg-text-gray-500"></p>`;
        text += `</section>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div data-v-95718dd0="" class="feed-footer">`;
        text += `<div data-v-95718dd0="" class="user-interaction">`;
        text += `<span data-v-95718dd0="" class="like sg-text-description sg-font-regular sg-text-gray-300">` + board.boardView + `</span>`;
        text += `<span data-v-95718dd0="" class="comment sg-text-description sg-font-regular sg-text-gray-300">` + /*board.comments.size() */+ `</span>`;
        text += `</div>`;
        text += `<span data-v-95718dd0="" class="sg-text-description sg-font-regular sg-text-gray-300">`
        text += board.createdDate == board.updateDate ? "작성 " + communityService.timeForToday(board.createdDate)
            : "수정 " + communityService.timeForToday(board.updateDate);
        text += `</span>`;
        text += `</div>`;
        text += `</a>`;
        text += `</li>`;
    });

    $("ul.feed-list").html(text);
}






