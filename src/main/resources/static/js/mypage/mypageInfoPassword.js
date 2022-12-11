/* mypageInfoPassword.html */

const memberId = 1;
let $inputCurrentPassword = $("input[name='currentPassword']");

/* 비밀번호 표시 전환 */
$(".current-password button.btn").on('click', function(){
    if($(this).text() == '표시') {
        $inputCurrentPassword.attr('type', 'text');
        $(this).text("숨김");
    } else {
        $inputCurrentPassword.attr('type', 'password');
        $(this).text("표시");
    }
});

/* 비밀번호 모달 끄기 */
function closeModal(){
    $("#__BVID__264___BV_modal_outer_").css('display', 'none');
}

/* 비밀번호 확인 */
function checkPassword(){
    mypageService.checkPassword(
        memberId, $("#__BVID__175").val(), afterCheckPassword
    );
}

/* 비밀번호 일치 여부 체크 */
function afterCheckPassword(check){
    if(check){
        location.href='/mypage/info/update';
    }else {
        $("#__BVID__264___BV_modal_outer_").css('display', 'block');
    }
}


