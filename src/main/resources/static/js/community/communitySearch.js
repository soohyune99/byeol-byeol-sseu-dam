/* communitySearch.js */

let $writeBtn = $(".write-button-desktop");
let $closeBtn = $(".swal2-cancel");
let $loginModal = $(".swal2-container")


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