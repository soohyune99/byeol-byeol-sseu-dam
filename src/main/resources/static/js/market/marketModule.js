/* market.html */

let marketService = (function(){

    /* 마켓 전체 조회 */
    function getProductList(formData, callback, error){
        $.ajax({
            url: "/product",
            type: "post",
            data: formData,
            enctype:'multipart/form-data',
            cache:false,
            contentType:false,
            processData:false,
            success: function(products, status, xhr){
                if(callback){
                    callback(products);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    noProductList();
                }
            }
        });
    }

    /* 마켓 상세 조회 */
    function getProductDetail(productId, callback, error){
        $.ajax({
            url: "/product/read/" + productId,
            type: "get",
            success: function(product, status, xhr){
                if(callback){
                    callback(product);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    return{ getProductList:getProductList, getProductDetail:getProductDetail}
})();