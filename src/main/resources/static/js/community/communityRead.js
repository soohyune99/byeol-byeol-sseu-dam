/* CommunityRead.html */

stickyNav();

let $dropdownMenu = $(".dropdown-menu.dropdown-menu-right.sticky-nav-menu");
let $boardMenu = $(".dropdown-menu.dropdown-menu-right.board-menu");
let $boardDeleteBtn = $(".boardDelete");
let $boardUpdateBtn = $(".boardUpdate");
let $commentMenu = $(".dropdown-menu.dropdown-menu-right.comment-menu");
let $commentContent = $("textarea[name='comment-input']");
let $commentFileForm = $("input[name='uploadFile']");
let $commentSubmitBtn = $(".write-comment-submit");
let updateText = "";
let $loginModal = $(".swal2-container.swal2-center");
let navCheck = -1;
let commentCheck = -1;

const $file = $("#file-input");
let $deleteFileBtn = $("img.delete-badge");


/* 스크롤이 최상단이 아니면 제목 nav 보이기 */
$(window).scroll(function(event){
    stickyNav();
});


/* 제목 nav의 도시락 버튼 클릭 시 드롭메뉴 보이기 */
$("button#__BVID__1564__BV_toggle_").on("click", function(){
    openDropdown($dropdownMenu);
});


/* 게시글 도시락 버튼 시 수정, 삭제 메뉴 보이기 */
$(".board-dropdownmenu-menuBtn").on("click", function(){
    openBoardMenu($boardMenu);
});

/* 댓글 입력 시 로그인 안 되어 있을 경우 로그인 모달창 띄우기 */
$("#__BVID__1499").on("focus", function(){
    if(memberId == null || memberId == ''){
        openLoginModal();
    }
});


/* 로그인 모달 취소 버튼 클릭 시 돌아가기 */
$(".swal2-cancel.btn").on("click", function(){
    closeLoginModal();
});

/* 제목Nav 스크롤 최하단일 때 사라지게 하기 */
function stickyNav(){
    let $scrollTop = $(this).scrollTop();

    if($scrollTop == 0){
        $("#app-sticky-nav .fixed").removeClass("show");
    }else {
        $("#app-sticky-nav .fixed").addClass("show");
    }
}

function openDropdown(menu){
    menu.toggle();
}

function openBoardMenu(menu){
    menu.toggle();
}

function openCommentMenu(commentId){
    $(".comment-menu." + commentId).toggle();
}

/* 로그인 모달 띄우기 */
function openLoginModal(){
    $loginModal.css('display', 'block');
    $("body").css('overflow-y', 'hidden');
}

/* 로그인 모달 닫기 */
function closeLoginModal(){
    $loginModal.css('display', 'none');
    $("body").css('overflow-y', 'auto');
}

/* 첨부파일 x 클릭 시 div 삭제 */
$(".comment-body").on('click', '.delete-badge', function(){
    $file.removeAttr('disabled');
    $(".attach-image-icon").css('cursor', 'pointer');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iIzJEMkQyRCIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');
    $(".image-preview").remove();
});

/* 댓글 내용 입력 시 등록 버튼 보이기 */
$commentContent.on('keyup', function(){
  if(!$commentContent.val()){
      $commentSubmitBtn.removeClass("active");
      return;
  }
    $commentSubmitBtn.addClass("active");
});



/* ================================== Board ==================================*/

let url = decodeURI(window.location.href).split("/");
let boardId = url[url.length - 1];

readBoard(boardId);

/* 게시글 보기 */
function readBoard(boardId){
    communityService.getBoardDetail(
        boardId, showBoardDetail
    );
}

/* 게시글 삭제 */
$boardDeleteBtn.on('click', function(){
    communityService.deleteBoard(boardId, deleteBoard);
});

