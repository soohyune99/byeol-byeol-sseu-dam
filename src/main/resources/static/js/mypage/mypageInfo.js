/* mypageInfo.js */

getMemberInfo();

/* 기본 회원 정보 조회 */
function getMemberInfo(){
    mypageService.getMyInfo(
        memberId, showMyInfo
    )
}

function showMyInfo(myinfo){
    let memberVerification = "";
    memberPhone = memberPhone == '' || memberPhone == null ? "전화번호로 본인인증을 진행해주세요." : memberPhone;
    memberLoginType = memberLoginType == '일반' ? '일반회원' : memberLoginType;

    if(memberCategory == "기사회원"){
        memberVerification = "기사회원";
    } else if(memberCategory == "관리자"){
        memberVerification = "관리자";
    } else {
        memberVerification = memberPhone == '' || memberPhone == null ? "비인증회원" : "인증회원";
    }

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