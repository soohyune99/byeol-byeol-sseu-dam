/* marketPayment.html */

let orderService = (function(){
    function getOrderDetail(productId, callback, error){
        console.log("product ajax");
        $.ajax({
            url: "/ordering/" + productId,
            type: "get",
            success: function (products, status, xhr) {
                if(callback){
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
        $.ajax({
            url: "/ordering/" + memberId,
            type: "post",
            success: function (member, status, xhr) {
                if(callback){
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


    return{getOrderDetail : getOrderDetail, getOrderMember:getOrderMember}
})();
