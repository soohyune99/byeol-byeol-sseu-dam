
let emailFilter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
let $inputId = $("#memberEmail");
let $btn = $(".btn-primary");

successJoin();

/* 이메일 유효성 검사 */
function idCheck(){
    if(!$inputId.val()){
        $(".validate").css("display", "block");
        $inputId.addClass("error");
        return;
    }else if(!emailFilter.test($inputId.val())){
        $(".validate").text("올바른 이메일 주소를 입력해주세요");
        $(".validate").css("display", "block");
        $inputId.addClass("error");
        return;
    }else{
        $(".validate").css("display", "none");
        $inputId.removeClass("error");
        return true;
    }
}

$inputId.on("blur", function () {
    idCheck();
})

/* 이메일 전송하기 버튼 눌렀을 때 유효성 검사 */
$btn.on("click",function () {
    $btn.attr("type", "button");
    idCheck() ? $btn.attr("type", "submit") : false;
})

/* 임시비밀번호 이메일 전송 성공 여부 판단 */
function successJoin(){
    let sendFlag = searchParam('pw');

    if(sendFlag == 'false'){
        // alert("유효하지 않는 이메일입니다.");
        $(".swal2-container").show();
    }
}

/* 쿼리스트링 가져오는 메소드 */
function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
};

function closeModal(){
    $(".swal2-container").hide();
}