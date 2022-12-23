/* marketPaid.html */


/* 세션에 있는 멤버 아이디 & 이름 */
const memberId = $("input[name='memberId']").val();
const memberName = $("input[name='memberName']").val();

/* ================================== 결제 내역 보기 ==================================*/

/* 주문완료 후  조회 */

showRecipt();

function showRecipt() {
    paidService.showPaidRecipt(
        memberId, showOrderRecipt
    )
}

function showOrderRecipt(orders){
    console.log(orders);
    $(".order-id").html(orders.orderId);

    let text = "";
    text += `<th>배송지</th>`;
    text += `<td>`;
    text += memberName + `<br>`;
    text += `<span style="color:#757575">` + orders.memberEmail + `</span>`;
    text += `<br>`;
    text += orders.orderAddress + `<br>`;
    text += `<br>`;
    text += `<span style="color:#757575">` + orders.orderMessage + `</span>`;
    text += `<br>`;
    text += `</td>`;

    $(".member-info").append(text);
}
