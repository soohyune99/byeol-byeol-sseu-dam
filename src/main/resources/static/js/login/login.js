
let emailFilter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
let pwFilter = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,20}$/;

let $inputId = $("#memberEmail");
let $inputPw = $("#memberPassword");
let $btn = $(".btn-primary");
let $modal = $(".swal2-container");
let $body = $("body.login");
let $modalConfirm = $modal.find(".swal2-confirm");

function idCheck(){
    if(!$inputId.val()){
        $(".invalid-feedback").text("이메일 주소를 입력해주세요.");
        $(".invalid-feedback").css("display", "block");
        $inputId.addClass("invalid");
    }else if(!emailFilter.test($inputId.val())){
        $(".invalid-feedback").text("올바른 이메일 주소를 입력해주세요.");
        $(".invalid-feedback").css("display", "block");
        $inputId.addClass("invalid");
    }else{
        $(".invalid-feedback").css("display", "none");
        $inputId.removeClass("invalid");
        return true;
    };
}

function pwCheck(){
    if(!$inputPw.val()){
        $(".text-invalid").text("비밀번호를 입력해주세요.");
        $(".text-invalid").css("display", "block");
        $inputPw.addClass("invalid");
    }else if(!pwFilter.test($inputPw.val())){
        $(".text-invalid").text("영문+숫자+특수문자 조합 8자리 이상 입력해주세요.");
        $(".text-invalid").css("display", "block");
        $inputPw.addClass("invalid");
    }else{
        $(".text-invalid").css("display", "none");
        $inputPw.removeClass("invalid");
        return true;
    };
}

// 이메일 유효성 검사하는 js
$inputId.on("blur", function () {idCheck();})

// 비밀번호 유효성 검사하는 js
$inputPw.on("blur", function () {pwCheck();})

// 이메일 로그인 눌렀을 때 유효성 검사하는 js
$btn.on("click",function (e) {
    let email = false;
    let pw = false;

    $btn.attr("type", "button");

    email = idCheck() ? !email : email;
    pw = pwCheck() ? !pw : pw;

    if(email && pw){
        $btn.attr("type", "submit");
    }else{
        $modal.css("display","flex")
        $body.css("overflow", "hidden")
    }
})
// 로그인 실패 모달 끄는 js
$modalConfirm.on("click", function () {
    $modal.css("display","none")
    $body.css("overflow", "unset")
})