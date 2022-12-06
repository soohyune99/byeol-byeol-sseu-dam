/* CommunityRead.html */

stickyNav();

let $dropdownMenu = $(".dropdown-menu.dropdown-menu-right.sticky-nav-menu");
let $boardMenu = $(".dropdown-menu.dropdown-menu-right.board-menu");
let $commentMenu = $(".dropdown-menu.dropdown-menu-right.comment-menu");
let $commentContent = $("textarea[name='comment-input']");
let $commentSubmitBtn = $(".write-comment-submit");
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


/* 댓글 도시락 버튼 클릭 시 수정, 삭제 메뉴 보이기 */
// $("button#__BVID__1555__BV_toggle_").on("click", function(){
//     openCommentMenu($commentMenu);
// });


/* 댓글 입력 시 로그인 안 되어 있을 경우 로그인 모달창 띄우기 */
// $("#__BVID__1499").on("focus", function(){
//     openLoginModal();
// });


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

function openCommentMenu(menu){
    console.log(menu);
    $(`".` + menu + `"`).toggle();
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

/* 댓글 사진 첨부 시 div 생성 */
$file.on('change', function (e) {
    var reader = new FileReader();
    let type = e.target.files[0].type;

    reader.readAsDataURL(e.target.files[0]);

    reader.onload = function (e) {
        let url = e.target.result;
        let text = "";

        if (url.includes('image')) {
            text += `<div data-v-60f50a1e="" class="image-preview">`;
            text += `<img data-v-60f50a1e="" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik01LjM2NCA0LjIzNGEuNzk4Ljc5OCAwIDEgMC0xLjEzIDEuMTNMNi44NyA4LjAwMWwtMi42MzcgMi42MzZhLjguOCAwIDAgMCAxLjEzIDEuMTI5TDggOS4xM2wyLjYzNiAyLjYzNWEuNzk4Ljc5OCAwIDEgMCAxLjEzLTEuMTNMOS4xMyA4LjAwMmwyLjYzNy0yLjYzN2EuOC44IDAgMCAwLTEuMTMtMS4xMjlMOCA2Ljg3IDUuMzY0IDQuMjM0eiIgZmlsbD0iI0ZGRiIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=" class="delete-badge">`;
            text += `<img data-v-60f50a1e="" src="` + url + `" alt="미리보기 이미지" class="image">`;
            text += `</div>`;

            $(".comment-body").append(text);
            $file.attr('disabled', 'true');
            $(".attach-image-icon").css('cursor', 'default');
            $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iI0M1QzVDNSIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');
        } else {
            alert("사진파일만 업로드 가능합니다.");

            return;
        }
    }
});

/* 첨부파일 x 클릭 시 div 삭제 */
$(".comment-body").on('click', '.delete-badge', function(){
    $file.removeAttr('disabled');
    $(".attach-image-icon").css('cursor', 'pointer');
    $(".attach-image-icon").attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Im0xMi43NSAyLjUgMS42NzggMS43NmgyLjkwNWMxLjAwOSAwIDEuODM0Ljc5IDEuODM0IDEuNzU5djEwLjU1NWMwIC45NjgtLjgyNSAxLjc2LTEuODM0IDEuNzZIMi42NjdjLTEuMDA5IDAtMS44MzQtLjc5Mi0xLjgzNC0xLjc2VjYuMDJjMC0uOTY4LjgyNS0xLjc2IDEuODM0LTEuNzZoMi45MDVMNy4yNSAyLjVoNS41ek0xMCA4LjE1NWMtMS44OTggMC0zLjQzOCAxLjUyLTMuNDM4IDMuMzkzIDAgMS44NzIgMS41NCAzLjM5MiAzLjQzOCAzLjM5MiAxLjg5OCAwIDMuNDM4LTEuNTIgMy40MzgtMy4zOTIgMC0xLjg3My0xLjU0LTMuMzkzLTMuNDM4LTMuMzkzeiIgZmlsbD0iIzJEMkQyRCIgZmlsbC1ydWxlPSJldmVub2RkIi8+Cjwvc3ZnPgo=');
    $(".image-preview").remove();
});

/* 댓글 내용 입력 시 등록 버튼 보이기 */
$commentContent.on('keyup', function(){
  if(!$commentContent.val()){ return; }
    $commentSubmitBtn.addClass("active");
});



/* ================================== Board ==================================*/

function showBoardDetail(boards){
    let text = "";

    text += `<div data-v-77f4e41c="" data-v-7037b88b="" class="post-header">`;
    text += `<div data-v-01b8dd61="" data-v-77f4e41c="" class="post-category-subject">`;
    text += `<ol data-v-01b8dd61="" class="category-breadcrumb list-inline">`;
    text += `<li data-v-01b8dd61="" class="category-breadcrumb-item">`;
    text += `<a data-v-01b8dd61="" href="/community" class="breadcrumb-item" data-testid="soomgo-life-home-breadcrumb">`;
    text += `<span data-v-01b8dd61="" class="sg-text-body2 sg-font-regular sg-text-gray-400">커뮤니티</span>`;
    text += `</a></li>`;
    text += `<p data-v-01b8dd61="" class="breadcrumb-divider"></p>`;
    text += `<li data-v-01b8dd61="" class="category-breadcrumb-item">`;
    text += `<a data-v-01b8dd61="" href="/community/soomgo-life/find-provider?from=breadcrumb" class="breadcrumb-item">`;
    text += `<span data-v-01b8dd61="" class="sg-text-body2 sg-font-regular sg-text-gray-400">환경활동</span>`;
    text += `</a></li></ol></div>`;
    text += `<div data-v-77f4e41c="" class="post-header-title-wrapper has-service">`;
    text += `<p data-v-77f4e41c="" class="post-service-name">환경활동</p>`;
    text += `<h1 data-v-77f4e41c="" data-testid="soomgo-life-post-title" class="post-header-title sg-text-display3 sg-font-bold">안녕하세요9</h1>`;
    text += `</div>`;
    text += `<div data-v-e0e63576="" data-v-77f4e41c="" class="observer-container">`;
    text += `<div data-v-77f4e41c="" data-v-e0e63576="" class="user-profile-wrapper">`;
    text += `<div data-v-77f4e41c="" data-v-e0e63576="" class="user-profile-area">`;
    text += `<img data-v-77f4e41c="" data-v-e0e63576="" alt="이정현" class="profile-image" data-src="https://static.cdn.soomgo.com/upload/profile/f69bc8fc-b536-426a-9244-632c7baa7233.jpg" src="https://static.cdn.soomgo.com/upload/profile/f69bc8fc-b536-426a-9244-632c7baa7233.jpg" lazy="loaded">`;
    text += `<img data-v-77f4e41c="" data-v-e0e63576="" alt="이정현" class="profile-image" data-src="https://static.cdn.soomgo.com/upload/profile/f69bc8fc-b536-426a-9244-632c7baa7233.jpg" src="https://static.cdn.soomgo.com/upload/profile/f69bc8fc-b536-426a-9244-632c7baa7233.jpg" lazy="loaded">`;
    text += `<div data-v-77f4e41c="" data-v-e0e63576="" class="profile-post-info">`;
    text += `<span data-v-77f4e41c="" class="user-name sg-text-subhead5 sg-font-medium" data-v-e0e63576="">성은지</span>`;
    text += `<div>`;
    text += `<span data-v-77f4e41c="" class="post-created-at sg-text-description sg-font-regular" data-v-e0e63576="">2022. 12. 02</span>`;
    text += `<span data-v-77f4e41c="" class="post-created-at sg-text-description sg-font-regular" data-v-e0e63576=""> · 조회 91</span>`;
    text += `</div></div></div>`;
    text += `<div data-v-77f4e41c="" data-v-e0e63576="" class="post-actions">`;
    text += `<div data-v-77f4e41c="" class="dropdown b-dropdown btn-group" data-v-e0e63576="" id="__BVID__1550">`;
    text += `<button aria-haspopup="true" aria-expanded="false" type="button" class="btn board-dropdown-toggle btn-secondary board-dropdownmenu-menuBtn" id="__BVID__1550__BV_toggle_"></button>`;
    text += `<ul role="menu" tabindex="-1" class="dropdown-menu dropdown-menu-right board-menu" aria-labelledby="__BVID__1550__BV_toggle_">`;
    text += `<li data-v-77f4e41c="" role="presentation">`;
    text += `<a role="menuitem" href="#" target="_self" class="dropdown-item boardUpdate">수정</a>`;
    text += `</li>`;
    text += `<li data-v-77f4e41c="" role="presentation">`;
    text += `<a role="menuitem" href="#" target="_self" class="dropdown-item boardDelete" style="color: red;">삭제</a>`;
    text += `</li></ul></div></div></div></div>`;
    text += `<div data-v-77f4e41c="" class="v-portal" style="display: none;"></div>`;
    text += `</div>`;

    $(".").html(text);
}





/* ================================== Comment ==================================*/


/* 댓글 등록 */
$commentSubmitBtn.on("click", function(){
    console.log($commentContent.val());
    console.log(boardId);
    console.log(member);
    commentService.save({
        boardId: boardId,
        memberId: member.memberId,
        commentContent: $commentContent.val()
    }, function(){console.log("성공")}, function(){console.log("실패")});
});








