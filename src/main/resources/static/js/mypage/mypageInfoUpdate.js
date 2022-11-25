/* mypageInfoUpdate.html */

let $profileModal = $("#__BVID__264___BV_modal_outer_");
let $profileModalBackground = $("#__BVID__264");
let $profileModalCancelBtn = $(".modal-cancel-btn");

let $dropOutBtn = $(".delete-account");
let $dropOutModal = $(".swal2-container.dropout-modal");
let $dropOutModalCancelBtn = $(".swal2-confirm");

let $inputName = $("input[name='username']");
let $inputEmail = $("input[name='email']");
let $inputCurrentPassword = $("input[name='currentPassword']");
let $inputNewPassword = $("input[name='password']");
let $inputNewPasswordConfirm = $("input[name='newPasswordConfirm']");
let $inputRestPhone = $("input[name='requestPhone']");
let $inputVerifyPhone = $("input[name='requestVerifyPhone']");
let $inputPhoneNumberBtn = $("#request-phone-btn");

const $file = $("#__BVID__268");
const $thumbnail = $("#user-profile");


$(".user-profile-picture").on('click', function(){
    profileModalOpen();
});

$profileModalCancelBtn.on('click', function(){
    profileModalClose();
});

$profileModalBackground.on('click', function(){
    profileModalClose();
});

$dropOutBtn.on('click', function(){
    dropOutModalOpen();
});

$dropOutModalCancelBtn.on('click', function(){
    dropOutModalClose();
});

/* $dropOutModal.on('click', function(){
    dropOutModalClose();
}); */

$(".modifyOK-info-btn").on('click', function(){
    modifyOKUserInfo();
});

$inputName.on('blur', function(){
    inputNameTest();
});

$inputEmail.on('blur', function(){
    inputEmailTest();
});

$inputCurrentPassword.on('blur', function(){
    inputCurrentPasswordTest();
});

$inputNewPassword.on('blur', function(){
    inputNewPasswordTest();
});

$inputNewPasswordConfirm.on('blur', function(){
    inputNewPasswordConfirmTest();
});

$inputPhoneNumberBtn.on('click', function(){
    inputDisabledTest();
});

$inputRestPhone.on('blur', function(){
    inputPhoneNumberTest();
});

$inputVerifyPhone.on('blur', function(){
    verifyPhoneTest();
});

/* 프로필 모달 띄우기 */
function profileModalOpen(){
    $profileModal.css('display', 'block');
}

/* 프로필 모달 취소하기 버튼 */
function profileModalClose(){
    $profileModal.css('display', 'none');
}


/* 탈퇴 모달 띄우기 */
function dropOutModalOpen(){
    $dropOutModal.css('display', '');
}

/* 탈퇴 모달 취소하기 버튼 */
function dropOutModalClose(){
    $dropOutModal.css('display', 'none');
}


/* 회원정보 수정 유효성 검사 */
function modifyOKUserInfo(){
    /* 이름 적었는지 확인 */
    if(!inputNameTest()){
        $inputName.focus();
        return;
    }
    /* 이메일 적었는지 확인 */
    if(!inputEmailTest()){
        $inputEmail.focus();
        return;
    }
    /* 현재 비밀번호를 적었는지, DB에 있는 비밀번호와 일치하는지 확인하기 */
    if(!inputCurrentPasswordTest()){
        $inputCurrentPassword.focus();
        return;
    }
    /* 새 비밀번호를 적었다면 비밀번호 확인까지 입력했는지 */
    if(inputNewPasswordTest()){
        if(!inputNewPasswordConfirmTest()){
            $inputNewPasswordConfirm.focus();
            return;
        }
    }
    /* 전화번호 재설정을 눌렀다면 인증번호 확인까지 완료했는지 */
    if(!inputDisabledTest()){
        if(!verifyPhoneTest) { return; }
    }

    $(".modify-info-modal").css('display', 'block');
}

/* 이름 유효성 검사 */
function inputNameTest(){
    if(!$inputName.val()) {
        $(".mypage-invalid-feedback.name").css('display', 'block');
        return false;
    }
    $(".mypage-invalid-feedback.name").css('display', 'none');
    return true;
}

/* 이메일 유효성 검사 */
function inputEmailTest(){
    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    if($inputEmail.val() == "") {
        $(".mypage-invalid-feedback.email").css('display', 'block');
        return false;
    }else if(!filter.test($inputEmail.val())){
        $(".mypage-invalid-feedback.email").html("올바른 이메일 주소를 입력해주세요.")
        $(".mypage-invalid-feedback.email").css('display', 'block');
        return false;
    }
    $(".mypage-invalid-feedback.email").css('display', 'none');
    return true;
}

