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

    $("#user-profile").attr('src', myinfo.memberProfileName);
    // $("#user-profile").attr('alt', 'https://www.jigushop.co.kr/common/img/default_profile.png');
    $(".member-logtype").html(memberLoginType);
    $(".member-name").html(myinfo.memberName);
    $(".member-email").html(myinfo.memberEmail);
    $(".member-phoneNumber").html(memberPhone);

}