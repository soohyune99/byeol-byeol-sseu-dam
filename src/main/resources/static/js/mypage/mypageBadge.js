/* mypageBadge.html */

let $mybadgeWrap = $(".mybadgeWrap");
let $badgeModal = $(".swal2-container");
let $modalCloseBtn = $(".swal2-confirm.btn");

$mybadgeWrap.on('click', function(){
    badgeModalOpen();
});

$modalCloseBtn.on('click', function(){
    badgeModalClose();
});


/* 배지 정보 모달 열기 */

function badgeModalOpen(){
    $badgeModal.css('display', 'block');
}


/* 배지 정보 모달 닫기 */
function badgeModalClose(){
    $badgeModal.css('display', 'none');
}