/* 기존 비밀번호 유효성 검사 */
function inputCurrentPasswordTest(){
    if(!$inputCurrentPassword.val()) {
        $(".mypage-invalid-feedback.current-password").css('display', 'block');
        return false;
    }
    $(".mypage-invalid-feedback.current-password").css('display', 'none');
    return true;
}

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

/* 새로운 비밀번호 유효성 검사 */
function inputNewPasswordTest(){
    let pwFilter = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,20}$/;

    if(!$inputNewPassword.val()) {
        $(".mypage-invalid-feedback.new-password").text("비밀번호를 입력하세요.");
        $(".mypage-invalid-feedback.new-password").css('display', 'block');
        return false;
    }else if(!pwFilter.test($inputNewPassword.val())){
        $(".mypage-invalid-feedback.new-password").text("비밀번호는 영문과 특수문자, 숫자를 포함하며 8자 이상이어야 합니다.");
        $(".mypage-invalid-feedback.new-password").css('display', 'block');
        return false;
    }
    $(".mypage-invalid-feedback.new-password").css('display', 'none');
    return true;
}

/* 새 비밀번호 확인 유효성 검사 */
function inputNewPasswordConfirmTest(){
    if(!$inputNewPasswordConfirm.val()) {
        $(".mypage-invalid-feedback.new-password-confirm").css('display', 'block');
        return false;
    }else if($inputNewPassword.val() != $inputNewPasswordConfirm.val()){
        $(".mypage-invalid-feedback.new-password-confirm").text("비밀번호가 일치하지 않습니다.")
        $(".mypage-invalid-feedback.new-password-confirm").css('display', 'block');
        return false;
    }
    $(".mypage-invalid-feedback.new-password-confirm").css('display', 'none');
    return true;
}

/* 전화번호 재설정 버튼 클릭 시 disabled 해제 */
function inputDisabledTest(){
    if($inputPhoneNumberBtn.text() == '재설정'){
        $("legend[for='request-phone']").text("휴대전화 번호 인증");
        $inputPhoneNumberBtn.text("전송");
        $inputPhoneNumberBtn.attr('disabled', 'true');
        $inputPhoneNumberBtn.addClass('disabled');
        $inputRestPhone.attr('placeholder', '예) 01012345678');
        $inputRestPhone.removeAttr('disabled');
        $inputRestPhone.removeClass('complete-phone-auth');
        return false;

    }else if($inputPhoneNumberBtn.text() == '전송'){
        $(".phone-validation").css('display', 'block');
        $("#__BVID__88").css('display', 'block');
        return false;
    }
}

/* 전화번호 입력 유효성 검사 */
function inputPhoneNumberTest(){
    if(!$inputRestPhone.val()) {
        $(".mypage-invalid-feedback.phoneNumber").css('display', 'block');
        return false;
    }else if(isNaN($inputRestPhone.val())){
        $(".mypage-invalid-feedback.phoneNumber").text("숫자만 입력해주세요.")
        $(".mypage-invalid-feedback.phoneNumber").css('display', 'block');
        return false;
    }else if($inputRestPhone.val().length != 11 || !$inputRestPhone.val().startsWith('010')){
        $(".mypage-invalid-feedback.phoneNumber").text("올바른 전화번호를 입력해주세요.")
        $(".mypage-invalid-feedback.phoneNumber").css('display', 'block');
        return false;
    }
    $(".mypage-invalid-feedback.phoneNumber").css('display', 'none');
    $(".send-button").removeAttr('disabled');
    $(".send-button").removeClass("disabled");
    return true;
}

/* 인증번호 유효성 검사 */
function verifyPhoneTest(){
    if($inputVerifyPhone.val() == '0000'){
        $inputVerifyPhone.attr('disabled', 'true');
        $inputVerifyPhone.addClass('complete-phone-auth');
        $(".validation-msg-wrapper.auth-key").text("인증되었습니다.");
        $(".validation-msg-wrapper.auth-key").css('color', '#00c7ae');
        return true;
    }
    $(".validation-msg-wrapper.auth-key").css('display', 'block');
    return false;
}

/* 프로필 사진 변경 시 썸네일 변경 */

$file.on('change', function(e){
    var reader = new FileReader();
    let type = e.target.files[0].type;

    reader.readAsDataURL(e.target.files[0]);

    reader.onload = function(e){
        let url = e.target.result;
        console.log(url);
        console.log(url.includes('image'));

        if(url.includes('image')){
            $thumbnail.attr('src', url);
        } else {
            $thumbnail.attr('src', 'https://www.jigushop.co.kr/common/img/default_profile.png');
        }
    }
});
