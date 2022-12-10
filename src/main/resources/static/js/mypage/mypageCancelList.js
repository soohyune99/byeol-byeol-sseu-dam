/* mypageCancelList.html */

const memberId = 1;
globalThis.page = 0;
let $cancelModal = $("#cocoaModal");

showMyCancel();

/* 취소 상세 모달 열기 */
function cancelModalOpen(cancelId){
    $cancelModal.css('display', 'block');

    mypageService.getMyCancel(
        cancelId, getMyCancel
    );
}

/* 취소 상세 모달 닫기 */
function cancelModalClose(mycancel){
    $cancelModal.css('display', 'none');
}

/* 주문취소 조회 */
function showMyCancel(){
    mypageService.getMyCancelList(
        memberId, globalThis.page, getMyCancelList
    );
    globalThis.page++;
}

/* 더보기 클릭 시 */
function showMyCancelMore(){
    if(globalThis.page != 0){
        $(".mycancel-more-btn").remove();
    }

    mypageService.getMyCancelList(
        memberId, globalThis.page, getMyCancelList
    );
    globalThis.page++;
}

function getMyCancelList(mycancels){
    let text = "";

    mycancels.forEach(mycancel => {
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
        text += `<a class="text-primary" href="/shop_mypage/?m2=order&amp;idx=94638288">` + mycancel.orderId + `<i class="im-icon im-ico-circle-arrow-right"></i></a>`;
        text += `</div>`;
        text += `</th>`;
        text += `<th class="text-right text-gray-bright no-padding-x im-body-size im-xs-body-size" colspan="2">`;
        text += `<span class="hidden-xs hidden-sm">주문일자 </span>`;
        text += `<span class="im-xs-bold">` + mycancel.createdDate + `</span>`;
        text += `<a href="/shop_mypage/?m2=order&amp;idx=94638288" class="text-brand hidden-lg hidden-md">주문 상세보기<i class="im-icon im-ico-circle-arrow-right"></i></a>`;
        text += `</th>`;
        text += `</tr>`;
        text += `</thead>`;
        text += `<tbody style="background: #fff;">`;
        mycancel.orderDetails.forEach(cancelDetail => {
            text += `<tr class="content">`;
            text += `<td class="im-xs-align-top">`;
            text += `<a class="im-flex im-justify-between" href="javascript:openCancelModal(` + mycancel.orderId + `)">`;
            text += `<div class="im-flex">`;
            text += `<img src="` + cancelDetail.productFileProfileName + `" class="margin-right-xxl" alt="주문상품 이미지">`;
            text += `<div>`;
            text += `<span class="im-body-size im-body-line-height text-bold">[` + cancelDetail.productCategory + `]</span>`;
            text += `<div class="im-body-size-90 im-body-line-height">`;
            text += `<span class="blocked opacity-70">` + cancelDetail.productName + `</span>`;
            text += `<span class="blocked price">` + cancelDetail.productPrice + `원 / ` + cancelDetail.orderDetailCount + `개` + `</span>`;
            text += `<span class="text-default opacity-70 im-body-size im-body-line-height text-bold hidden-lg hidden-md hidden-sm" style="margin-bottom: 5px;">` + mycancel.orderStatus + `</span>`;
            text += `</div>`;
            text += `</div>`;
            text += `</div>`;
            text += `<div class="order_title text-bold text-center hidden-xs">`;
            text += `<span class="text-default opacity-70 im-body-size im-body-line-height">` + mycancel.orderStatus + `</span>`;
            text += `</div>`;
            text += `</a>`;
            text += `</td>`;
            text += `<td class="cart-btn-tools text-right">`;
            text += `<div class="im-inline-flex im-flex-col-reverse im-xs-flex im-xs-flex-row-reverse im-xs-flex-wrap-reverse _btn_tool_row" style="width: 87px;">`;
            text += `<a class="btn-order-cancel im-flex-1" href="javascript:cancelModalOpen(` + mycancel.orderId + `)">상세보기</a>`;
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

    if(mycancels.length == 3){
        text += `<button onclick="javascript:showMyCancelMore();" class="mycancel-more-btn" style="width: 100%; height: 70px; background: transparent; border: none; font-size: 12px; font-weight:bold;">더보기</button>`;
    }

    $("#shop_mypage_orderlist").append(text);
}

function getMyCancel(mycancel){
    let text = "";

    mycancel.orderDetails.forEach(orderDetail => {
        text += `<div class="tabled full-width prod_preview">`;
        text += `<div class="table-cell vertical-middle width-2">`;
        text += `<img width="60" height="60" src="` + orderDetail.productFileProfileName + `">`;
        text += `</div>`;
        text += `<div class="table-cell vertical-middle">`;
        text += `<span class="text-15">[` + orderDetail.productCategory + `]` + orderDetail.productName + `</span>`;
        text += `<div class="text-13 text-gray-bright">`;
        text += `<!--<div>컬러&nbsp;:&nbsp;그린</div>-->`;
        text += `<div>` + orderDetail.productPrice + `원 / ` + orderDetail.orderDetailCount + `개</div>`;
        text += `</div>`;
        text += `</div>`;
        text += `<div class="text-13 table-cell vertical-middle text-right width-2">` + mycancel.orderStatus + `</div>`;
        text += `</div>`;
    });
    $(".body-block").html(text);
}










