/* marketBasket.html */

/* 세션에 있는 멤버 아이디 */
/* 세션에 있는 멤버 아이디 & 이름 */
const memberId = $("input[name='memberId']").val();
const memberName = $("input[name='memberName']").val();

let $optionModal = $("#shop_cart_change_layer");
let $modalOpenBtn = $(".btn._modal_open_btn");
let $modalXBtn = $(".btn-cancel._modal_close_btn");

let $selectBox = $(".dropdown-toggle");
let $dropdownMenu = $(".dropdown-menu");

let $totalSelectBtn = $("#_all_check");
let $selectBtn = $(".agree");

let clickFlag = true;

/*$modalOpenBtn.on('click', function(){
    optionModalOpen();
});*/

$modalXBtn.on('click', function(){
    $optionModal.css('display', 'none');
});

$selectBox.on('click', function(){
    selectboxOpen();
});

/* 옵션 선택 박스 열기 */
function selectboxOpen(){
    $dropdownMenu.toggle();
}

// 전체 동의 클릭
$totalSelectBtn.on("click", function(){
    //각각의 약관의 checked 프로퍼티를 모두 전체 동의의 checked 상태로 변경시켜준다.
    // 전체 동의가 true면 나머지 다 true
    $(".agree").prop("checked", $(this).is(":checked"));

    let totalPrice = 0;
    if($('#_all_check').is(':checked')){
        $("._cart_check-count").html($(".agree").length);

        let prices = $(this).closest('table').find('.each-total-hidden-price');
        for(let i = 0; i < prices.length; i++){
            totalPrice += Number($(prices.get(i)).text());
        }
        console.log(totalPrice.toLocaleString());
        $("._cart-real_price").html((totalPrice).toLocaleString() + "원");
        $("._cart_main_total_price").html((totalPrice + 3000).toLocaleString() + "원");
    }else{
        $("._cart_check-count").html(0);
        $("._cart-real_price").html((0) + "원");
        $("._cart_main_total_price").html((3000).toLocaleString() + "원")
    }

});


// 각각의 약관 동의 클릭
$(".startBakset").on("click",".agree" ,function(){
    // 각각의 약관의 checked 프로퍼티가 true인 개수를 가져온 뒤
    // 2개 모두 true일 경우 전체 동의도 true이다.
    $totalSelectBtn.prop("checked", $(".agree").filter(":checked").length == $(".agree").length);

    let $checked = $("input[name='check-agree']:checked");

  /*  if($checked.length < 1){
        alert('삭제할 품목을 선택해 주세요.');
        return false;
    }*/
    var checkList = [];
    let totalPrice = 0;
    $.each($checked, function(k, v){
        checkList.push($(this).val());
        $("._cart_check-count").html($checked.length);
        let price = $(this).closest('tr.content').find('.each-total-hidden-price').text();
        totalPrice += Number(price);
    });
    $("._cart-real_price").html(totalPrice.toLocaleString() + "원");
    $("._cart_main_total_price").html((totalPrice + 3000).toLocaleString() + "원");

});


/* 상품 개수 조절 변수 선언 */
let $totalItemCount = $(".body_font_color_70.itemCount");
let $totalCount = $totalItemCount.text().split('개')[0].split('(')[1];
let $totalPrice = $(".total-price");
let index = 0;

/* 상품 개수 빼기 */
function minusItemCount(index){
    let identifier = ".form-control._order_count_mobile." + index;
    let $itemCount = $(identifier);
    if($itemCount.val() == 1){
        alert("1개 미만은 주문할 수 없습니다.");
        return;
    }else {
        $itemCount.val(parseInt($itemCount.val()) - 1);
        totalItemCount();
    }
}

/* 상품 개수 추가 */
function plusItemCount(index){
    let identifier = ".form-control._order_count_mobile." + index;
    let $itemCount = $(identifier);
    $itemCount.val(parseInt($itemCount.val()) + 1);
    totalItemCount();
}

/* 상품 개수 변경 이벤트 */

$(".goods_payment").on('change', '.form-control._order_count_mobile', function(){
    totalItemCount();
});

$(".form-control._order_count_mobile").on('change', function(){
    totalItemCount();
});

/* 총 상품 개수 */
function totalItemCount(){
    let text = "";
    let total = 0;
    text += "총 상품금액(";
    for(var i = index; i >= 0; i--) {
        let identifier = ".form-control._order_count_mobile." + i;
        let $itemCount = $(identifier);
        total += parseInt($itemCount.val());
    }
    text += total;
    text += "개)";
    $totalItemCount.html(text);
    totalPrice();
}

