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

/* ============================= memberInfo ============================= */

const memberId = 1;

getMemberInfo();

/* 기본 회원 정보 조회 */
function getMemberInfo(){
    mypageService.getMyInfo(
        memberId, showMemberInfo
    )
}

function showMemberInfo(member){
    $(".mypage-memberProfileName").attr('src', member.memberProfileName);
    $(".mypage-memberName").html(member.memberName);
    $(".mypage-memberEmail").html(member.memberEmail);
    $(".mypage-memberType").html(member.memberCategory);
    $(".mypage-memberPoint").html(member.memberPoint);
}






