/* mypageOrderList.html */

const memberId = 1;
globalThis.page = 0;

showMyOrder();
getMemberInfo();

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

/* 주문조회 */
function showMyOrder(){
    mypageService.getMyOrderList(
        memberId, globalThis.page, showMyOrderList
    );
    globalThis.page++;
}

/* 더보기 클릭 시 */
function showMyOrderMore(){
    if(globalThis.page != 0){
        $(".myorder-more-btn").remove();
    }

    mypageService.getMyOrderList(
        memberId, globalThis.page, showMyOrderList
    );
    globalThis.page++;
}

function showMyOrderList(myorders){
    let text = "";

    myorders.forEach(myorder => {
        text += `<table class="table no-margin shop-table shop-table2">`;
        text += `<colgroup>`;
        text += `<col>`;
        text += `<col class="width-6">`;
        text += `</colgroup>`;
        text += `<thead class="item-subject">`;
        text += `<tr>`;
        text += `<th class="hidden-xs hidden-sm im-body-size">`;
        text += `<div class="im-flex im-items-center">`;
        text += `<span class="margin-right-xl">주문번호</span>`;
        text += `<span class="margin-right-lg"></span>`;
        text += `<a class="text-primary" href="/shop_mypage/?m2=order&amp;idx=94638288">` + myorder.orderId + `<i class="im-icon im-ico-circle-arrow-right"></i></a>`;
        text += `</div>`;
        text += `</th>`;
        text += `<th class="text-right text-gray-bright no-padding-x im-body-size im-xs-body-size" colspan="2">`;
        text += `<span class="hidden-xs hidden-sm">주문일자 </span>`;
        text += `<span class="im-xs-bold">` + myorder.createdDate + `</span>`;
        text += `<a href="/shop_mypage/?m2=order&amp;idx=94638288" class="text-brand hidden-lg hidden-md">주문 상세보기<i class="im-icon im-ico-circle-arrow-right"></i></a>`;
        text += `</th>`;
        text += `</tr>`;
        text += `</thead>`;
        text += `<tbody style="background: #fff;">`;
        myorder.orderDetails.forEach(orderDetail => {
            text += `<tr class="content">`;
            text += `<td class="im-xs-align-top">`;
            text += `<a class="im-flex im-justify-between" href="/mypage/order/` + myorder.orderId + `">`;
            text += `<div class="im-flex">`;
            text += `<img src="` + orderDetail.productFileProfileName + `" class="margin-right-xxl" alt="주문상품 이미지">`;
            text += `<div>`;
            text += `<span class="im-body-size im-body-line-height text-bold">[` + orderDetail.productCategory + `]</span>`;
            text += `<div class="im-body-size-90 im-body-line-height">`;
            text += `<span class="blocked opacity-70">` + orderDetail.productName + `</span>`;
            text += `<span class="blocked price">` + orderDetail.productPrice + `원 / ` + orderDetail.orderDetailCount + `개` + `</span>`;
            text += `<span class="text-default opacity-70 im-body-size im-body-line-height text-bold hidden-lg hidden-md hidden-sm" style="margin-bottom: 5px;">` + myorder.orderStatus + `</span>`;
            text += `</div>`;
            text += `</div>`;
            text += `</div>`;
            text += `<div class="order_title text-bold text-center hidden-xs">`;
            text += `<span class="text-default opacity-70 im-body-size im-body-line-height">` + myorder.orderStatus + `</span>`;
            text += `</div>`;
            text += `</a>`;
            text += `</td>`;
            text += `<td class="cart-btn-tools text-right">`;
            text += `<div class="im-inline-flex im-flex-col-reverse im-xs-flex im-xs-flex-row-reverse im-xs-flex-wrap-reverse _btn_tool_row" style="width: 87px;">`;
            text += `<a class="btn-order-cancel im-flex-1" href="/mypage/order/` + myorder.orderId + `">상세보기</a>`;
            text += `</div>`;
            text += `</td>`;
            text += `</tr>`;
            text += `</tbody>`;
        });
        text += `<tbody id="shop_mypage_orderlist_empty" style="display: none">`;
        text += `<tr style="background: transparent; padding: 0">`;
        text += `<td colspan="2" style="padding: 70px; text-align: center;">`;
        text += `<div style="font-size:18px;" class="body_font_color_40"></div>`;
        text += `</td>`;
        text += `</tr>`;
        text += `</tbody>`;
        text += `</table>`;
    });

    if(myorders.length == 3){
        text += `<button onclick="javascript:showMyOrderMore();" class="myorder-more-btn" style="width: 100%; height: 70px; background: transparent; border: none; font-size: 12px; font-weight:bold;">더보기</button>`;
    }

    $("#shop_mypage_orderlist").append(text);
}










