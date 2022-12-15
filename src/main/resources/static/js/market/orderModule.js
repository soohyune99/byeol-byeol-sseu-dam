/* marketPayment.html */

let orderService = (function(){
    function getOrderDetail(productId, callback, error){
        $.ajax({
            url: "/ordering/" + productId,
            type: "get",
            data: JSON.stringify(productId),
            contentType: "application/json; charset=utf-8",
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
        $.ajax({
            url: "/ordering/" + memberId,
            type: "post",
            data: JSON.stringify(memberId),
            contentType: "application/json; charset=utf-8",
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

    function getSaveOrder(formData, callback, error) {
        $.ajax({
            url:"/ordering/payment",
            type: "post",
            data: formData,
            enctype:'multipart/form-data',
            cache:false,
            contentType:false,
            processData:false,
            success: function(order){
                location.href = "/market/paid/"+ order;
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
