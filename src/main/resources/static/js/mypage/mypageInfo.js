/* mypageInfo.js */

showMyInfo();


function showMyInfo(myinfo){
    let memberVerification = memberPhone == '' || memberPhone == null ? "비인증회원" : "인증회원";
    memberPhone = memberPhone == '' || memberPhone == null ? "전화번호로 본인인증을 진행해주세요." : memberPhone;
    memberLoginType = memberLoginType == '일반' ? '일반회원' : memberLoginType;

    console.log(memberPhone);
    console.log(memberLoginType)
    console.log(memberVerification)

    $("#user-profile").attr('src', memberProfileName);
    $(".member-category").html(memberVerification);
    $(".member-logintype").html(memberLoginType);
    $(".member-name").html(memberName);
    $(".member-email").html(memberEmail);
    $(".member-phoneNumber").html(memberPhone);
}