/* 총 상품 금액 */
function totalPrice(){
    let text = "";
    let total = 0;
    for(var i = index; i >= 0; i--) {
        let itemIdentifier = ".item_price." + i;
        let $itemPrice = $(itemIdentifier).text().split('원')[0].replace(',', '');
        let countIdentifier = ".form-control._order_count_mobile." + i;
        let $itemCount = $(countIdentifier);
        total += (parseInt($itemCount.val()) * parseInt($itemPrice));
    }
    text += total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    text += "원";
    $(".total-price").html(text);
}

/* ================================== MarketBasket ==================================*/

// $totalSelectBtn.click();
showBasket();

$(document).ready(function () {
    $(".agree").prop("checked", $("#_all_check").is(":checked"));

    let totalPrice = 0;
    if($('#_all_check').is(':checked')){
        $("._cart_check-count").html($(".agree").length);

        let prices = $totalSelectBtn.closest('table').find('.each-total-hidden-price');
        for(let i = 0; i < prices.length; i++){
            totalPrice += Number($(prices.get(i)).text());
        }
        console.log(totalPrice.toLocaleString());
        $("._cart-real_price").html((totalPrice).toLocaleString() + "원");
        $("._cart_main_total_price").html((totalPrice + 3000).toLocaleString() + "원");
    }else{
        $("._cart_check-count").html(0);
        $("._cart-real_price").html((0) + "원");
        $("._cart_main_total_price").html((3000).toLocaleString() + "원")
    }

})

function showBasket(){
    basketService.getBasketList(
        memberId, showBasketList
    )
}

