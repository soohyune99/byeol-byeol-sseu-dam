/* mypageMain.html */

getMyInfo();

function getMyInfo(){
    if(memberProfileName != null || memberProfileName != ''){
        $(".member-profile-img").attr('src', memberProfileName);
    }
    $(".mypage-memberName").html(memberName);
    $(".mypage-memberEmail").html(memberEmail);
}