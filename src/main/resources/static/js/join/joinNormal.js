
let emailFilter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
let pwFilter = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,20}$/;

let $inputName = $("#__BVID__75");
let $inputEmail = $("#__BVID__77");
let $inputPw = $("#__BVID__79");
let $btn = $(".btn-primary");
let $agree = $("#agree-terms-checkbox-1667907552644");
let $moreThan14 = $("#more-than-14-checkbox-1667907552646");
let $passwordShowBtn = $(".password-show");

// 이름 유효성 검사
function nameCheck(){
    if (!$inputName.val()) {
        $inputName.siblings(".invalid-feedback").text("이름을 입력해주세요.");
        $inputName.siblings(".invalid-feedback").css("display", "block");
        $inputName.addClass("invalid");
    } else {
        $inputName.siblings(".invalid-feedback").css("display", "none");
        $inputName.removeClass("invalid");
        return true;
    };
}
// 이메일 유효성 검사
function idCheck(){
    if (!$inputEmail.val()) {
        $inputEmail.siblings(".invalid-feedback").text("이메일 주소를 입력해주세요.");
        $inputEmail.siblings(".invalid-feedback").css("display", "block");
        $inputEmail.addClass("invalid");
    } else if (!emailFilter.test($inputEmail.val())) {
        $inputEmail.siblings(".invalid-feedback").text("올바른 이메일 주소를 입력해주세요.");
        $inputEmail.siblings(".invalid-feedback").css("display", "block");
        $inputEmail.addClass("invalid");
    } else {
        $inputEmail.siblings(".invalid-feedback").css("display", "none");
        $inputEmail.removeClass("invalid");
        return true;
    }
    ;
}
//비밀번호 유효성 검사
function pwCheck(){
    if (!$inputPw.val()) {
        $inputPw.parent().siblings(".invalid-feedback").text("비밀번호를 입력해주세요.");
        $inputPw.parent().siblings(".invalid-feedback").css("display", "block");
        $inputPw.addClass("invalid");
    } else if (!pwFilter.test($inputPw.val())) {
        $inputPw.parent().siblings(".invalid-feedback").text("영문+숫자+특수문자 조합 8자리 이상 입력해주세요.");
        $inputPw.parent().siblings(".invalid-feedback").css("display", "block");
        $inputPw.addClass("invalid");
    } else {
        $inputPw.parent().siblings(".invalid-feedback").css("display", "none");
        $inputPw.removeClass("invalid");
        return true;
    }
    ;
}

$inputName.on("blur", function () {
    nameCheck();
})

$inputEmail.on("blur", function () {
    idCheck();
})

$inputPw.on("blur", function () {
    pwCheck();
})
//회원가입 버튼 클릭 시 유효성 검사
$btn.on("click", function () {
    let name = false;
    let email = false;
    let pw = false;
    let $agreeCheck = $agree.is(":checked");
    let $agreeError = $agree.siblings("p.error-message");
    let $moreThan14Check =  $moreThan14.is(":checked");
    let $moreThan14Error =  $moreThan14.siblings("p.error-message");

    $btn.attr("type", "button");

    name = nameCheck() ? !name : name;
    email = idCheck() ? !email : email;
    pw = pwCheck() ? !pw : pw;

    $agreeCheck ? $agreeError.css("display", "none") : $agreeError.css("display", "block");
    $moreThan14Check ? $moreThan14Error.css("display", "none") : $moreThan14Error.css("display", "block");

    if(name && email && pw && $agreeCheck && $moreThan14Check){
        $btn.attr("type", "submit")
    }
});
//비밀번호 표시, 숨김 전환하는 js
$passwordShowBtn.on("click", function () {
    if($passwordShowBtn.text() == "표시"){
        $passwordShowBtn.text("숨김");
        $passwordShowBtn.siblings().attr("type", "text");
    }else{
        $passwordShowBtn.text("표시");
        $passwordShowBtn.siblings().attr("type", "password");
    }
})