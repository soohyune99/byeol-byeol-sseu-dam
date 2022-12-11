/* mypageOrderDetail.html */

const memberId = 1;
let url = decodeURI(window.location.href).split("/");
let orderId = url[url.length - 1];
let $cancelModal = $("#cocoaModal");

getMyOrderDetail();
getMemberInfo();

/* 주문취소 모달 열기 */
function cancelModalOpen(orderId){
    $cancelModal.css('display', 'block');

    $(".order-cancel-btn").attr('href', 'javascript:cancelMyOrder(' + orderId + ');')

    mypageService.getMyOrder(
        orderId, getMyOrderModal
    );
}

/* 주문취소하기 */
function cancelMyOrder(orderId){
    $cancelModal.css('display', 'none');

    mypageService.cancelMyOrder(
        orderId, getMyOrderDetail
    );
}

/* x버튼으로 주문취소 모달 닫기 */
function cancelModalClose(){
    $cancelModal.css('display', 'none');
}

/* ==================================== memberInfo ==================================== */

/* 기본 회원 정보 조회 */
function getMemberInfo(){
    mypageService.getMyInfo(
        memberId, showMemberInfo
    )
}

function showMemberInfo(member){
    $(".mypage-memberProfileName").attr('src', member.memberProfileName);
    $(".mypage-memberName").html(member.memberName);
    $(".mypage-memberEmail").html(member.memberEmail);
    $(".mypage-memberType").html(member.memberCategory);
    $(".mypage-memberPoint").html(member.memberPoint);
}

/* 주문내역 상세보기 조회 */
function getMyOrderDetail(){
    mypageService.getMyOrder(
        orderId, showMyOrderDetail
    )
}

function showMyOrderDetail(myorder){
    let text = "";
    let totalPrice = 0;

    myorder.orderDetails.forEach(orderDetail => {
        totalPrice += orderDetail.productPrice * orderDetail.orderDetailCount;

        text += `<tr class="im-body-line-height">`;
        text += `<td style="width: 60%;">`;
        text += `<div class="im-flex">`;
        text += `<div class="margin-right-xxl">`;
        text += `<a href="/bathroom/?idx=24" target="_blank">`;
        text += `<img alt="상품 이미지" src="https://cdn.imweb.me/thumbnail/20220117/6a329ba1bf9e7.jpg">`;
        text += `</a>`;
        text += `</div>`;
        text += `<div class="full-width">`;
        text += `<a href="/bathroom/?idx=24" target="_blank">`;
        text += `<div class="im-body-size myorder-productCategory">[` + orderDetail.productCategory + `]</div>`;
        text += `<div class="im-body-size-90 opacity-70 myorder-productName">` + orderDetail.productName + `</div>`;
        text += `<div class="im-body-size-90 myorder-productPrice">` + orderDetail.productPrice + `원</div>`;
        text += `</a>`;
        text += `<div class="im-status-wrap im-xs-flex-col hidden-lg hidden-md hidden-sm">`;
        text += `<div class="text-bold im-body-size text-default opacity-70 myorder-orderStatus">` + myorder.orderStatus + `</div>`;
        text += `<div class="im-inline-flex im-flex-col-reverse im-xs-flex im-xs-flex-row-reverse im-xs-flex-wrap-reverse cart-btn-tools _btn_tool_row" style="width: 24px;">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `</td>`;
        text += `<td class="im-deliv-price hidden-xs im-body-size-95 myorder-orderDetailCount" rowspan="1">` + orderDetail.orderDetailCount + `</td>`;
        text += `<td class="hidden-xs">`;
        text += `<div class="im-flex im-justify-between im-items-center im-xs-flex-col">`;
        if(myorder.orderStatus == '주문완료'){
            text += `<div class="text-bold im-body-size text-default opacity-70 myorder-orderStatus" style="margin: 0 auto; !important;">`;
            text += `<a class="btn-order-cancel im-flex-1" href="javascript:cancelModalOpen(` + myorder.orderId + `)">주문취소</a>`;
            text += `</div>`;
        } else {
            text += `<div class="text-bold im-body-size text-default opacity-70 myorder-orderStatus" style="margin: 0 auto;">` + myorder.orderStatus + `</div>`;
        }
        text += `<div class="im-inline-flex im-flex-col-reverse im-xs-flex im-xs-flex-row-reverse im-xs-flex-wrap-reverse cart-btn-tools _btn_tool_row" style="width: 24px;">`;
        text += `</div>`;
        text += `</div>`;
        text += `</td>`;
        text += `</tr>`;
    });

    $(".order-detail-list").html(text);

    $(".myorder-date").html(myorder.createdDate);
    $(".myorder-orderId").html(myorder.orderId);
    $(".myorder-memberName").html(myorder.memberName);
    $(".myorder-memberPhone").html(myorder.memberPhone);
    $(".myorder-memberEmail").html(myorder.memberEmail);
    $(".myorder-orderAddress").html(myorder.orderAddress);
    $(".myorder-orderPrice").html(totalPrice - 3000 + "원");
    $(".myorder-totalPrice").html(totalPrice + "원");

}

function getMyOrderModal(myorder){
    let text = "";

    myorder.orderDetails.forEach(orderDetail => {
        text += `<div class="tabled full-width prod_preview">`;
        text += `<div class="table-cell vertical-middle width-2">`;
        text += `<img width="60" height="60" src="` + orderDetail.productFileProfileName + `">`;
        text += `</div>`;
        text += `<div class="table-cell vertical-middle">`;
        text += `<span class="text-15">[` + orderDetail.productCategory + `]` + orderDetail.productName + `</span>`;
        text += `<div class="text-13 text-gray-bright">`;
        text += `<div>` + orderDetail.productPrice + `원 / ` + orderDetail.orderDetailCount + `개</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div class="text-13 table-cell vertical-middle text-right width-2">` + (orderDetail.productPrice * orderDetail.orderDetailCount) + `원</div>`;
        text += `</div>`;
    });
    $(".body-block").html(text);
}







