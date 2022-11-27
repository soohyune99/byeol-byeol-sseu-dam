
let emailFilter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
let pwFilter = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,20}$/;

let $inputName = $("#__BVID__75");
let $inputEmail = $("#__BVID__77");
let $inputPw = $("#__BVID__79");
let $pwChecking = $("#pwChecking");
let $btn = $(".btn-primary");
let $agree = $("#agree-terms-checkbox-1667907552644");
let $moreThan14 = $("#more-than-14-checkbox-1667907552646");
let $passwordShowBtn = $(".password-show");
let $duplicationCheckBtn = $(".email-duplicate");
let emailDuplicate = false;

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
        $inputEmail.parent().siblings(".invalid-feedback").text("이메일 주소를 입력해주세요.");
        $inputEmail.parent().siblings(".invalid-feedback").css("display", "block");
        $inputEmail.parent().siblings(".invalid-feedback").css("color","#fa5963");
        $inputEmail.addClass("invalid");
        $duplicationCheckBtn.attr("disabled", true)
    } else if (!emailFilter.test($inputEmail.val())) {
        $inputEmail.parent().siblings(".invalid-feedback").text("올바른 이메일 주소를 입력해주세요.");
        $inputEmail.parent().siblings(".invalid-feedback").css("display", "block");
        $inputEmail.parent().siblings(".invalid-feedback").css("color","#fa5963");
        $inputEmail.addClass("invalid");
        $duplicationCheckBtn.attr("disabled", true)
    } else {
        $inputEmail.parent().siblings(".invalid-feedback").css("display", "none");
        $inputEmail.removeClass("invalid");
        $duplicationCheckBtn.attr("disabled", false)
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

function matchPw(){
    if(!$pwChecking.val() || $pwChecking.val() != $inputPw.val()){
        $pwChecking.parent().siblings(".invalid-feedback").css("display", "block");
        $pwChecking.addClass("invalid");
    }else{
        $pwChecking.parent().siblings(".invalid-feedback").css("display", "none");
        $pwChecking.removeClass("invalid");
        return true;
    }
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

$pwChecking.on("blur", function () {
    matchPw();
})

//회원가입 버튼 클릭 시 유효성 검사
$btn.on("click", function () {
    let name = false;
    let email = false;
    let pw = false;
    let pwc = false;

    let $agreeCheck = $agree.is(":checked");
    let $agreeError = $agree.siblings("p.error-message");
    let $moreThan14Check =  $moreThan14.is(":checked");
    let $moreThan14Error =  $moreThan14.siblings("p.error-message");
    $btn.attr("type", "button");

    name = nameCheck() ? !name : name;
    email = idCheck() ? !email : email;
    pw = pwCheck() ? !pw : pw;
    pwc = matchPw() ? !pwc : pwc;

    if(!emailDuplicate) {
        $inputEmail.parent().siblings(".invalid-feedback").text("중복 확인이 되지 않았습니다.");
        $inputEmail.parent().siblings(".invalid-feedback").css("display", "block");
        $inputEmail.parent().siblings(".invalid-feedback").css("color", "#fa5963");
        $inputEmail.addClass("invalid");
        $inputEmail.focus();
    }

    $agreeCheck ? $agreeError.css("display", "none") : $agreeError.css("display", "block");
    $moreThan14Check ? $moreThan14Error.css("display", "none") : $moreThan14Error.css("display", "block");

    if(name && email && pw && pwc && emailDuplicate && $agreeCheck && $moreThan14Check){
        $btn.attr("type", "submit")
    }
});
//비밀번호 표시, 숨김 전환하는 js
$passwordShowBtn.on("click", function () {
    if($(this).text() == "표시"){
        $(this).text("숨김");
        $(this).siblings().attr("type", "text");
    }else{
        $(this).text("표시");
        $(this).siblings().attr("type", "password");
    }
})
//이메일 중복검사하는 js
function checkEmailDuplication() {
    //중복검사 예시
    if ($inputEmail.val() == "hds1234@gmail.com") {
        $inputEmail.parent().siblings(".invalid-feedback").text("중복된 이메일 주소입니다.");
        $inputEmail.parent().siblings(".invalid-feedback").css("display", "block");
        $inputEmail.parent().siblings(".invalid-feedback").css("color", "#fa5963");
        $inputEmail.addClass("invalid");
        emailDuplicate = false;
    }else {
        $inputEmail.parent().siblings(".invalid-feedback").text("사용 가능한 이메일 주소입니다.");
        $inputEmail.parent().siblings(".invalid-feedback").css("display", "block");
        $inputEmail.parent().siblings(".invalid-feedback").css("color", "#00c7ae");
        $inputEmail.removeClass("invalid");
        emailDuplicate = true;

        // $.ajax({
        //     success: function (condition) {
        //         if(condition){
        //             $inputEmail.parent().siblings(".invalid-feedback").text("중복된 이메일 주소입니다.");
        //             $inputEmail.parent().siblings(".invalid-feedback").css("display", "block");
        //             $inputEmail.addClass("invalid");
        //         }else{
        //             $inputEmail.parent().siblings(".invalid-feedback").text("사용가능한 이메일 주소입니다..");
        //             $inputEmail.parent().siblings(".invalid-feedback").css("display", "block");
        //             $inputEmail.removeClass("invalid");
        //         }
        //     }
        // })
    }
}

$duplicationCheckBtn.on("click",function () {
    checkEmailDuplication();
})