function showBoardDetail(board){
    let text = "";

    $(".categoryDetail").html(board.boardCategory);
    $(".post-service-name").html(board.boardCategory);
    $(".board-title").html(board.boardTitle);
    $(".board-writer-profile").attr('src', board.memberProfileName);
    $(".board-writer-name").html(board.memberName);
    $(".board-createdDate").html(
        board.createdDate == board.updatedDate ? "작성 " + communityService.timeForToday(board.createdDate)
        : "수정 " + communityService.timeForToday(board.updatedDate)
    );
    $(".board-boardView").html(' · 조회 ' + board.boardView);
    if(board.memberId != memberId){
        $("#__BVID__1550__BV_toggle_").hide();
    }

    $boardUpdateBtn.attr('href', '/community/update?id=' + board.boardId);
    $(".board-content").html('<br>' + board.boardContent);

    board.files.forEach(file => {
        text += `<li data-v-7614b52f="" data-type="lightGallery board-file-img-wrap" data-exthumbimage="https://static.cdn.soomgo.com/upload/media/b057a7b9-b84d-49e1-a376-956cc5f087bc.jpg?h=160&amp;w=160" data-sub-html-src="https://static.cdn.soomgo.com/upload/media/b057a7b9-b84d-49e1-a376-956cc5f087bc.jpg?h=160&amp;w=160" data-src="https://static.cdn.soomgo.com/upload/media/b057a7b9-b84d-49e1-a376-956cc5f087bc.jpg" data-sub-html=" " class="grid-image-list">`;
        text += `<img data-v-7614b52f="" alt="4298D74C-9291-4543-8137-3820671DEA3C.jpg" class="image"  onclick="javascript:openPhotoModal(this.src);" data-src="https://static.cdn.soomgo.com/upload/media/b057a7b9-b84d-49e1-a376-956cc5f087bc.jpg" src="` + file.fileBoardName + `" lazy="loaded">`;
        text += `</li>`;
    })
    $(".grid-image-wrapper").html(text);

    $(".post-react-item.comments > span").html('댓글 ' + board.comments.length);

}


function deleteBoard(){
    location.href = "/community";
}

/* 사진 클릭 시 상세보기 모달 띄우기 */
function openPhotoModal(img){
    $(".lg-show-after-load").css('display', 'block');
    $(".lg-show-after-load").css('z-index', 1060);
    $(".lg-image").attr('src', img);
}

/* 사진 모달 닫기 */
$(".lg-close.lg-icon").on('click', function(){
    $(".lg-show-after-load").css('display', 'none');
    $(".lg-show-after-load").css('z-index', 0);
});



/* ================================== Comment ==================================*/

globalThis.page = 1;

showComment();

/* 댓글 목록 보기 */
function showComment(){
    $commentContent.val('');
    $commentSubmitBtn.removeClass("active");

    commentService.getCommentList(
        boardId, showCommentList
    );
}

$commentSubmitBtn.on("click", function(){
    saveComment();
});

/* 댓글 더 보기 눌렀을 시 */
function clickMoreCommentBtn(){
    if(globalThis.page != 0) {
        $(".comment-more-btn").remove();
    }

    commentService.getMoreComment(
        boardId, globalThis.page, showCommentMore
    );
    globalThis.page++;
}

/* 댓글 첨부파일 등록 시 uploade 저장 */
$commentFileForm.on('change', function(){
    let file = $commentFileForm[0].files[0];

    communityService.uploadBoardFile(
        file, afterUploadeCommentFile
    );
});

/* 댓글 등록 */
// $(document).ready(function(){
    function saveComment() {
        console.log($commentContent.val())
        if(!$commentContent.val() || $commentContent.val() == '') {
            alert("내용을 입력하세요.");
            $commentSubmitBtn.removeClass("active");
            return;
        }
        commentService.save({
            boardId: boardId,
            memberId: memberId,
            commentContent: $commentContent.val(),
            commentFileName: $("input[name='commentFileName']").val()
        }, function () {
            showComment();
        });
    }
// });

/* 댓글 수정 */
function updateComment(commentId){
    let $comment = $(".commentContent." + commentId);
    let text = "";
    updateText = "";
    updateText = $comment.text();

    $(".comment-menu." + commentId).css('display', 'none');

    text += `<input type="text" class="commentContent ` + commentId;
    text += `">`;
    text += `<button class="comment-updateCancel-btn" onclick="javascript:updateCancel(` + commentId + `)">취소</button>`;
    text += `<button class="comment-updateOk-btn" onclick="javascript:updateOKComment(` + commentId + `)">완료</button>`;

    $comment.replaceWith(text);
    $("input.commentContent." + commentId).val(updateText);
}

