// 프로필 선택 시

getMemberInfo();

/* 기본 회원 정보 조회 */
function getMemberInfo(){
    getMyInfo(
        memberId, showMemberInfo
    )
}

function getMyInfo(memberId, callback, error){
    $.ajax({
        url: "/mypage/" + memberId,
        type: "get",
        success: function(myinfo, status, xhr){
            if(callback){
                callback(myinfo);
            }
        },
        error: function(xhr, status, err){
            if(error){
                error(err);
            }
        }
    });
}

function showMemberInfo(member){
    $("img#memberProfile").attr('src', member.memberProfileName);
    $("div.memberProfile").css("background-image","url("+ member.memberProfileName +")");
    $("h4.memberName").text(member.memberName);

    if(member.memberLoginType == 'KAKAO'){
        // $("button.logout").attr("onclick","location.href='https://kauth.kakao.com/oauth/logout?client_id=b365527827a25dae48ba21331cda4133&logout_redirect_uri=http://localhost:10001/kakao/logout'")
        $("button.logout").attr("onclick","location.href='/logout'")
    }else if(member.memberLoginType == 'NAVER'){
        $("button.logout").attr("onclick","location.href='/logout'")
    }else if(member.memberLoginType == 'GOOGLE'){
        $("button.logout").attr("onclick","location.href='/logout'")
    }else{
        $("button.logout").attr("onclick","location.href='/logout'")
    }

    if(member.memberLoginType != '일반' && member.memberCategory == '일반회원'){
        $(".change-user-type").attr("onclick"," ");
        $(".change-user-type").on("click",function () {
            alert("소셜 아이디는 기사 가입을 지원하지 않습니다. 일반 계정으로 가입해주세요.")

        })
    }

    // $(".mypage-memberType").html(member.memberCategory);
    // $(".mypage-memberPoint").html(member.memberPoint);
}





let flag = true;
let $memberCategory = $("input[name='memberCategory']").val();
if($memberCategory != "일반회원"){
    $(".change-user-type").css("display", "none")
}else{
    $(".change-user-type").css("display", "block")
}


$('.page-profile').click(function () {
    if (flag) {
        $('.page-dropdown').show();
        $('.pageBtn').attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTIiIGhlaWdodD0iMTIiIHZpZXdCb3g9IjAgMCAxMiAxMiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTAgMGgxMnYxMkgweiIvPgogICAgICAgIDxwYXRoIHN0cm9rZT0iIzg4OCIgc3Ryb2tlLXdpZHRoPSIxLjIiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTTEwIDggNiA0IDIgOCIvPgogICAgPC9nPgo8L3N2Zz4K');
        // $('.pageBtn').attr('src', 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png');
    } else {
        $('.page-dropdown').hide();
        $('.pageBtn').attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTIiIGhlaWdodD0iMTIiIHZpZXdCb3g9IjAgMCAxMiAxMiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTAgMGgxMnYxMkgweiIvPgogICAgICAgIDxwYXRoIHN0cm9rZT0iIzg4OCIgc3Ryb2tlLXdpZHRoPSIxLjIiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTTEwIDQgNiA4IDIgNCIvPgogICAgPC9nPgo8L3N2Zz4K')
        // $('.pageBtn').attr('src', 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png')
    }
    flag = !flag;
})

$('.pageBtn').click(function () {
    if (flag) {
        $('.page-dropdown').show();
        $('.pageBtn').attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTIiIGhlaWdodD0iMTIiIHZpZXdCb3g9IjAgMCAxMiAxMiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTAgMGgxMnYxMkgweiIvPgogICAgICAgIDxwYXRoIHN0cm9rZT0iIzg4OCIgc3Ryb2tlLXdpZHRoPSIxLjIiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTTEwIDggNiA0IDIgOCIvPgogICAgPC9nPgo8L3N2Zz4K');
        // $('.pageBtn').attr('src', 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png');
    } else {
        $('.page-dropdown').hide();
        $('.pageBtn').attr('src', 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTIiIGhlaWdodD0iMTIiIHZpZXdCb3g9IjAgMCAxMiAxMiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTAgMGgxMnYxMkgweiIvPgogICAgICAgIDxwYXRoIHN0cm9rZT0iIzg4OCIgc3Ryb2tlLXdpZHRoPSIxLjIiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTTEwIDQgNiA4IDIgNCIvPgogICAgPC9nPgo8L3N2Zz4K')
        // $('.pageBtn').attr('src', 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png')
    }
    flag = !flag;
})

// 도시락 버튼 클릭 시
$('.moblieBtn').click(function(){
    $('.mobile-usermenu').css('transform', 'translateX(0)');
})

$('.close-button').click(function(){
    $('.mobile-usermenu').css('transform', '');
})