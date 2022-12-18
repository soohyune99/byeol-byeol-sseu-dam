/* marketPayment.html */


/* 세션에 있는 멤버 아이디 & 이름 */
const memberId = $("input[name='memberId']").val();
const memberName = $("input[name='memberName']").val();

let $totalAgreeBtn = $("#paymentAllCheck");
let $agreeBtn = $(".agree");

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

// 전체 동의 클릭
$totalAgreeBtn.on("click", function(){
    //각각의 약관의 checked 프로퍼티를 모두 전체동의의 checked 상태로 변경시켜준다.
    // 전체 동의가 true면 나머지 다 true
    $agreeBtn.prop("checked", $(this).is(":checked"));
});

// 각각의 약관 동의 클릭
$agreeBtn.on("click", function(){
    // 각각의 약관의 checked 프로퍼티가 true인 개수를 가져온 뒤
    // 2개 모두 true일 경우 전체 동의도 true이다.
    $totalAgreeBtn.prop("checked", $agreeBtn.filter(":checked").length == $agreeBtn.length);
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
function cancelModificationOrder(){
    text = "";

    text += `<div class="info text-14 text-gray">` + ordererName + `</div>`
    text += `<div class="info text-14 text-gray">` + ordererPhoneNumber + `</div>`
    text += `<div class="info text-14 text-gray">` + ordererEmail + `</div>`
    text += `<a href="javascript:modifyOrdererInfo();" class="btn btn-primary btn-sm-padding text-13 btn_top_right _btn_modify_shipping" role="button">수정</a>`;

    $ordererInfo.html(text);
}

/* ================================== 카카오 주소 api ==================================*/
//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").focus();
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;


            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }
        }
    }).open();
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
    /*  $(".select-control").style.display = 'none';
      $(".modifyInfo").style.display = "display";
      let wirteMsg =  $("input[name='write-msg']").val()
      console.log("직접 쓴 메시지");
      console.log(wirteMsg);*/
});

/* ================================== MarketPayment ==================================*/
let url = decodeURI
(window.location.href).split("=");

let count = url[url.length - 1];    // 주문 수량
let producturl = url[url.length - 2].split("&");
let productId = producturl[producturl.length - 2];    // 상품 번호

getMemberDetailList();
typePayment();

/* 쿼리스트링 가져오는 메소드 */
function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
}
let paymentFlag = searchParam('basketId');

function typePayment(){
    let paymentFlag = searchParam('basketId');
    console.log(paymentFlag);

    if(paymentFlag != '' && paymentFlag != null){          // 장바구니에서 넘어옴
        console.log("if문");
        basketService.buyBasketProduct(
            paymentFlag, showOrderBasketDetail
        )
    }else{
        console.log("else문");
        orderService.getOrderDetail(
            productId, showOrderProductDetail
        );
    }
}

/* 회원 조회 */
function getMemberDetailList(){
    orderService.getOrderMember(
        memberId, showOrderMemberDetail
    );
}

/* 회원 정보 */
function showOrderMemberDetail(member){
    $(".info-member-id").html(member.memberId);
    $(".info-member-name").html(member.memberName);
    $(".info-member-email").html(member.memberEmail);
    $(".info-member-phone").html(member.memberPhone);
    $(".my-point").html(member.memberPoint);
}

/* 포인트 사용 유효성 검사 - 전액 사용 */
function useMypoint(){
    let $mypoint = parseInt($(".holding-mypoint > strong").text()); // 현재 회원 포인트
    let $productPrice = parseInt($(".total-price").text());
    console.log("총 금액");
    console.log($productPrice);
    let flag = 0;
    $("input._input_point").val($mypoint);  // 포인트 input 태그 안에 자동으로 전액 포인트 입력
    $(".use_point_text").html("포인트 사용");
    $(".use_point_number").html(" - " + $mypoint + "원");  // 전액 포인트 사용
    $(".total-price").html(($productPrice - $mypoint)+ "원");
    $(".save-point").html(($productPrice - $mypoint) * 0.01);

    console.log(document.getElementById('sample4_postcode').value); // 우편번호
    console.log(document.getElementById("sample4_roadAddress").value);  // 도로명 주소
    console.log(document.getElementById("sample4_extraAddress").value); // 참고 항목
    console.log(document.getElementById("sample4_jibunAddress").value); // 상세 주소

    let zipcode = document.getElementById('sample4_postcode').value;
    let roadAdd = document.getElementById("sample4_roadAddress").value;
    let detailAdd = document.getElementById("sample4_extraAddress").value;
    let writeAdd = document.getElementById("sample4_jibunAddress").value;
    let allAdd = zipcode + roadAdd + detailAdd + writeAdd;

}

/* 포인트 직접 입력 */
function onblurPoint() {
    let $mypoint = parseInt($(".holding-mypoint > strong").text());
    var blurPoint = document.getElementById('use_point');
    let $productPrice = parseInt($(".total-price").text());

    if(blurPoint.value > $mypoint){
        alert("보유 포인트보다 많습니다. 다시 입력해주세요.");
        $(".use_point_text").html("포인트 사용");
        $(".use_point_number").html(" - " + 0 + "원");
        $(".total-price").html($productPrice - 0 + "원");

    }else{
        $(".use_point_text").html("포인트 사용");
        $(".use_point_number").html(" - " + blurPoint.value + "원");
        $(".total-price").html($productPrice - blurPoint.value + "원");
        $(".save-point").html(($productPrice - blurPoint.value) * 0.01);
    }
}

