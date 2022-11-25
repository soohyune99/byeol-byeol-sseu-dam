
let $abc = $(".Padded-sc-1mbfr4n-0");
let $abc1 = $(".ToastContainer__BottomToastWrapper-sqfjqn-0");
let $abc2 = $(".GlobalNavigationBar__Wrapper-og74wb-0");
let $abc3 = $(".CategoryList__Wrapper-l9zk8m-0");
let $abc4 = $(".Left__LeftPageTitle-sc-12djr3w-0");

//js로 미디어쿼리 적용
window.matchMedia("(max-width: 768px)").addListener(function (e) {
    if (e.matches) {
        $abc.removeClass("KOZEV");
        $abc.addClass("hMNBwb");
        $abc1.removeClass("boEfOZ")
        $abc1.addClass("boEfRG")
        $abc2.removeClass("dPJHWR")
        $abc2.addClass("bHgsOZ")
        $abc3.removeClass("hWBKQy")
        $abc3.addClass("hWBKQE")
        $abc4.removeClass("cqeeCQ")
        $abc4.addClass("RMzhy")
    } else {
        $abc.removeClass("hMNBwb");
        $abc.addClass("KOZEV");
        $abc1.removeClass("boEfRG")
        $abc1.addClass("boEfOZ")
        $abc2.removeClass("bHgsOZ")
        $abc2.addClass("dPJHWR")
        $abc3.removeClass("hWBKQE")
        $abc3.addClass("hWBKQy")
        $abc4.removeClass("RMzhy")
        $abc4.addClass("cqeeCQ")
    }
})
//브라우저 창 크기변화 감지해서 그때마다 getHeight() 실행
$( window ).resize(function() {
    getHeight();
    $(".acd_collapse.collapse.in").attr("style", `height:` + getHeight() + `px`)
});

//outerHeight()로 margin, padding을 포함한 div 전체 height 구하고 거기에 20 더하기
function getHeight(){
    return $(".acd_body").outerHeight() + 20;
}

//아코디언 효과 적용. 하나 열린 상태로 다른 목록 클릭 시 기존거는 닫히고 새로운 게 열림
$(".accordion").on("click", function(){
    let $collapse = $(this).find(".acd_collapse.collapse");

    if(!$collapse.hasClass("in")){
        $collapse.addClass("in")
        $(this).siblings(".accordion").find(".acd_collapse.collapse").removeClass("in")
        $(this).siblings(".accordion").find(".acd_collapse.collapse").attr("style", "height: 1px")
        $collapse.attr("style", `height:` + getHeight() + `px`)
    }else{
        $collapse.removeClass("in");
        $collapse.attr("style", "height: 1px")
    }
});