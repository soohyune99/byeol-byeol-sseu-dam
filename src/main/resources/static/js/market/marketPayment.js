/* marketPayment.html */


let $totalAgreeBtn = $("#paymentAllCheck");
let $agreeBtn = $(".checkbox-styled ._agree");
let agreeCheck = -1;

let text = "";
let $ordererInfo = $(".ordererInfo");
let ordererName = $ordererInfo.children().eq(0).text();
let ordererPhoneNumber = $ordererInfo.children().eq(1).text();
let ordererEmail = $ordererInfo.children().eq(2).text();

let $shippingInfo = $("._deliv_preview_wrap");
let shippingName = $("._shipping_preview_wrap").children().eq(0).text();
let shippingPhoneNumber = $("._shipping_preview_wrap").children().eq(1).text();
let shippingAddress = $("._shipping_preview_wrap").children().eq(2).children().eq(0).text();
let shippingAddressDetail = $("._shipping_preview_wrap").children().eq(2).children().eq(1).text();
let shippingZipCode = $("._shipping_preview_wrap").children().eq(2).children().eq(2).text();


/* 전체 동의 체크박스 */
$totalAgreeBtn.on('click', function(){
    /* if(agreeCheck < 0){
        $totalAgreeBtn.attr('checked', 'true');
        console.log($totalAgreeBtn);
        console.log($totalAgreeBtn.is('checked'));
    }else {
        $totalAgreeBtn.prop('checked', 'false');
    }
    $agreeBtn.prop('checked', $totalAgreeBtn.is('checked'));

    agreeCheck *= -1; */
});


/* 주문자 정보 수정하기 */
function modifyOrdererInfo(){
    text = "";

    text += `<input type="text" class="modifyInfo text-14 text-gray" value="` + ordererName + `">`;
    text += `<input type="text" class="modifyInfo text-14 text-gray" value="` + ordererPhoneNumber + `">`;
    text += `<input type="text" class="modifyInfo text-14 text-gray" value="` + ordererEmail + `">`;
    text += `<a href="javascript:modifyOKOrdererInfo();" class="btn btn-primary btn-sm-padding text-13 btn_top_right _btn_modify_shipping" role="button">완료</a>`;
    text += `<a href="javascript:cancelModificationOrderer();" class="cancelModifyBtn btn btn-sm-padding text-13 btn_top_right _btn_modify_shipping" role="button">취소</a>`

    $ordererInfo.html(text);
}


/* 주문자 정보 수정 완료 */
function modifyOKOrdererInfo(){
    let modifiedName = $ordererInfo.children().eq(0).val();
    let modifiedPhoneNumber = $ordererInfo.children().eq(1).val();
    let modifiedEmail = $ordererInfo.children().eq(2).val();

    if(!modifiedName || !modifiedPhoneNumber || !modifiedEmail) {
        alert("입력하지 않은 정보가 있습니다.");
        return;
    }

    text = "";

    text += `<div class="info text-14 text-gray">` + modifiedName + `</div>`;
    text += `<div class="info text-14 text-gray">` + modifiedPhoneNumber + `</div>`;
    text += `<div class="info text-14 text-gray">` + modifiedEmail + `</div>`;
    text += `<a href="javascript:modifyOrdererInfo();" class="btn btn-primary btn-sm-padding text-13 btn_top_right _btn_modify_shipping" role="button">수정</a>`;

    ordererName = modifiedName;
    ordererPhoneNumber = modifiedPhoneNumber;
    ordererEmail = modifiedEmail;

    $ordererInfo.html(text);
}


/* 주문자 정보 수정 취소 */
function cancelModificationOrderer(){
    text = "";

    text += `<div class="info text-14 text-gray">` + ordererName + `</div>`
    text += `<div class="info text-14 text-gray">` + ordererPhoneNumber + `</div>`
    text += `<div class="info text-14 text-gray">` + ordererEmail + `</div>`
    text += `<a href="javascript:modifyOrdererInfo();" class="btn btn-primary btn-sm-padding text-13 btn_top_right _btn_modify_shipping" role="button">수정</a>`;

    $ordererInfo.html(text);
}


/* 배송 정보 수정 */
function modifyShippingInfo(){
    text = "";

    text += `<div class="_shipping_preview_wrap">`;
    text += `<input type="text" class="modifyInfo text-14 margin-bottom-lg text-gray" value="` + shippingName + `">`;
    text += `<input type="text" class="modifyInfo text-14 margin-bottom-lg text-gray" value="` + shippingPhoneNumber + `">`;
    text += `<div class="text-14 margin-bottom-lg text-gray">`
    text += `<input type="text" class="modifyInfo no-margin" value="` + shippingAddress + `">`;
    text += `<input type="text" class="modifyInfo no-margin" value="` + shippingAddressDetail + `">`;
    text += `<input type="text" class="modifyInfo no-margin" value="` + shippingZipCode + `">`;
    text += `</div></div>`;
    text += `<a href="javascript:modifyOkShippingInfo();" class="btn btn-primary btn-sm-padding text-13 btn_top_right _btn_modify_shipping" role="button">완료</a>`;
    text += `<a href="javascript:cancelModificationShipping();" class="cancelModifyBtn btn btn-primary btn-sm-padding text-13 btn_top_right _btn_modify_shipping" role="button">취소</a>`;

    $shippingInfo.html(text);
}