/* 장바구니 조회 */
function showBasketList(baskets){
    console.log(baskets);

    let text = "";
    let check = 0;
    let checkIndex = 0;
    $("._cart_item_count").html(baskets.length);
    baskets.forEach(basket => {
        text += `<tr class="content" style="overflow: visible;">`;
        text += `<td class="slt img">`;
        text += `<div class="im-flex">`;
        text += `<div class="im-cart-checkbox-wrap">`;
        text += `<div class="checkbox checkbox-styled no-margin">`;
        text +=`<label class="secondChecked" style="padding-left: 6%;">`;
        text += `<input type="checkbox" name="check-agree" class="agree" value="` + basket.basketId + `">`;
        text += `<input type="hidden" name="checkList" value="` + basket.basketId + `">`;
        text += `</label>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div class="im-cart-img-wrap im-grow im-xs-flex im-xs-flex-col">`;
        text += `<a class="cart-item-wrap" href="/market/` + basket.productId + `">`;
        text += `<div class="cart-item-img">`;
        text += `<img src="` + basket.productFileProfileName + `" alt="cart item">`;
        text += `</div>`;
        text += `<p class="cart-item-title text-15" id="shop_cart_title_i20221117b8f210ee88cd6">` + basket.productName + `</p>`;
        text += `</a>`;
        text += `<span class="cart-item-remove">`;
        text += `<i class="btl bt-times" aria-hidden="true" id="` + basket.basketId + `"></i>`;
        text += `</span>`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `</td>`;
        text += `<td class="amount td-blocked hidden-xs">`;
        text += `<div class="text-13 title text-center text-15">` + basket.basketCount +`</div>`;
        text += `<div class="text-center margin-top-xl no-margin-bottom">`;
        text += `<span class="cart-btn-tools">`;
        text += `<a href="javascript:optionModalOpen(` + basket.basketId + `)"   class="change-btn btn _modal_open_btn no-margin-bottom">수량 변경</a>`;
        text += `</span>`;
        text += `</div>`;
        text += `</td>`;
        text += `<td class="amount td-blocked hidden-lg hidden-md hidden-sm no-margin-bottom">`;
        text += `<div class="im-price-result im-flex im-justify-between">`;
        text += `<div class="title"> 주문금액 </div>`;
        text += `<div class="cont blocked text-20 text-bold">` + basket.productPrice.toLocaleString() + "원" +`</div>`;
        text += `</div>`;
        text += `<div class="im-flex im-justify-between margin-bottom-xl">`;
        text += `<div class="title">`;
        text += `<span>상품금액( <em class="hidden-lg hidden-md ">총</em>1개) </span>`;
        text += `</div>`;
        text += `<div class="cont blocked">` + basket.productPrice + `</div>`;
        text += `</div>`;
        text += `<div>`;
        text += `<div class="im-flex im-justify-between margin-bottom-xl">`;
        text += `<span class="title">배송비</span>`;
        text += `<div class="cont text-12 text-right">`;
        text += `<span>3,000원</span>`;
        text += `<a href="javascript:;" class="html-popover deliv_price_info" data-toggle="popover" tabindex="0" data-trigger="focus" title="" data-content="<div class=&quot;&quot;><label class=&quot;text-bold text-13&quot;>배송비 안내</label><div class=&quot;table-responsive&quot;><table class=&quot;table no-margin table-no-padding-x&quot;><caption class=&quot;text-bold no-padding-top&quot;>조건부 무료배송</caption><tr><td colspan=&quot;2&quot;><p>기본 배송비 3,000원<br>50,000원 이상 구매 시 무료배송</p></td></tr></table></div></div>" data-placement="auto" data-original-title="">`;
        text += `<i class="btm bt-question-circle opacity-60 text-13" aria-hidden="true"></i>`;
        text += `<span class="sr-only">배송안내 툴팁</span>`;
        text += `</a>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div class="im-flex im-justify-between margin-bottom-xl">`;
        text += `<span class="title">배송수단</span>`;
        text += `<div class="cont text-13 text-right">`;
        text += `<span class="margin-bottom-lg">택배</span>`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `</td>`;
        text += `<td class="price td-blocked text-center hidden-xs">`;
        text += `<span class="each-total-price text-bold text-20">` + (basket.productPrice * basket.basketCount).toLocaleString() + "원" +`</span>`;
        text += `<div class="each-total-hidden-price text-bold text-20" style="display:none;">` + (basket.productPrice * basket.basketCount) +`</div>`;
        text += `<p class="margin-top-xl no-margin-bottom">`;
        text += `<span class="cart-btn-tools">`;
        text += `<a href="javascript:;" id="` + basket.productId + `" class="pay-one-btn hidden-xs btn-primary no-margin-bottom">바로구매</a>`;
        text += `</span>`;
        text += `</p>`;
        text += `</td>`;
        if(check == 0){
            text += `<td class="hidden-xs td-blocked text-center row-span" rowspan=" ` + baskets.length + `">`;
            text += `<div class="cont text-15 text-center im-flex im-justify-center im-items-center margin-bottom-lg">`;
            text += `<span class="text-bold">`;
            text += `<span>3,000원</span>`;
            text += `</span>`;
            text += `<a href="javascript:;" class="html-popover deliv_price_info margin-left-lg" data-toggle="popover" tabindex="0" data-trigger="focus" title="" data-content="<div class=&quot;&quot;><label class=&quot;text-bold text-15&quot;>배송비 안내</label><div class=&quot;table-responsive&quot;><table class=&quot;table no-margin&quot;><caption class=&quot;text-bold no-padding-top&quot;>조건부 무료배송</caption><tr><td colspan=&quot;2&quot;><p>기본 배송비 3,000원<br>50,000원 이상 구매 시 무료배송</p></td></tr></table></div></div>" data-placement="auto" data-original-title="">`;
            text += `<i class="btm bt-question-circle opacity-60 text-13" aria-hidden="true"></i>`;
            text += `<span class="sr-only">배송안내 툴팁</span>`;
            text += `</a>`;
            text += `</div>`;
            text += `<div class="cont text-13 text-center opacity-60">`;
            text += `<span class="margin-bottom-lg">택배</span>`;
            text += `</div>`;
            text += `</td>`;
            text += `<td class="td-blocked hidden-lg hidden-md hidden-sm" style="margin-top: 6px;">`;
            text += `<div class="im-flex cart-btn-tools">`;
            text += `<a href="javascript:;" class="btn btn-cart-option full-width margin-right-lg _modal_open_btn">수량 변경</a>`;
            text += `<a class="btn btn-cart-order full-width btn-primary" href="javascript:;">바로구매</a>`;
            text += `</div>`;
            text += `</td>`;
            check++;
            $(".item_price").html(basket.productPrice);
        }
        text += `</tr>`;
    });
    $(".startBakset").html(text);
    $totalSelectBtn.click();
}



/* 장바구니가 비어 있을 때 */
/*function emptyBasket(){
    let text = "";

    text += `<tr>`;
    text += `<td colspan="4">`;
    text += `<i class="btl bt-shopping-cart bt-3x"></i>`;
    text += `<div class="body_font_color_40" style="font-size:14px;">장바구니가 비어있습니다.</div>`;
    text += `</td>`;
    text += `</tr>`;

    $(".cart_empty").append(text);

}*/

