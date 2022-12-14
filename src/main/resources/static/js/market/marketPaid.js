/* marketPaid.html */
/* 세션에 있는 멤버 아이디 & 이름 */
const memberId = $("input[name='memberId']").val();
const memberName = $("input[name='memberName']").val();

console.log(memberId);
console.log(memberName);
/* ================================== 결제 내역 보기 ==================================*/

/* 주문 영수증 조회 */

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
    text += `<td>`;
    text += memberName + `<br>`;
    text += `<span style="color:#757575">` + orders.memberPhone + `</span>`;
    text += `<br>`;
    text += orders.address + `<br>`;
    text += `<br>`;
    text += `<span style="color:#757575">` + orders.message + `</span>`;
    text += `<br>`;
    text += `</td>`;

    $(".member-info").append(text);
}
