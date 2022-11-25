/* marketBasket.html */

let $optionModal = $("#shop_cart_change_layer");
let $modalOpenBtn = $(".btn._modal_open_btn");
let $modalXBtn = $(".btn-cancel._modal_close_btn");

let $selectBox = $(".dropdown-toggle");
let $dropdownMenu = $(".dropdown-menu");


$modalOpenBtn.on('click', function(){
    optionModalOpen();
});

$modalXBtn.on('click', function(){
    $optionModal.css('display', 'none');
});

$selectBox.on('click', function(){
    selectboxOpen();
});


/* 옵션 변경 모달 열기 */
function optionModalOpen(){
    $optionModal.css('display', 'block');
}

/* 옵션 변경 모달 x버튼으로 닫기 */
function optionModalClose(){
    $optionModal.css('display', 'none');
}

/* 옵션 선택박스 열기 */
function selectboxOpen(){
    $dropdownMenu.toggle();
}












