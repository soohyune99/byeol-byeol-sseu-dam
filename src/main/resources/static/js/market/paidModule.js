/* marketPaid.html */

let paidService = (function(){
    function showPaidRecipt(memberId, callback, error){
        console.log("영수증 ajax");
        console.log(memberId);

        $.ajax({
            url : "/ordering/success/" + memberId,
            type: "post",
            data: JSON.stringify(memberId),
            contentType: "application/json; charset=utf-8",
            success: function(orders, status, xhr){
                if(callback){
                    console.log("영수증 callback");
                    console.log(orders);
                    callback(orders);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }
    return{showPaidRecipt:showPaidRecipt}

})();