/* 수량 변경 클릭 시 모달에 정보 조회 */
function optionModalOpen(basketId){
    $optionModal.css('display', 'block');

    basketService.getBasket(
        basketId, showBasketModal
    )
}

/* 옵션 변경 모달 x 버튼으로 닫기 */
function optionModalClose(){
    $optionModal.css('display', 'none');
}

function showBasketModal(basket){
    let text = "";

    $(".basket-img").attr('src', basket.productFileProfileName);
    $(".name").html(basket.productName);
    $(".hidden-basket-id").html(basket.basketId);
    $(".hidden-product-id").html(basket.productId);
    $(".text-brand-price").html(basket.productPrice);
    $(".product-price").html(basket.productPrice);
    $(".total-price").html(basket.productPrice);

};

/* 옵션 변경 버튼 누르면 update */
function optionChangeModal(){
    let basketCount = $("input[name='changeCount']").val();
    let basketId = $("input[name='hidden-basket-id']").text();

    console.log($("input[name='hidden-basket-id']").text());
    console.log($("input[name='changeCount']").val());

    basketService.updateBasketCount(
        basketId, basketCount, afterUpdateBasketModal
    );

}

function afterUpdateBasketModal(){
    $optionModal.css('display', 'none');
    showBasket();
}


/* 선택 상품 삭제 */
function checkedProductDelete() {
    let $checked = $("input[name='check-agree']:checked");

    if($checked.length < 1){
        alert('삭제할 품목을 선택해 주세요.');
        return false;
    }
    var checkList = [];

    $.each($checked, function(k, v){
        checkList.push($(this).val());
    });
    let basketId = checkList.join(',');

    basketService.deleteBasket(
        basketId, showBasket
    )
}


/* x 버튼 누르면 삭제 */
$(".startBakset").on("click", ".bt-times", function(){
    let basketId = $(this).attr("id");
    basketService.deleteBasket(
        basketId, showBasket
    )
});





/* ================================== MarketPayment ==================================*/

/* 개별 구매*/
$(".startBakset").on("click", ".pay-one-btn", function(){
    let productId = $(this).attr("id");
    console.log(productId);
    let $productCount = $("input[name='changeCount']").val();
    console.log($productCount);

    location.href ='/market/payment?productId='+ productId + '&count='+ $productCount;

});

/* 선택적 구매 */
function checkedProductBuy() {
    let formData = new FormData();
    let $checked = $("input[name='check-agree']:checked");
    let $check = $("input[name='check-agree']");
    console.log($checked);

    if($checked.length < 1){
        alert('구매할 품목을 선택해 주세요.');
        return false;
    }
    // var checkList = [];
    var checkList = [];

    $.each($checked, function(k, v){
        checkList.push($(this).val());
    });
    let basketId = checkList.join(',');

    location.href= '/market/basket/payment?basketId='+ basketId;

}
/*

function example(test) {
    location.href = '/ordering/payment/' + test;
}*/

/*function example(baskets) {
    let basketIdAr = [];
    let formData = new FormData();
    baskets.forEach(basket => {
        basketIdAr.push(basket.basketId)
    });
    formData.append("basketDTO", basketIdAr);
    fetch("/market/basket/payment", {
        method: "post",
        cache:"no-cache",
        data: formData
        // body: new URLSearchParams({
        //     basketId: baskets
        // })
    })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
        })
}*/

/*
/!* 쿼리스트링 가져오는 메소드 *!/
function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
}

function typePayment(){
    let paymentFlag = searchParam('basketId');


    if(paymentFlag != ''){          // 장바구니에서 넘어옴
        basketService.buyBasketProduct(
            paymentFlag, showOrderBasketDetail
        )
    }else{
        orderService.payment(
            products, memberId
        )
    }
}
*/

/* basketService.BuyBasketProduct(
       basketId, example
   )*/

/* $.each($checked, function(index){
     console.log("formdata");
     console.log(index);
     console.log($checked)
     console.log( $checked[index]);
     console.log( $($checked[index]).val());

     formData.append("basketId", $($checked[index]).val());
     // checkList.push($(this).val());
 });
 // let basketId = checkList.join(',');
 console.log("append 완료");
 console.log(formData.getAll("basketId"));
 basketService.BuyBasketProduct(
     formData, example
 )*/