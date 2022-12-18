/* marketBasket.html */

/* 로그인 한 사람들 장바구니 */
let basketService = (function(){

    function saveBasket(basket, callback, error) {
        $.ajax({
            url:"/cart/new",
            type:"post",
            data: JSON.stringify(basket),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr,status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

   /* 장바구니 조회*/
    function getBasketList(memberId, callback, error){
        $.ajax({
            url:"/cart/show/" + memberId,
            type:"post",
            data: JSON.stringify(memberId),
            contentType: "application/json; charset=utf-8",
            success: function (baskets, status, xhr) {
                if(callback){
                    callback(baskets);
                }
            },
            error: function (xhr, status, err) {
                if(error){
                    error(err);
                }
            }
        });
    }

    /* 모달로 장바구니 한개 조회 */
    function getBasket(basketId, callback, error){
        $.ajax({
            url:"/cart/modal/" + basketId,
            type:"post",
            data: JSON.stringify(basketId),
            contentType: "application/json; charset=utf-8",
            success: function (basket, status, xhr) {
                if(callback){
                    callback(basket);
                }
            },
            error: function (xhr, status, err) {
                if(error){
                    error(err);
                }
            }
        });
    }

    /* 상품 수량 변경 */
    function updateBasketCount(basketId, basketCount, callback, error){
        console.log("상품 수량 ajax");
        console.log(basketId);
        console.log(basketCount);
        $.ajax({
            url: "/cart/update/" + basketId + "/" + basketCount,
            type: "post",
            data: JSON.stringify(basketId, basketCount),
            contentType: "application/json; charset=utf-8",
            success: function (baskets, status, xhr) {
                if (callback) {
                    console.log("변경 성공");
                    console.log(baskets);
                    callback(baskets);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    /* 상품 삭제 */
    function deleteBasket(basketId, callback, error){
        console.log("상품 삭제 ajax");
        console.log(basketId);
        $.ajax({
            url: "/cart/delete/" + basketId,
            type: "delete",
            data: JSON.stringify(basketId),
            contentType: "application/json; charset=utf-8",
            success: function (baskets, status, xhr) {
                if(callback){
                    callback();
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }



    /* 상품 구매 */
    function buyBasketProduct(paymentFlag, callback, error) {
        console.log("상품 구매 ajax");
        console.log(paymentFlag);
        $.ajax({
            url: "/cart/buy",
            type:"post",
            data: JSON.stringify(paymentFlag),
            contentType: "application/json; charset=utf-8",
            success: function (baskets, status, xhr) {
                if (callback) {
                    console.log("변경 성공");
                    callback(baskets);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    alert("오류");
                    error(err);
                }
            }
        });
    }

    return{saveBasket:saveBasket, getBasketList:getBasketList, getBasket:getBasket,
        updateBasketCount:updateBasketCount, deleteBasket:deleteBasket, buyBasketProduct:buyBasketProduct}
})();