/* 댓글 수정 취소 */
function updateCancel(commentId){
    let text = "";

    text += `<span data-v-6f126738="" class="commentContent `;
    text += commentId;
    text += `">`;
    text += updateText;
    text += `</span>`;

    $("input.commentContent." + commentId).closest("p.text").html(text);
}

/* 댓글 수정 완료 */
function updateOKComment(commentId){
    commentService.updateOKComment({
        commentId: commentId,
        commentContent: $("input.commentContent." + commentId).val(),
        boardId: boardId,
        memberId: memberId,
        commentFileName: $("input[name='commentFileName']").val()
    }, showComment);
}

/* 댓글 삭제 */
function deleteComment(commentId){
    commentService.deleteComment(
        commentId, showComment
    );
}

/* 댓글 개수 */
function countComment(){
    commentService.countCommentofBoard(
        boardId, showCountofComment
    );
    console.log("하잇" + boardId);
}

function showCountofComment(count){
    console.log("콜백")
    console.log(count)
    $(".comment-count").html("댓글 " + count);
}

/* 본인 작성 댓글만 수정삭제 메뉴 보이게 하기 */
function hideCommentMenu(){
    console.log(memberId);
    console.log($(".member805"));
    $(".board-dropdown-toggle.btn-secondary.btn-comment").css('display', 'none');
    $(".board-dropdown-toggle.member" + memberId).css('display', 'block');
}

function showCommentList(comments){
    let text = "";

    comments.forEach(comment => {
        text += `<li data-v-eb37dd0c="" data-v-e30e236e="" class="post-comments-list-item">`;
        text += `<div data-v-6f126738="" data-v-eb37dd0c="" class="post-comment-wrapper">`;
        text += `<div data-v-6f126738="" class="profile-image provider">`;
        text += `<img data-v-6f126738="" alt="" class="image" data-src="https://static.cdn.soomgo.com/upload/profile/dce93681-1662-4df6-b116-bed3bc418d25.jpg?h=110&amp;w=110" src="` + comment.memberProfileName + `" lazy="loaded">`;
        text += `</div>`;
        text += `<div data-v-6f126738="" class="comment-information">`;
        text += `<span data-v-6f126738="" class="user-name sg-text-subhead4 sg-font-bold sg-text-gray-900">` + comment.memberName + `</span>`;
        text += `<div data-v-6f126738="" class="content">`;
        text += `<p data-v-6f126738="" class="text sg-text-body2 sg-font-regular">`;
        text += `<span data-v-6f126738="" class="commentContent ` + comment.commentId + `">` + comment.commentContent + `</span>`;
        text += `</p></div>`;
        text += comment.commentFileName != null ? `<ul data-v-25f837a2="" class="attached-image-wrapper">` : '';
        text += comment.commentFileName != null ? `<li data-v-25f837a2="" data-type="lightGallery" data-exthumbimage="https://static.cdn.soomgo.com/upload/media/0e9b2380-ea7a-4ffa-a75f-ff6d55bcfaa4.jpg?h=110&amp;w=110" data-sub-html-src="https://static.cdn.soomgo.com/upload/media/0e9b2380-ea7a-4ffa-a75f-ff6d55bcfaa4.jpg?h=110&amp;w=110" data-src="https://static.cdn.soomgo.com/upload/media/0e9b2380-ea7a-4ffa-a75f-ff6d55bcfaa4.jpg" data-sub-html=" " class="attached-image-item">` : '';
        text += comment.commentFileName != null ? `<img data-v-25f837a2="" class="image" onclick="javascript:openPhotoModal(this.src);" src="` + comment.commentFileName + `" lazy="loaded">` : '';
        text += comment.commentFileName != null ? `</li>` : '';
        text += comment.commentFileName != null ? `</ul>` : '';
        text += `<div data-v-6f126738="" class="comment-action-group sg-text-description sg-font-regular">`;
        text += `<div data-v-6f126738="" class="comment-react">`;
        text += `<span data-v-6f126738="" class="text">`;
        text += comment.createdDate == comment.updatedDate ? "작성 " + communityService.timeForToday(comment.createdDate)
            : "수정 " + communityService.timeForToday(comment.updatedDate);
        text += `</span>`;
        text += `</div>`;
        text += `<div data-v-6f126738="" class="more-action">`;
        text += `<div data-v-6f126738="" class="dropdown b-dropdown show btn-group" id="__BVID__1555">`;
        text += `<button aria-haspopup="true" aria-expanded="true" type="button" class="btn board-dropdown-toggle btn-secondary btn-comment member` + comment.memberId + ' ' + comment.commentId +`" onclick="javascript:openCommentMenu(` + comment.commentId + `)"></button>`;
        text += `<ul role="menu" tabindex="-1" class="dropdown-menu dropdown-menu-right comment-menu ` + comment.commentId + `" aria-labelledby="__BVID__1555__BV_toggle_" style="position: absolute; transform: translate3d(-116px, 22px, 0px); top: 0px; left: 0px; will-change: transform;" x-placement="bottom-end">`;
        text += `<li data-v-77f4e41c="" role="presentation">`;
        text += `<a role="menuitem" href="javascript:updateComment(` + comment.commentId + `)" target="_self" class="dropdown-item commentUpdate">수정</a>`;
        text += `</li>`;
        text += `<li data-v-77f4e41c="" role="presentation">`;
        text += `<a role="menuitem" href="javascript:deleteComment(` + comment.commentId + `)" target="_self" class="dropdown-item commentDelete" style="color: #ff0000;">삭제</a>`;
        text += `</li></ul></div></div></div></div></div>`;
        text += `</li>`;
    });

    if(comments.length >= 5){
        text += `<button onclick="javascript:clickMoreCommentBtn();" class="comment-more-btn" style="width: 100%; background: transparent; border: none; font-size: 12px;">더보기</button>`;
    }

    $(".post-comments-list").html(text);
    $(".image-preview").remove();
    $file.removeAttr('disabled');
    $(".attach-image-icon").css('cursor', 'pointer');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iIzJEMkQyRCIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');

    countComment();
    hideCommentMenu();
}

