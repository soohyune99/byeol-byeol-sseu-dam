
let emailFilter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
let pwFilter = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,20}$/;
let $inputName = $("#memberName");
let $inputGender = $(".gender-radio");
let $selectedGender = $inputGender.find(".radio");
let $inputEmail = $("#memberEmail");
let $inputPw = $("#memberPassword");
let $pwChecking = $("#pwChecking");
let $inputPhone = $("#memberPhone");
let $verification = $("#request-verify-phone");
let $btn = $(".btn-primary");
let verify = false;
let emailDuplicate = false;
let $agree = $("#agree-terms-checkbox-1668331384759");
let $moreThan14 = $("#more-than-14-checkbox-1668331384764");
let $passwordShow = $(".password-show");
let $duplicationCheckBtn = $(".email-show");
//이름 유효성 검사
function nameCheck(){
    if (!$inputName.val()) {
        $inputName.addClass("is-invalid")
        $inputName.siblings(".invalid-feedback").find(".error").css("display", "block");
    } else {
        $inputName.removeClass("is-invalid")
        $inputName.siblings(".invalid-feedback").find(".error").css("display", "none");
        return true;
    }
}
//성별 유효성 검사
function genderCheck(e){
    e.addClass("selected");
    e.closest("span").siblings("span").find(".radio").removeClass("selected")
    $inputGender.removeClass("is-invalid");
    $inputGender.siblings(".invalid-feedback").find(".error").css("display", "none");
}
//이메일 유효성 검사
function idCheck(){
    if (!$inputEmail.val()) {
        $inputEmail.addClass("is-invalid")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("display", "block")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").text("이메일 주소를 입력해주세요.")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("color", "#fa5963")
        $duplicationCheckBtn.attr("disabled", true)
    } else if (!emailFilter.test($inputEmail.val())) {
        $inputEmail.addClass("is-invalid")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("display", "block")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").text("올바른 이메일 주소를 입력해주세요.")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("color", "#fa5963")
        $duplicationCheckBtn.attr("disabled", true)
    } else {
        $inputEmail.removeClass("is-invalid")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("display", "none")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("color", "#00c7ae")
        $duplicationCheckBtn.attr("disabled", false)
        return true;
    }
}
//비밀번호 유효성 검사
function pwCheck(){
    if(!$inputPw.val()){
        $inputPw.attr("class", "input-lg form-control is-invalid invalid")
        $inputPw.parent().siblings(".invalid-feedback").css("display","block")
        $inputPw.parent().siblings(".invalid-feedback").find(".error").css("display", "block");
        $inputPw.parent().siblings(".invalid-feedback").find(".error").text("비밀번호를 입력해주세요.")
    }else if(!pwFilter.test($inputPw.val())){
        $inputPw.attr("class", "input-lg form-control is-invalid invalid")
        $inputPw.parent().siblings(".invalid-feedback").css("display","block")
        $inputPw.parent().siblings(".invalid-feedback").find(".error").css("display", "block");
        $inputPw.parent().siblings(".invalid-feedback").find(".error").text("영문+숫자+특수문자 조합 8자리 이상 입력해주세요.")
    }else{
        $inputPw.parent().siblings(".invalid-feedback").find(".error").css("display", "none");
        $inputPw.attr("class", "input-lg form-control is-valid")
        return true;
    };
}
//비밀번호 확인
function matchPw() {
    if(!$pwChecking.val() || $pwChecking.val() != $inputPw.val()){
        $pwChecking.attr("class", "input-lg form-control is-invalid invalid");
        $pwChecking.parent().siblings(".invalid-feedback").css("display", "block");
        $pwChecking.parent().siblings(".invalid-feedback").find(".error").css("display", "block");
        $pwChecking.parent().siblings(".invalid-feedback").find(".error").text("비밀번호가 일치하지 않습니다.");
    }else{
        $pwChecking.parent().siblings(".invalid-feedback").css("display", "none");
        $pwChecking.attr("class", "input-lg form-control is-valid");
        return true;
    }
}
//이메일 중복 확인
function checkEmailDuplication() {
    if ($inputEmail.val() == "hds1234@gmail.com") {
        $inputEmail.addClass("is-invalid")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("display", "block")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("color", "#fa5963")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").text("중복된 이메일 주소입니다.")
        emailDuplicate = false;
    }else {
        // $inputEmail.parent().siblings(".invalid-feedback").text("사용 가능한 이메일 주소입니다.");
        // $inputEmail.parent().siblings(".invalid-feedback").css("display", "block");
        // $inputEmail.parent().siblings(".invalid-feedback").css("color", "#00c7ae");
        // $inputEmail.removeClass("invalid");
        $inputEmail.removeClass("is-invalid")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("display", "block")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("color", "#00c7ae")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").text("사용 가능한 이메일 주소입니다.")
        emailDuplicate = true;
        return true;
    }
}




