/* mypageBadge.html */

let $mybadgeWrap = $(".mybadgeWrap.acquired");
let $badgeModal = $(".swal2-container");
let $modalCloseBtn = $(".swal2-confirm.btn");

const memberId = 1;

getMemberInfo();
getBadge();
getMybadge();

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

/* ============================= badge ============================= */


/* 배지 조회 */
function getBadge(){
    mypageService.getBadges(
        showBadges
    );
}

/* 획득한 배지 조회 */
function getMybadge(){
    mypageService.getMybadges(
        memberId, showMyBadges
    );
}

/* 배지 조회 callback */
function showBadges(badges){
    let text = "";

    text += `<div class="divisionLine"></div>`;
    text += `<div class="badgeList">`;
    text += `<ul>`;
    badges.forEach(function(badge, index){
        if(index >= 0 && index < 3){
            text += `<li class="mybadgeWrap ` + badge.badgeId + `">`;
            text += `<img class="mybadgeImg ` + badge.badgeId + `" src="` + badge.badgeFileName + `">`;
            text += `<p class="badgeName">` + badge.badgeName + `</p>`;
            text += `<p class="badgeInfo">` + badge.badgeInfo + `</p>`;
            text += `</li>`;
        }
    });
    text += `</ul>`;
    text += `<ul>`;
    badges.forEach(function(badge, index){
        if(index >= 3 && index < 6){
            text += `<li class="mybadgeWrap ` + badge.badgeId + `">`;
            text += `<img class="mybadgeImg ` + badge.badgeId + `" src="` + badge.badgeFileName + `">`;
            text += `<p class="badgeName">` + badge.badgeName + `</p>`;
            text += `<p class="badgeInfo">` + badge.badgeInfo + `</p>`;
            text += `</li>`;
        }
    });
    text += `</ul>`;
    text += `</div>`;
    text += `<div class="divisionLine"></div>`;
    text += `<div class="badgeList">`;
    text += `<ul>`;
    badges.forEach(function(badge, index){
        if(index >= 6 && index < 9){
            text += `<li class="mybadgeWrap ` + badge.badgeId + `">`;
            text += `<img class="mybadgeImg ` + badge.badgeId + `" src="` + badge.badgeFileName + `">`;
            text += `<p class="badgeName">` + badge.badgeName + `</p>`;
            text += `<p class="badgeInfo">` + badge.badgeInfo + `</p>`;
            text += `</li>`;
        }
    });
    text += `</ul>`;
    text += `<ul>`;
    badges.forEach(function(badge, index){
        if(index >= 9 && index < 12){
            text += `<li class="mybadgeWrap ` + badge.badgeId + `">`;
            text += `<img class="mybadgeImg ` + badge.badgeId + `" src="` + badge.badgeFileName + `">`;
            text += `<p class="badgeName">` + badge.badgeName + `</p>`;
            text += `<p class="badgeInfo">` + badge.badgeInfo + `</p>`;
            text += `</li>`;
        }
    });
    text += `</ul>`;
    text += `</div>`;
    text += `<div class="divisionLine"></div>`;
    text += `<div class="badgeList">`;
    text += `<ul>`;
    badges.forEach(function(badge, index){
        if(index >= 12 && index < 15){
            text += `<li class="mybadgeWrap ` + badge.badgeId + `">`;
            text += `<img class="mybadgeImg ` + badge.badgeId + `" src="` + badge.badgeFileName + `">`;
            text += `<p class="badgeName">` + badge.badgeName + `</p>`;
            text += `<p class="badgeInfo">` + badge.badgeInfo + `</p>`;
            text += `</li>`;
        }
    });
    text += `</ul>`;
    text += `<ul>`;
    badges.forEach(function(badge, index){
        if(index >= 15 && index < 18){
            text += `<li class="mybadgeWrap ` + badge.badgeId + `">`;
            text += `<img class="mybadgeImg ` + badge.badgeId + `" src="` + badge.badgeFileName + `">`;
            text += `<p class="badgeName">` + badge.badgeName + `</p>`;
            text += `<p class="badgeInfo">` + badge.badgeInfo + `</p>`;
            text += `</li>`;
        }
    });
    text += `</ul>`;
    text += `</div>`;
    text += `<div class="divisionLine"></div>`;

    $(".mybadge").append(text);
    $(".totalBadge").html(badges.length);
}

/* 획득한 배지 조회 callback */
function showMyBadges(mybadges){
    mybadges.forEach(mybadge => {
        $(".mybadgeWrap." + mybadge.badgeId).addClass("acquired");
        $(".mybadgeImg." + mybadge.badgeId).addClass("acquired");
    });

    $(".acquiredBadge").html(mybadges.length);
}