function showCommentMore(comments){
    let text = "";

    comments.forEach(comment => {
        text += `<li data-v-eb37dd0c="" data-v-e30e236e="" class="post-comments-list-item">`;
        text += `<div data-v-6f126738="" data-v-eb37dd0c="" class="post-comment-wrapper">`;
        text += `<div data-v-6f126738="" class="profile-image provider">`;
        text += `<img data-v-6f126738="" alt="" class="image" data-src="https://static.cdn.soomgo.com/upload/profile/dce93681-1662-4df6-b116-bed3bc418d25.jpg?h=110&amp;w=110" src="` + comment.memberProfileName + `" lazy="loaded">`;
        text += `</div>`;
        text += `<div data-v-6f126738="" class="comment-information">`;
        text += `<span data-v-6f126738="" class="user-name sg-text-subhead4 sg-font-bold sg-text-gray-900">` + comment.memberName + `</span>`;
        text += `<div data-v-6f126738="" class="content">`;
        text += `<p data-v-6f126738="" class="text sg-text-body2 sg-font-regular">`;
        text += `<span data-v-6f126738="" class="commentContent ` + comment.commentId + `">` + comment.commentContent + `</span>`;
        text += `</p></div>`;
        text += comment.commentFileName != null ?`<ul data-v-25f837a2="" class="attached-image-wrapper">` : '';
        text += comment.commentFileName != null ?`<li data-v-25f837a2="" data-type="lightGallery" data-exthumbimage="https://static.cdn.soomgo.com/upload/media/0e9b2380-ea7a-4ffa-a75f-ff6d55bcfaa4.jpg?h=110&amp;w=110" data-sub-html-src="https://static.cdn.soomgo.com/upload/media/0e9b2380-ea7a-4ffa-a75f-ff6d55bcfaa4.jpg?h=110&amp;w=110" data-src="https://static.cdn.soomgo.com/upload/media/0e9b2380-ea7a-4ffa-a75f-ff6d55bcfaa4.jpg" data-sub-html=" " class="attached-image-item">` : '';
        text += comment.commentFileName != null ? `<img data-v-25f837a2="" class="image" onclick="javascript:openPhotoModal(this.src);" src="` + comment.commentFileName + `" lazy="loaded">` : '';
        text += comment.commentFileName != null ?`</li>` : '';
        text += comment.commentFileName != null ?`</ul>` : '';
        text += `<div data-v-6f126738="" class="comment-action-group sg-text-description sg-font-regular">`;
        text += `<div data-v-6f126738="" class="comment-react">`;
        text += `<span data-v-6f126738="" class="text">`;
        text += comment.createdDate == comment.updatedDate ? "작성 " + communityService.timeForToday(comment.createdDate)
            : "수정 " + communityService.timeForToday(comment.updatedDate);
        text += `</span>`;
        text += `</div>`;
        text += `<div data-v-6f126738="" class="more-action">`;
        text += `<div data-v-6f126738="" class="dropdown b-dropdown show btn-group" id="__BVID__1555">`;
        text += `<button aria-haspopup="true" aria-expanded="true" type="button" class="btn board-dropdown-toggle btn-secondary btn-comment ` + comment.commentId +`" onclick="javascript:openCommentMenu(` + comment.commentId + `)"></button>`;
        text += `<ul role="menu" tabindex="-1" class="dropdown-menu dropdown-menu-right comment-menu ` + comment.commentId + `" aria-labelledby="__BVID__1555__BV_toggle_" style="position: absolute; transform: translate3d(-116px, 22px, 0px); top: 0px; left: 0px; will-change: transform;" x-placement="bottom-end">`;
        text += `<li data-v-77f4e41c="" role="presentation">`;
        text += `<a role="menuitem" href="javascript:updateComment(` + comment.commentId + `)" target="_self" class="dropdown-item commentUpdate">수정</a>`;
        text += `</li>`;
        text += `<li data-v-77f4e41c="" role="presentation">`;
        text += `<a role="menuitem" href="javascript:deleteComment(` + comment.commentId + `)" target="_self" class="dropdown-item commentDelete" style="color: red;">삭제</a>`;
        text += `</li></ul></div></div></div></div></div>`;
        text += `</li>`;
    });

    if(comments.length >= 5){
        text += `<button onclick="javascript:clickMoreCommentBtn();" class="comment-more-btn" style="width: 100%; background: transparent; border: none; font-size: 12px;">더보기</button>`;
    }

    $(".post-comments-list").append(text);
    $file.removeAttr('disabled');
    $(".attach-image-icon").css('cursor', 'pointer');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iIzJEMkQyRCIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');

    countComment();
}

