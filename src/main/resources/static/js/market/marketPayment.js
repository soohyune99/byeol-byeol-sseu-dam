/* marketPayment.html */


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
function cancelModificationOrderer(){
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

            /*  var guideTextBox = document.getElementById("guide");
              // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
              if(data.autoRoadAddress) {
                  var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                  guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                  guideTextBox.style.display = 'block';

              } else if(data.autoJibunAddress) {
                  var expJibunAddr = data.autoJibunAddress;
                  guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                  guideTextBox.style.display = 'block';
              } else {
                  guideTextBox.innerHTML = '';
                  guideTextBox.style.display = 'none';
              }*/
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
});

/* ================================== MarketPayment ==================================*/
const memberId = 1;
let url = decodeURI(window.location.href).split("=");

let count = url[url.length - 1];    // 주문 수량
let producturl = url[url.length - 2].split("&");
let productId = producturl[producturl.length - 2];    // 상품 번호

getOrderDetailList();

/* 상품 & 회원 조회 */
function getOrderDetailList(){
    orderService.getOrderMember(
        memberId, showOrderMemberDetail
    );

    orderService.getOrderDetail(
        productId, showOrderProductDetail
    );
}

/* 회원 정보 */
function showOrderMemberDetail(member){
    console.log(member.memberName);
    console.log(member.memberEmail);
    console.log(member.memberPhone);
    console.log(member.memberPoint);

    $(".info-member-name").html(member.memberName);
    $(".info-member-email").html(member.memberEmail);
    $(".info-member-phone").html(member.memberPhone);
    $(".my-point").html(member.memberPoint);

}

/* 포인트 사용 유효성 검사 - 전액 사용 */
function useMypoint(){
    let $mypoint = parseInt($(".holding-mypoint > strong").text()); // 현재 회원 포인트
    let $productPrice = parseInt($(".total-price").text());
    let flag = 0;
    $("input._input_point").val($mypoint);  // 포인트 input 태그 안에 자동으로 전액 포인트 입력
    $(".use_point_text").html("포인트 사용");
    $(".use_point_number").html(" - " + $mypoint + "원");  // 전액 포인트 사용
    $(".total-price").html($productPrice - $mypoint + "원");
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

    console.log(allAdd);    // 전체 배송 주소
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
    console.log("text");
    console.log(products);
    console.log(products.productPrice);
    console.log(count);

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
        text += `<span class="text-bold text-14">` + products.productPrice + `</span>`;
        text += `</div>`;
        text += `</div>`;
        text += `</a>`;
        text += `</div>`;
        text += `<div class="im-payment-deliv">`;
        text += `<div>`;
        text += `배송비 <span class="text-bold"> 3,000원 </span>`;
        text += `</div>`;
    $(".order-info").append(text);

    $(".order-product-price").html(products.productPrice * count + "원");    // 상품 가격 * 수량
    $(".total-price").html(products.productPrice * count + 3000 + "원");    // 상품 가격 * 수량
    $(".save-point").html((products.productPrice * count + 3000) * 0.01);   // 포인트 적립
}


//실제 복사하여 사용시에는 모든 주석을 지운 후 사용하세요
BootPay.request({
    price: '1000', //실제 결제되는 가격
    application_id: "[ WEB SDK용 Application ID ]",
    name: '블링블링 마스카라', //결제창에서 보여질 이름
    pg: '',
    method: '', //결제수단, 입력하지 않으면 결제수단 선택부터 화면이 시작합니다.
    show_agree_window: 0, // 부트페이 정보 동의 창 보이기 여부
    items: [
        {
            item_name: '나는 아이템', //상품명
            qty: 1, //수량
            unique: '123', //해당 상품을 구분짓는 primary key
            price: 1000, //상품 단가
            cat1: 'TOP', // 대표 상품의 카테고리 상, 50글자 이내
            cat2: '티셔츠', // 대표 상품의 카테고리 중, 50글자 이내
            cat3: '라운드 티', // 대표상품의 카테고리 하, 50글자 이내
        }
    ],
    user_info: {
        username: '사용자 이름',
        email: '사용자 이메일',
        addr: '사용자 주소',
        phone: '010-1234-4567'
    },
    order_id: '고유order_id_1234', //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
    params: {callback1: '그대로 콜백받을 변수 1', callback2: '그대로 콜백받을 변수 2', customvar1234: '변수명도 마음대로'},
    account_expire_at: '2020-10-25', // 가상계좌 입금기간 제한 ( yyyy-mm-dd 포멧으로 입력해주세요. 가상계좌만 적용됩니다. )
    extra: {
        start_at: '2019-05-10', // 정기 결제 시작일 - 시작일을 지정하지 않으면 그 날 당일로부터 결제가 가능한 Billing key 지급
        end_at: '2022-05-10', // 정기결제 만료일 -  기간 없음 - 무제한
        vbank_result: 1, // 가상계좌 사용시 사용, 가상계좌 결과창을 볼지(1), 말지(0), 미설정시 봄(1)
        quota: '0,2,3', // 결제금액이 5만원 이상시 할부개월 허용범위를 설정할 수 있음, [0(일시불), 2개월, 3개월] 허용, 미설정시 12개월까지 허용,
        theme: 'purple', // [ red, purple(기본), custom ]
        custom_background: '#00a086', // [ theme가 custom 일 때 background 색상 지정 가능 ]
        custom_font_color: '#ffffff' // [ theme가 custom 일 때 font color 색상 지정 가능 ]
    }
}).error(function (data) {
    //결제 진행시 에러가 발생하면 수행됩니다.
    console.log(data);
}).cancel(function (data) {
    //결제가 취소되면 수행됩니다.
    console.log(data);
}).ready(function (data) {
    // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
    console.log(data);
}).confirm(function (data) {
    //결제가 실행되기 전에 수행되며, 주로 재고를 확인하는 로직이 들어갑니다.
    //주의 - 카드 수기결제일 경우 이 부분이 실행되지 않습니다.
    console.log(data);
    var enable = true; // 재고 수량 관리 로직 혹은 다른 처리
    if (enable) {
        BootPay.transactionConfirm(data); // 조건이 맞으면 승인 처리를 한다.
    } else {
        BootPay.removePaymentWindow(); // 조건이 맞지 않으면 결제 창을 닫고 결제를 승인하지 않는다.
    }
}).close(function (data) {
    // 결제창이 닫힐때 수행됩니다. (성공,실패,취소에 상관없이 모두 수행됨)
    console.log(data);
}).done(function (data) {
    //결제가 정상적으로 완료되면 수행됩니다
    //비즈니스 로직을 수행하기 전에 결제 유효성 검증을 하시길 추천합니다.
    console.log(data);
});
