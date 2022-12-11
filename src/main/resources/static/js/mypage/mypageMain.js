/* mypageMain.html */

const memberId = 1;

getMemberInfo();

function getMemberInfo(){
    mypageService.getMyInfo(
        memberId, showMemberInfo
    )
}

function showMemberInfo(member){
    console.log(member.memberProfileName)
    $(".member-profile-img").attr('src', member.memberProfileName);
    $(".mypage-memberName").html(member.memberName);
    $(".mypage-memberEmail").html(member.memberEmail);
}