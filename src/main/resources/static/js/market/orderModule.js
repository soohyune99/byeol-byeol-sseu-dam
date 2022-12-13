/* marketPayment.html */

let orderService = (function(){
    function getOrderDetail(productId, callback, error){
        console.log("product ajax");
        console.log(productId);
        $.ajax({
            url: "/ordering/" + productId,
            type: "get",
            data: JSON.stringify(productId),
            contentType: "application/json; charset=utf-8",
            success: function (products, status, xhr) {
                if(callback){
                    console.log("상품 성공");
                    console.log(products);
                    callback(products);
                }
            },
            error: function (xhr, status, err) {
                if(error){
                    error(err);
                }
            }
        });
    }

    function getOrderMember(memberId, callback, error){
        console.log("member ajax");
        console.log(memberId);
        $.ajax({
            url: "/ordering/" + memberId,
            type: "post",
            data: JSON.stringify(memberId),
            contentType: "application/json; charset=utf-8",
            success: function (member, status, xhr) {
                if(callback){
                    console.log("회원 성공");
                    console.log(member);
                    callback(member);
                }
            },
            error: function (xhr, status, err) {
                if(error){
                    error(err);
                }
            }
        });
    }

    function getSaveOrder(priceAmount, callback, error) {
        console.log("결제 ajax");
        console.log(priceAmount);
        $.ajax({
            url:"/ordering/payment",
            type: "post",
            data: JSON.stringify(priceAmount),
            contentType: "application/json; charset=utf-8",
            success: function (orderDTO) {
                location.href = "/app/market/marketPaid";
            },
            error: function (xhr, status, err) {
                if(error){
                    error(err);
                }
            }
        });

    }


    return{getOrderDetail : getOrderDetail, getOrderMember:getOrderMember, getSaveOrder:getSaveOrder}
})();
