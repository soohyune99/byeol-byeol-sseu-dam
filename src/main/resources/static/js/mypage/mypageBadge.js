/* mypageBadge.html */

let $acquiredBadge = $(".acquired");
let $badgeModal = $(".swal2-container");
let $modalCloseBtn = $(".swal2-confirm.btn");

getBadge();
getMybadge();
showMyInfo();

// $("div.mybadge").on('click', ".acquired", function(){
//     $badgeModal.css('display', 'block');
//
// });

$modalCloseBtn.on('click', function(){
    badgeModalClose();
});

/* 배지 정보 모달 열기 */
function badgeModalOpen(badgeId){
    let $badgeImg = $(".mybadgeImg.acquired." + badgeId).val();
    let $badgeName = $(".mybadgeImg.acquired." + badgeId).next().text()
    let $badgeInfo = $(".mybadgeImg.acquired." + badgeId).next().next().text();

    $(".acquired-badgeName").html($badgeName);
    $(".acquired-badgeImage").html($badgeImg);
    $(".acquired-badgeInfo").html($badgeInfo);

    $badgeModal.css('display', 'block');
}

/* 배지 정보 모달 닫기 */
function badgeModalClose(){
    $badgeModal.css('display', 'none');
}

/* 회원 정보 */
function showMyInfo(){
    $(".mypage-memberProfileName").attr('src', memberProfileName);
    $(".mypage-memberName").html(memberName);
    $(".mypage-memberEmail").html(memberEmail);
    $(".mypage-memberType").html(memberCategory);
    $(".mypage-memberPoint").html(memberPoint);
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
    let badgeAr = [];
    let badgeArray = [];

    mybadges.forEach(mybadge => {
        badgeAr.push(mybadge.badgeId);

        $(".mybadgeWrap." + mybadge.badgeId).addClass("acquired");
        $(".mybadgeImg." + mybadge.badgeId).addClass("acquired");
        $(".mybadgeImg." + mybadge.badgeId).attr('onclick', 'badgeModalOpen(' + mybadge.badgeId + ')');
    });
    badgeArray = Array.from(new Set(badgeAr));

    $(".acquiredBadge").html(badgeArray.length);
}





