/* mypageCommunity.html */

const memberId = 1;
globalThis.page = 0;

changeTab();

function changeTab(tab){
    globalThis.page = 0;
    $(".community-activity-item").remove();
    $(".mycommunity-more-btn").remove();
    $(".mycomment-more-btn").remove();

    if(tab == 'comment'){
        showMyComment();
        $(".community-tab").removeClass("selected");
        $(".comment-tab").addClass("selected");
    }else {
        showMyCommunity();
        $(".comment-tab").removeClass("selected");
        $(".community-tab").addClass("selected");
    }
}



/* ================================== Board ==================================*/


/* 내가 쓴 글 조회 */
function showMyCommunity(){
    mypageService.getMyCommunityList(
        memberId, globalThis.page ,showMyCommunityList
    );
    globalThis.page ++;
}

function showMyCommunityList(myboards){
    let text = "";
    let appendedCommunity = 0;

    myboards.forEach(myboard => {
        text += `<li data-v-6de74f26="" data-v-be5cd312="" class="community-activity-item">`;
        text += `<a href="/community/` + myboard.boardId + `">`;
        text += `<span data-v-6de74f26="" class="community-activity-badge sg-text-subhead7 sg-font-medium sg-text-gray-500">` + myboard.boardCategory + `</span>`;
        text += `<div data-v-a3afae98="" data-v-6de74f26="" class="collapsed">`;
        text += `<div data-v-a3afae98="" class="line-clamp" style="line-height: 1.5; max-height: 3rem; -webkit-line-clamp: 2;">`;
        text += `<p data-v-6de74f26="" class="community-activity-title sg-text-subhead5 sg-font-medium" data-v-a3afae98="">` + myboard.boardTitle + `</p>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div data-v-a3afae98="" data-v-6de74f26="" class="collapsed">`;
        text += `<div data-v-a3afae98="" class="line-clamp" style="line-height: 1.5; max-height: 3rem; -webkit-line-clamp: 2;">`;
        text += `<p data-v-6de74f26="" class="community-activity-content sg-text-body2 sg-font-regular sg-text-gray-500" data-v-a3afae98="">` + myboard.boardContent + `</p>`;
        text += `</div>`;
        text += `</div>`;
        text += `<span data-v-6de74f26="" class="sg-text-body2 sg-font-regular sg-text-gray-300">` + myboard.createdDate + `</span>`;
        text += `</a>`;
        text += `</li>`;
    });

    if(myboards.length >= 5){
        text += `<button onclick="javascript:clickMoreMyboards();" class="mycommunity-more-btn" style="width: 100%; height: 70px; background: transparent; border: none; font-size: 12px; font-weight:bold;">더보기</button>`;
    }

    $(".community-activity-list").append(text);

    appendedCommunity = $(".community-activity-item");

    if(myboards.size == 0 && appendedCommunity.length == 0){
        $(".community-content-section.white").css('display', 'block');
    }
}

/* 내가 쓴 글 더보기 */
function clickMoreMyboards(){
    if(globalThis.page != 0) {
        $(".mycommunity-more-btn").remove();
    }

    mypageService.getMyCommunityList(
        memberId, globalThis.page ,showMyCommunityList
    );
    globalThis.page++;
}


/* ================================== Comment ==================================*/


/* 내가 쓴 댓글 조회 */
function showMyComment(){
    mypageService.getMyCommunityList(
        memberId, globalThis.page, showMyCommentList
    );
    globalThis.page ++;
}

function showMyCommentList(mycomments){
    let text = "";
    let appendedComment = 0;

    mycomments.forEach(mycomment => {
        text += `<li data-v-6de74f26="" data-v-be5cd312="" class="community-activity-item">`;
        text += `<a href="/community/` + mycomment.boardId + `">`
        text += `<div data-v-a3afae98="" data-v-6de74f26="" class="collapsed">`;
        text += `<div data-v-a3afae98="" class="line-clamp" style="line-height: 1.5; max-height: 3rem; -webkit-line-clamp: 2;">`;
        text += `<p data-v-6de74f26="" class="community-activity-content sg-text-subhead5 sg-font-medium" data-v-a3afae98="">` + mycomment.boardTitle + `</p>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div data-v-a3afae98="" data-v-6de74f26="" class="collapsed">`;
        text += `<div data-v-a3afae98="" class="line-clamp" style="line-height: 1.5; max-height: 1.5rem; -webkit-line-clamp: 1;">`;
        text += `<p data-v-6de74f26="" class="community-activity-title sg-text-body2 sg-font-regular sg-text-gray-500" data-v-a3afae98="">` + mycomment.commentContent + `</p>`;
        text += `</div>`;
        text += `</div>`;
        text += `<span data-v-6de74f26="" class="sg-text-body2 sg-font-regular sg-text-gray-300">` + mycomment.createdDate + `</span>`;
        text += `</a>`;
        text += `</li>`;
    });

    if(mycomments.length >= 5){
        text += `<button onclick="javascript:clickMoreMycomments();" class="mycomment-more-btn" style="width: 100%; height: 70px; background: transparent; border: none; font-size: 12px; font-weight:bold;">더보기</button>`;
    }

    $(".community-activity-list").append(text);

    appendedComment = $(".community-activity-item");

    if(mycomments.size == 0 && appendedComment.length == 0){
        $(".").css('display', 'block');
    }
}

/* 내가 쓴 댓글 더보기 */
function clickMoreMycomments(){
    if(globalThis.page != 0) {
        $(".mycomment-more-btn").remove();
    }

    mypageService.getMyCommentList(
        memberId, globalThis.page ,showMyCommentList
    );
    globalThis.page++;
}