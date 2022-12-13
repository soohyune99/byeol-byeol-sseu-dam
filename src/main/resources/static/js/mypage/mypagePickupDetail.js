/* mypagePickupDetail.html */

const memberId = 1;
let url = decodeURI(window.location.href).split("/");
let pickupId = url[url.length - 1];

getMyPickupDetail();
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

function showMemberInfo(member){
    $(".mypage-memberProfileName").attr('src', member.memberProfileName);
    $(".mypage-memberName").html(member.memberName);
    $(".mypage-memberEmail").html(member.memberEmail);
    $(".mypage-memberType").html(member.memberCategory);
    $(".mypage-memberPoint").html(member.memberPoint);
}

/* 수거신청내역 상세보기 조회 */
function getMyPickupDetail(){
    mypageService.getMyPickup(
        pickupId, showMyPickupDetail
    )
}

function showMyPickupDetail(mypickup){
    console.log(mypickup.pickupId);
    $(".pickup-date").html(mypickup.createdDate);
    $(".pickup-pickupId").html(mypickup.pickupId);
    $(".pickup-petCount").html(mypickup.petCount + "개");
    $(".pickup-glassCount").html(mypickup.glassCount + "개");
    $(".pickup-memberName").html(mypickup.memberName);
    $(".pickup-memberPhone").html(mypickup.memberPhone);
    $(".pickup-memberEmail").html(mypickup.memberEmail);
    $(".pickup-pickupAddress").html(mypickup.pickupAddress);
    $(".pickup-pickupMessage").html(mypickup.pickupMessage);
}