/* 배송 정보 수정완료 */
function modifyOkShippingInfo(){
    let modifiedshppingName = $("._shipping_preview_wrap").children().eq(0).val();
    let modifiedshippingPhoneNumber = $("._shipping_preview_wrap").children().eq(1).val();
    let modifiedAddress = $("._shipping_preview_wrap").children().eq(2).children().eq(0).val();
    let modifiedAddressDetail = $("._shipping_preview_wrap").children().eq(2).children().eq(1).val();
    let modifiedZipCode = $("._shipping_preview_wrap").children().eq(2).children().eq(2).val();

    text = "";

    text += `<div class="_shipping_preview_wrap">`;
    text += `<div class="text-14 margin-bottom-lg text-gray">` + modifiedshppingName + `</div>`;
    text += `<div class="text-14 margin-bottom-lg text-gray">` + modifiedshippingPhoneNumber + `</div>`;
    text += `<div class="text-14 margin-bottom-lg text-gray"">`;
    text += `<p class="no-margin">` + modifiedAddress + `</p>`;
    text += `<p class="no-margin">` + modifiedAddressDetail + `</p>`;
    text += `<p class="no-margin">` + modifiedZipCode + `</p>`;
    text += `</div></div>`;
    text += `<a href="javascript:modifyShippingInfo();" class="btn btn-primary btn-sm-padding text-13 btn_top_right _btn_modify_shipping"
    role="button">수정</a>`;

    shippingName = modifiedshppingName;
    shippingPhoneNumber = modifiedshippingPhoneNumber;
    shippingAddress = modifiedAddress;
    shippingAddressDetail = modifiedAddressDetail;
    shippingZipCode = modifiedZipCode;

    $shippingInfo.html(text);
}


/* 배송 정보 수정 취소 */
function cancelModificationShipping(){
    text = "";

    text += `<div class="_shipping_preview_wrap">`;
    text += `<div class="text-14 margin-bottom-lg text-gray">` + shippingName + `</div>`;
    text += `<div class="text-14 margin-bottom-lg text-gray">` + shippingPhoneNumber + `</div>`;
    text += `<div class="text-14 margin-bottom-lg text-gray"">`;
    text += `<p class="no-margin">` + shippingAddress + `</p>`;
    text += `<p class="no-margin">` + shippingAddressDetail + `</p>`;
    text += `<p class="no-margin">` + shippingZipCode + `</p>`;
    text += `</div></div>`;
    text += `<a href="javascript:modifyShippingInfo();" class="btn btn-primary btn-sm-padding text-13 btn_top_right _btn_modify_shipping"
    role="button">수정</a>`;

    $shippingInfo.html(text);
}


/* 배송메모 직접 입력 시 input 태그 변환 */
$("select[name='deliv_memo']").on('click', function(){
    if($(this).val() != 'custom'){ return; }

    text = "";
    text += `<input type="text" class="modifyInfo text-14 margin-bottom-lg text-gray" placeholder="직접 입력">`;

    $(".memo-select-wrap").html(text);
});


/* 포인트 사용 유효성검사 */
function useMypoint(){
    let $mypoint = parseInt($(".holding-mypoint > strong").text());
    let $inputpoint = parseInt($("input._input_point").val());

    $("input._input_point").val($mypoint);
}

// 이용 약관
const $all = $("#paymentAllCheck");
const $terms = $(".terms");

// 전체 동의 클릭
$all.on("click", function(){
    //각각의 약관의 checked 프로퍼티를 모두 전체동의의 checked 상태로 변경시켜준다.
    // 전체 동의가 true면 나머지 다 true
    $terms.prop("checked", $(this).is(":checked"));
});

// 각각의 약관 동의 클릭
$terms.on("click", function(){
    // 각각의 약관의 checked 프로퍼티가 true인 개수를 가져온 뒤
    // 2개 모두 true일 경우 전체 동의도 true이다.
    $all.prop("checked", $terms.filter(":checked").length == $terms.length);
});


/* ================================== MarketPayment ==================================*/
const memberId = 1;
let url = decodeURI(window.location.href).split("=");

let count = url[url.length - 1];    // 주문 수량
let product = url[url.length - 2].split("&");
let productId = product[product.length - 2];    // 상품 번호

getOrderDetailList();

function getOrderDetailList(){
    orderService.getOrderMember(
        memberId, showOrderMemberDetail
    );

    orderService.getOrderDetail(
        productId, showOrderProductDetail
    );
}

function showOrderMemberDetail(member){
    $("info_name").html(member.memberName);
    $("info-email").html(member.memberEmail);
}

function showOrderProductDetail(products){
    let text = "";

    products.forEach(product => {
        text += `<a href="/bathroom/?idx=24" target="_blank">`;
        text += `<div class="product_img_wrap">`;
        text += `<img src="` + product.productFileProfileName + `" alt="주문상품 이미지">`;
        text += `</div>`;
        text += `<div class="product_info_wrap">`;
        text += `<span class="shop_item_title"></span>`;
        text += `<div class="shop_item_opt">`;
        text += `<p>`;
        text += `<em class="list_badge badge_latest">필수</em>`;
        text += `<p>` + count + `</p>`;
        text += `</p>`;
        text += ` </div>`;
        text += `<div class="shop_item_pay">`;
        text += `<span class="text-bold text-14">` + product.productPrice + `</span>`;
        text += `</div>`;
        text += `</div>`;
        text += `</a>`;
    });

    $("shop_item_thumb").append(text);

}