/* 배송 메시지 선택 */
function chageSelect(){

    let selectList = document.getElementById("category")

    if(selectList.options[selectList.selectedIndex].value == "배송 전에 미리 연락 바랍니다."){
        console.log(selectList.options[selectList.selectedIndex].text);
        console.log(selectList.options[selectList.selectedIndex].value);
    }
    if(selectList.options[selectList.selectedIndex].value == "부재시 경비실에 맡겨주세요."){
        console.log(selectList.options[selectList.selectedIndex].text);
        console.log(selectList.options[selectList.selectedIndex].value);
    }
    if(selectList.options[selectList.selectedIndex].value == "부재시 전화나 문자를 남겨주세요."){
        console.log(selectList.options[selectList.selectedIndex].text);
        console.log(selectList.options[selectList.selectedIndex].value);
    }if(selectList.options[selectList.selectedIndex].value == "custom"){
        let $writeMessage = $("input[name='custom_deliv_memo']").val();
        console.log($writeMessage);
        console.log(selectList.options[selectList.selectedIndex].value);
    }

}


/* 한개의 상품의 주문내역 */
function showOrderProductDetail(products){

    let text = "";

    text += `<div class="shop_item_thumb">`;
    text += `<a href="/bathroom/?idx=24">`;
    text += `<div class="product_img_wrap">`;
    text += `<img src=" ` + products.productFileProfileName + `" alt="주문상품 이미지">`;
    text += `</div>`;
    text += `<div class="product_info_wrap">`;
    text += `<span class="shop_item_title">`+ products.productName + `</span>`;
    text += `<div class="shop_item_opt">`;
    text += `<p>`;
    text += `<em class="list_badge badge_latest">필수</em>`;
    text += `<p>` + count + `</p>`;
    text += `</p>`;
    text += ` </div>`;
    text += `<div class="shop_item_pay">`;
    text += `<span class="text-bold text-14">` + products.productPrice.toLocaleString() + "원" + `</span>`;
    text += `</div>`;
    text += `</div>`;
    text += `</a>`;
    text += `</div>`;
    text += `<div class="im-payment-deliv">`;
    text += `<div>`;
    text += `배송비 <span class="text-bold delivery"> 3,000원 </span>`;
    text += `</div>`;

    $(".order-info").append(text);


    let $productPrice = parseInt($(".total-price").text());
    $(".hidden-productId").html(products.productId);
    $(".hidden-productName").html(products.productName);
    $(".hidden-productPrice").html(products.productPrice.toLocaleString() + "원");
    $(".order-product-price").html((products.productPrice * count).toLocaleString() + "원");    // 상품 가격 * 수량
    $(".total-price").html((products.productPrice * count + 3000));    // 상품 가격 * 수량 + 배송비
    $(".save-point").html((products.productPrice * count + 3000) * 0.01);   // 포인트 적립
    let deliver = $(".delivery").text().split('원')[0].replace(',', '');
    let totalPrice = $(".hidden-productPrice").val();
    let savePoint = $(".save-point").val();
}


/* 장바구니 결제 */
function showOrderBasketDetail(baskets){
    console.log("text");
    console.log(baskets);

    let text = "";
    baskets.forEach(basket => {
        text += `<div class="shop_item_thumb">`;
        text += `<a href="/bathroom/?idx=24">`;
        text += `<div class="product_img_wrap">`;
        text += `<img src=" ` + basket.productFileProfileName + `" alt="주문상품 이미지">`;
        text += `</div>`;
        text += `<div class="product_info_wrap">`;
        text += `<span class="shop_item_title">`+ basket.productName + `</span>`;
        text += `<div class="shop_item_opt">`;
        text += `<p>`;
        text += `<em class="list_badge badge_latest">필수</em>`;
        text += `<p class="basket-count">` + basket.basketCount + `</p>`;
        text += `</p>`;
        text += ` </div>`;
        text += `<div class="shop_item_pay">`;
        text += `<span class="text-bold text-14">` + basket.productPrice.toLocaleString() + "원" + `</span>`;
        text += `<div class="hidden-price" style="display: none;">` + basket.productPrice+ `</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `</a>`;
        text += `</div>`;
    });
    text += `<div class="im-payment-deliv">`;
    text += `<div>`;
    text += `배송비 <span class="text-bold delivery"> 3,000원 </span>`;
    text += `</div>`;

    $(".order-info").html(text);
    console.log(baskets.length);
    let all = 0;
    for(let i = 0; i < baskets.length; i++ ){
        let realprice = $($(".hidden-price")[i]).text();
        console.log(realprice);
        let realcount = $($(".basket-count")[i]).text();
        console.log(realcount);
        all += Number(realprice) * Number(realcount);
    }
    console.log("돈");
    console.log(all);
    $(".hidden-productId").html(baskets.productId);
    $(".hidden-productName").html(baskets.productName);
    // $(".hidden-productPrice").html(baskets.productPrice.toLocaleString() + "원");
    $(".order-product-price").html(all.toLocaleString() + "원");    // 상품 가격 * 수량
    $(".total-price").html(all + 3000);    // 상품 가격 * 수량 + 배송비
    $(".save-point").html((all + 3000) * 0.01);   // 포인트 적립
    let $productPrice = parseInt($(".total-price").text());
    let deliver = $(".delivery").text().split('원')[0].replace(',', '');
    console.log("배달비");
    console.log(deliver);
    let totalPrice = $(".hidden-productPrice").val();
    let savePoint = $(".save-point").val();
    console.log("총 가격");
    console.log(totalPrice);
    console.log("적립 포인트");
    console.log(savePoint);
}