//핸드폰 번호 유효성 검사 - 우린 이메일 인증으로 해야하나
function phoneCheck(){
    if (!$inputPhone.val()) {
        $inputPhone.siblings("div").find(".send-button").attr("disabled", true)
        $inputPhone.siblings("div").find(".send-button").addClass("disabled")
        $inputPhone.addClass("error")
        $("p.validation").css("color", "#fa5963")
        $("p.validation").css("display", "block")
        $("p.validation").text("휴대전화 번호를 입력해주세요")
    } else if (!$inputPhone.val().startsWith("010") || $inputPhone.val().length != 11 || isNaN($inputPhone.val())) {
        $inputPhone.siblings("div").find(".send-button").attr("disabled", true)
        $inputPhone.siblings("div").find(".send-button").addClass("disabled")
        $inputPhone.addClass("error")
        $("p.validation").css("color", "#fa5963")
        $("p.validation").css("display", "block")
        $("p.validation").text("잘못된 번호입니다.")
    } else {
        $inputPhone.removeClass("error")
        $("p.validation").css("color", "#00c7ae")
        $("p.validation").css("display", "none")
        $inputPhone.siblings("div").find(".send-button").attr("disabled", false)
        $inputPhone.siblings("div").find(".send-button").removeClass("disabled")
    }
}
//인증번호 유효성 검사
function verificationCheck(){
    //12345는 인증번호 예시
    if($verification.val() == "12345"){
        $(".authentication").css("color", "#00c7ae")
        $(".authentication").text("인증이 완료되었습니다.")

        $inputPhone.addClass("complete-phone-auth")
        $verification.addClass("complete-phone-auth")
        verify = !verify;
    }else{
        $(".authentication").css("color", "#fa5963")
        $(".authentication").text("잘못된 인증번호입니다.")
        $(".answer-input.form-control.is-valid").attr("class", "answer-input form-control is-invalid error")
    }
}
$inputName.on("blur", function () {nameCheck()});
$selectedGender.on("click", function () {genderCheck($(this))});
$inputEmail.on("blur", function () {idCheck()});
$inputPw.on("blur", function () {pwCheck()});
$pwChecking.on("blur", function () {matchPw()});
$inputPhone.on("blur", function () {phoneCheck()});
$inputPhone.on("focus", function () {
    if(!$(this).val()){
        $("p.validation").css("display", "block")
    }
});

$inputPhone.siblings("div").find(".send-button").on("click", function () {
    $("#__BVID__81").css("display","block")
    $inputPhone.siblings("div").find(".send-button").text("재전송")
})

$verification.on("keydown", function () {
    $(this).siblings("div").find(".send-button").attr("disabled", false)
    $(this).siblings("div").find(".send-button").removeClass("disabled")
})

$verification.siblings("div").find(".send-button").on("click", function () {
    verificationCheck();
})

//가입하기 눌렀을 때 전체 유효성 검사
$btn.on("click", function () {
    $btn.attr("type", "button");
    let gender = false;
    let email = false;
    let pw = false;
    let pwc = false;
    let $agreeCheck = $agree.is(":checked");
    let $agreeError = $agree.siblings("p.error-message");
    let $moreThan14Check = $moreThan14.is(":checked");
    let $moreThan14Error = $moreThan14.siblings("p.error-message");

    if($selectedGender.hasClass("selected")){
        gender = !gender;
    }else{
        $inputGender.addClass("is-invalid");
        $inputGender.siblings(".invalid-feedback").find(".error").css("display", "block");
    }
    email = idCheck() ? !email : email;
    pw = pwCheck() ? !pw : pw;
    pwc = matchPw() ? !pwc : pwc;
    phoneCheck();

    if(!emailDuplicate){
        $inputEmail.addClass("is-invalid")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("display", "block")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").text("중복 확인이 되지 않았습니다.")
        $inputEmail.parent().siblings(".invalid-feedback").find(".error").css("color", "#fa5963")
        $inputEmail.focus();
    }

    $agreeCheck ? $agreeError.css("display", "none") : $agreeError.css("display", "block")
    $moreThan14Check ? $moreThan14Error.css("display", "none") : $moreThan14Error.css("display", "block")


    if(gender && email && pw && pwc && emailDuplicate && verify && $agreeCheck && $moreThan14Check){
        $btn.attr("type", "submit");
    }
})
//비밀번호 표시, 숨김 전환하는 js
$passwordShow.on("click", function () {
    if($(this).text() == "표시"){
        $(this).text("숨김");
        $(this).siblings().attr("type", "text");
    }else{
        $(this).text("표시");
        $(this).siblings().attr("type", "password");
    }
})
//증복확인 버튼 누르면 작동
$duplicationCheckBtn.on("click", function () {
    checkEmailDuplication();
})