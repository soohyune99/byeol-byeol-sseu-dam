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

    if(myinfo.memberCategory == "기사회원"){
        memberVerification = "기사회원";
    } else if(myinfo.memberCategory == "관리자"){
        memberVerification = "관리자";
    } else {
        memberVerification = myinfo.memberPhone == '' || myinfo.memberPhone == null ? "비인증회원" : "인증회원";
    }

    myinfo.memberPhone = myinfo.memberPhone == '' || myinfo.memberPhone == null ? "전화번호로 본인인증을 진행해주세요." : myinfo.memberPhone;
    myinfo.memberLoginType = myinfo.memberLoginType == '일반' ? '일반회원' : myinfo.memberLoginType;

    if(myinfo.memberLoginType == 'KAKAO'){
        $(".member-logintype").css('color', '#f9cf21');
    }

    $("#user-profile").attr('src', myinfo.memberProfileName);
    $(".member-category").html(memberVerification);
    $(".member-logintype").html(myinfo.memberLoginType);
    $(".member-name").html(myinfo.memberName);
    $(".member-email").html(myinfo.memberEmail);
    $(".member-phoneNumber").html(myinfo.memberPhone);
}

/* 소셜 로그인 회원 정보수정 불가 */
function openSocialLoginModal(){
    if(memberLoginType != '일반'){
        $(".swal2-container").show();
        return;
    }
    location.href='/mypage/info/password';
}

/* 모달 닫기 */
function closeSocialLoginModal(){
    $(".swal2-container").hide();
}