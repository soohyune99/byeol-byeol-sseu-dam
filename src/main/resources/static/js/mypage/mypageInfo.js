/* mypageInfo.js */

const memberId = 1;

showMyInfo();

function showMyInfo(){
    mypageService.getMyInfo(
      memberId, showMyInfoCallback
    );
}

function showMyInfoCallback(myinfo){
    let memberPhone = myinfo.memberPhone || "전화번호로 본인인증을 진행해주세요."
    let memberLoginType = myinfo.memberLoginType == '일반' ? '일반회원' : myinfo.memberLoginType;
    let memberVerification = myinfo.memberPhone == null ? "비인증회원" : "인증회원";

    $("#user-profile").attr('src', myinfo.memberProfileName);
    $(".member-category").html(memberVerification);
    $(".member-logtype").html(memberLoginType);
    $(".member-name").html(myinfo.memberName);
    $(".member-email").html(myinfo.memberEmail);
    $(".member-phoneNumber").html(memberPhone);
}