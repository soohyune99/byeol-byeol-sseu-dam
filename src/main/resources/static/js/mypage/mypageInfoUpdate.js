/* mypageInfoUpdate.html */

/* 프로필 파일 선택 모달 */
let $profileModal = $("#__BVID__264___BV_modal_outer_");
let $profileModalBackground = $("#__BVID__264");
let $profileModalCancelBtn = $(".modal-cancel-btn");

/* 탈퇴 모달 */
let $dropOutBtn = $(".delete-account");
let $dropOutModal = $(".swal2-container.dropout-modal");
let $dropOutModalCancelBtn = $(".swal2-confirm");

/* 회원정보 입력 */
let $inputName = $("input[name='memberName']");
let $inputEmail = $("input[name='email']");
let $inputCurrentPassword = $("input[name='currentPassword']");
let $inputNewPassword = $("input[name='password']");
let $inputNewPasswordConfirm = $("input[name='newPasswordConfirm']");
// 전화번호 입력 input
let $inputRestPhone = $("input[name='requestPhone']");
// 인증번호 입력 input
let $inputVerifyPhone = $("input[name='requestVerifyPhone']");
let $inputPhoneNumberBtn = $("#request-phone-btn");
let $sendPhoneNumberBtn = $("#send-phone-btn");
// 기존에 저장돼있거나 인증이 완료된 전화번호를 담고 있는 hidden input
let $verificatedPhone = $("#verificated-phoneNumber");

/* 회원 프로필 */
const $file = $("#__BVID__268");
const $thumbnail = $("#user-profile");

globalThis.vertificationNumber = "";
globalThis.profileUrl = "";

$(".user-profile-picture.h-100").on('click', function(){
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

$(".modifyOK-info-btn").on('click', function(){
    modifyOKUserInfo();
});

$inputName.on('blur', function(){
    inputNameTest();
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
    /* 이름 입력했는지 확인 */
    if(!inputNameTest()){
        $inputName.focus();
        return;
    }

    /* 비밀번호 입력했는지 확인 */
    if(!inputNewPasswordTest()){
        $inputNewPassword.focus();
        return;
    }

    /* 새 비밀번호를 적었다면 비밀번호 확인까지 입력했는지 */
    if(!inputNewPasswordConfirmTest()){
        $inputNewPasswordConfirm.focus();
        return;
    }

    updateOkMyinfo();
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
function inputDisabledTest() {
    $("legend[for='request-phone']").text("휴대전화 번호 인증");
    $sendPhoneNumberBtn.css('display', 'block');
    $inputPhoneNumberBtn.css('display', 'none');
    $inputRestPhone.attr('placeholder', '예) 01012345678');
    $inputRestPhone.removeAttr('disabled');
    $inputRestPhone.removeClass('complete-phone-auth');
    $("#request-phone").val('');
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
    $sendPhoneNumberBtn.removeAttr('disabled');
    $sendPhoneNumberBtn.removeClass("disabled");
}

/* 전화번호 입력 후 인증번호 전송 */
function sendVertification(){
    $(".phone-validation").css('display', 'block');
    $("#__BVID__88").css('display', 'block');

    console.log($inputRestPhone.val())

    mypageService.sendVerification(
        $inputRestPhone.val(), saveVerification
    )
}

function saveVerification(vertificationNumber){
    console.log(vertificationNumber);
    globalThis.vertificationNumber = vertificationNumber;
}

/* 인증번호 유효성 검사 */
function verifyPhoneTest(){
    if($inputVerifyPhone.val() == globalThis.vertificationNumber){
        $inputVerifyPhone.attr('disabled', 'true');
        $inputVerifyPhone.addClass('complete-phone-auth');
        $inputRestPhone.attr('disabled', 'true');
        $inputRestPhone.addClass('complete-phone-auth');

        $inputPhoneNumberBtn.css('display', 'block');
        $sendPhoneNumberBtn.css('display', 'none');

        $verificatedPhone.val($inputRestPhone.val());

        $(".validation-msg-wrapper.auth-key").text("인증되었습니다.");
        $(".validation-msg-wrapper.auth-key").css('display', 'block');
        $(".validation-msg-wrapper.auth-key").css('color', '#00c7ae');
    }
    $(".validation-msg-wrapper.auth-key").css('display', 'block');
}

/* ================================= update =================================*/

getMemberInfo();

/* 기본 회원 정보 조회 */
function getMemberInfo(){
    mypageService.getMyInfo(
        memberId, showMemberInfo
    )
}

/* 회원 정보 조회 */
function showMemberInfo(member){
    console.log(member.memberName)
    $("#user-profile").attr('src', member.memberProfileName);
    $("#__BVID__139").val(member.memberName);
    $("input[name='email']").val(member.memberEmail);

    if(member.memberPhone == 'undefined' || member.memberPhone == null){
        $("legend[for='request-phone']").text("휴대전화 번호 인증");
        $sendPhoneNumberBtn.css('display', 'block');
        $inputPhoneNumberBtn.css('display', 'none');
        $inputRestPhone.attr('placeholder', '예) 01012345678');
        $inputRestPhone.removeAttr('disabled');
        $inputRestPhone.removeClass('complete-phone-auth');
    }else {
        $("#request-phone").val(member.memberPhone);
        $verificatedPhone.val(member.memberPhone);
    }
}

/* 프로필 사진 변경 시 썸네일 변경 및 업로드 파일에 저장 */
$file.on('change', function(e){
    let file = $file[0].files[0];
    var reader = new FileReader();
    let type = e.target.files[0].type;

    reader.readAsDataURL(e.target.files[0]);

    reader.onload = function(e){
        let url = e.target.result;

        if(url.includes('image')){
            $thumbnail.attr('src', url);
        } else {
            $thumbnail.attr('src', 'https://www.jigushop.co.kr/common/img/default_profile.png');
        }
    }

    mypageService.uploadProfileFile(
        file, afterUploadeProfileFile
    );
});

function afterUploadeProfileFile(url) {
    globalThis.profileUrl = url;
}

/* 수정 완료 눌렀을 때 */
function updateOkMyinfo(){
    let formData = new FormData();

    formData.append('memberId', memberId);
    formData.append('memberName', $inputName.val());
    formData.append('memberPassword', $inputNewPasswordConfirm.val());
    formData.append('memberPhone', $verificatedPhone.val());
    formData.append('memberProfileName', globalThis.profileUrl);

    console.log($thumbnail.attr('src'));
    console.log(formData.get('memberProfileName'));

    mypageService.updateUserInfo(
        formData, afterUpdateMyinfo
    );
}

function afterUpdateMyinfo(member){
    console.log(member.memberName);
    $(".update-memberName").html(member.memberName);
    $(".modify-info-modal").css('display', 'block');
}

/* 탈퇴 버튼 눌렀을 때 */
function dropOutMember(){
    mypageService.dropOutMember(
        memberId, logoutDropOutMember
    );
}

function logoutDropOutMember(){
    mypageService.logoutMember(
        function(){
            location.href='/main';
        }
    );
}