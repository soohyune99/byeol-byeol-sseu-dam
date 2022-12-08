/* market.html */

let marketService = (function(){

    /* 마켓 전체 조회 */
    function getProductList(product, callback, error){
        $.ajax({
            url: "/product",
            type: "get",
            success: function(products, status, xhr){
                if(callback){
                    callback(products);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    /* 마켓 카테고리별 조회 */
    function getCategoryMarkets(productCategory, callback, error){
        console.log(productCategory);
        $.ajax({
            url: "/product/" + productCategory,
            type: "get",
            success: function(products, status, xhr){
                if(callback){
                    callback(products);

                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    /* 마켓 검색 */
    function getSearchProducts(keyword, callback, error) {
        $.ajax({
            url: "/product/" + keyword,
            type: "post",
            success: function (products, status, xhr){
                console.log(keyword);
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

    /* 무한 스크롤 */
    function infiniteScroll(formData, callback, error){
        $.ajax({
            url: "/product/scroll/",
            type: "post",
            data:formData,
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
                    error(err);
                }
            }
        });
    }

    return{ getProductList:getProductList, getCategoryMarkets:getCategoryMarkets,
        getSearchProducts:getSearchProducts, getProductDetail:getProductDetail,
        infiniteScroll:infiniteScroll }
})();