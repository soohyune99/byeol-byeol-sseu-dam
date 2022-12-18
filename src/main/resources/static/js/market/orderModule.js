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
                    console.log("변경 성공");
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

    function getOrderDetailList(productId, callback, error) {
        $.ajax({
            url: "/ordering/all/" + productId,
            type: "post",
            data: JSON.stringify(productId),
            contentType: "application/json; charset=utf-8",
            success: function (products, status, xhr) {
                if(callback){
                    console.log("변경 성공");
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
    function getBasketOrderList(basketFlag, callback, error){
        console.log("장바구니 상품 구매 ajax");
        console.log(basketFlag);
        $.ajax({
            url: "/ordering/basket/",
            type:"post",
            data: JSON.stringify(basketFlag),
            contentType: "application/json; charset=utf-8",
            success: function (baskets, status, xhr) {
                console.log(baskets);
                if (callback) {
                    console.log("변경 성공");
                    callback(baskets);

                }
            },
            error: function (xhr, status, err) {
                console.log("실패");
                if (error) {
                    alert("오류");
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
        console.log("ajax 들어옴");
        console.log(formData.get("basketId"));
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
    function getBasketSaveOrder(formData, callback, error) {
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


    async function payment( products, memberId, callback, error){
        // 결제 금액, 구매자의 이름, 이메일, 전화번호, 주소, 배송메시지, 포인트
        const s_zipNo = document.getElementById('sample4_postcode').value;
        const roadAdd = document.getElementById("sample4_roadAddress").value;
        const detailAdd = document.getElementById("sample4_extraAddress").value;
        const writeAdd = document.getElementById("sample4_jibunAddress").value;
        const buyerMemberAddress = s_zipNo + roadAdd + detailAdd + writeAdd;
        const buyerMemberMsg = document.getElementById("category").options[document.getElementById("category").selectedIndex].value;
        const mypoint = $(".my-point").val();
        const price = $(".total-price").text().split("원")[0]; //총 금액
        const savePoint = $(".save-point").val();
        const productId = $(".hidden-productId").text();
        const productName = $(".hidden-productName").text();
        const productPrice =$(".hidden-productPrice").text();
        // const memberId = $("input[name='memberId']").val();
        const memberName = $("input[name='memberName']").val();
        const memberPhone =  $(".info-member-phone").text();
        const memberEmail =  $(".info-member-email").text();

        const response = await Bootpay.requestPayment({
            "application_id": "639762b8d01c7e0020feb95d",
            "price": price,      // 요청 금액
            "order_name":productName,
            "order_id":productId,
            "order_name": JSON.stringify(products),
            "order_id": JSON.stringify(products),
            "pg": "케이씨피",
            "method": "카드",
            "tax_free": 0,
            "user": {
                "id": memberId,
                "username": memberName,
                "phone": memberPhone,
                "email": memberEmail,
                "add": buyerMemberAddress,
                "msg": buyerMemberMsg,
                "point": savePoint
            },
            // "items": JSON.stringify(products),
            "extra": {
                "open_type": "iframe",
                "card_quota": "0,2,3",
                "escrow": false,
                "display_success_result":true
            }

        })
        switch (response.event) {
            case 'done':
                let formData = new FormData();
                formData.append('zipcode', s_zipNo);
                formData.append('roadAdd', roadAdd);
                formData.append('detailAdd', detailAdd);
                formData.append('writeAdd', writeAdd);
                formData.append('message', buyerMemberMsg);
                formData.append('buyCount', count);
                formData.append('memberId', memberId);
                formData.append('productId', productId);

                // 결제 완료 처리
                orderService.getSaveOrder(formData);
                break;
        }
    }


    async function basketPayment(baskets, memberId, callback, error){
        // 결제 금액, 구매자의 이름, 이메일, 전화번호, 주소, 배송메시지, 포인트
        const s_zipNo = document.getElementById('sample4_postcode').value;
        const roadAdd = document.getElementById("sample4_roadAddress").value;
        const detailAdd = document.getElementById("sample4_extraAddress").value;
        const writeAdd = document.getElementById("sample4_jibunAddress").value;
        const buyerMemberAddress = s_zipNo + roadAdd + detailAdd + writeAdd;
        const buyerMemberMsg = document.getElementById("category").options[document.getElementById("category").selectedIndex].value;
        const mypoint = $(".my-point").val();
        const price = $(".total-price").text().split("원")[0]; //총 금액
        const savePoint = $(".save-point").val();
        const basketId = $(".hidden-productId").text();
        const productName = $(".hidden-productName").text();
        const productPrice =$(".hidden-productPrice").text();
        // const memberId = $("input[name='memberId']").val();
        const memberName = $("input[name='memberName']").val();
        const memberPhone =  $(".info-member-phone").text();
        const memberEmail =  $(".info-member-email").text();

        const response = await Bootpay.requestPayment({
            "application_id": "639762b8d01c7e0020feb95d",
            "price": price,      // 요청 금액
            "order_name":productName,
            "order_id":basketId,
            "order_name": JSON.stringify(baskets),
            "order_id": JSON.stringify(baskets),
            "pg": "케이씨피",
            "method": "카드",
            "tax_free": 0,
            "user": {
                "id": memberId,
                "username": memberName,
                "phone": memberPhone,
                "email": memberEmail,
                "add": buyerMemberAddress,
                "msg": buyerMemberMsg,
                "point": savePoint
            },
            // "items": JSON.stringify(products),
            "extra": {
                "open_type": "iframe",
                "card_quota": "0,2,3",
                "escrow": false,
                "display_success_result":true
            }

        })
        switch (response.event) {
            case 'done':
                let formData = new FormData();
                formData.append('zipcode', s_zipNo);
                formData.append('roadAdd', roadAdd);
                formData.append('detailAdd', detailAdd);
                formData.append('writeAdd', writeAdd);
                formData.append('message', buyerMemberMsg);
                formData.append('buyCount', count);
                formData.append('memberId', memberId);
                formData.append('basketId', basketId);

                // 결제 완료 처리
                orderService.getBasketSaveOrder(formData);
                break;
        }
    }


    return{getOrderDetail : getOrderDetail,getOrderDetailList:getOrderDetailList ,getOrderMember:getOrderMember, getSaveOrder:getSaveOrder,
        payment:payment, getBasketOrderList:getBasketOrderList, basketPayment:basketPayment, getBasketSaveOrder:getBasketSaveOrder}
})();