/* 댓글 첨부파일 업로드 */
function afterUploadeCommentFile(file){
    let text = "";

    text += `<div data-v-60f50a1e="" class="image-preview">`;
    text += `<img data-v-60f50a1e="" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik01LjM2NCA0LjIzNGEuNzk4Ljc5OCAwIDEgMC0xLjEzIDEuMTNMNi44NyA4LjAwMWwtMi42MzcgMi42MzZhLjguOCAwIDAgMCAxLjEzIDEuMTI5TDggOS4xM2wyLjYzNiAyLjYzNWEuNzk4Ljc5OCAwIDEgMCAxLjEzLTEuMTNMOS4xMyA4LjAwMmwyLjYzNy0yLjYzN2EuOC44IDAgMCAwLTEuMTMtMS4xMjlMOCA2Ljg3IDUuMzY0IDQuMjM0eiIgZmlsbD0iI0ZGRiIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=" class="delete-badge">`;
    text += `<img data-v-60f50a1e="" src="` + file + `" class="image">`;
    text += `<input type="hidden" name="commentFileName" value ="` + file + `">`;
    text += `</div>`;

    $(".comment-body").append(text);
    $file.attr('disabled', 'true');
    $(".attach-image-icon").css('cursor', 'default');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iI0M1QzVDNSIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');
}



