
// 서비스에 대한 기능들을 하나의 모듈로 묶어서 처리한다.
let reviewService = (function(){
    function save(review, callback, error){
        $.ajax({
            url: "/review/new",
            type: "post",
            data: JSON.stringify(review),
            contentType: "application/json; charset=utf-8",
            success: function(result, status, xhr){
                if(callback){
                    console.log(typeof result)
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getList(param, callback, error){
        $.ajax({
            url: "/review/list/" + param.productId,
            type: "get",
            success: function(reviewDTO, status, xhr){
                if(callback){
                    callback(reviewDTO);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    // function read(replyNumber, callback, error){
    //     $.ajax({
    //         url: "/reply/" + replyNumber,
    //         type: "get",
    //         success: function(reply, status, xhr){
    //             if(callback){
    //                 callback(reply);
    //             }
    //         },
    //         error: function(xhr, status, err){
    //             if(error){
    //                 error(err);
    //             }
    //         }
    //     });
    // }
    //
    // function remove(replyNumber, callback, error){
    //     $.ajax({
    //         url: "/reply/" + replyNumber,
    //         type: "delete",
    //         success: function(text){
    //             if(callback){
    //                 callback(text);
    //             }
    //         },
    //         error: function(xhr, status, err){
    //             if(error){
    //                 error(err);
    //             }
    //         }
    //     });
    // }
    //
    // function modify(reply, callback, error){
    //     $.ajax({
    //         url: "/reply/" + reply.replyNumber + "/" + (reply.replyWriter || ""),
    //         type: "patch",
    //         contentType: "application/json; charset=utf-8",
    //         data: JSON.stringify(reply),
    //         success: function(text){
    //             if(callback){
    //                 callback(text);
    //             }
    //         },
    //         error: function(xhr, status, err){
    //             if(error){
    //                 error(err);
    //             }
    //         }
    //     })
    // }
    //
    // function getReplyDate(replyDate){
    //     let today = new Date();
    //     let registerDate = new Date(replyDate);
    //     let gap = today.getTime() - registerDate.getTime();
    //
    //     if(gap < 1000 * 60 * 60 * 24){
    //         let h = registerDate.getHours();
    //         let mm = registerDate.getMinutes();
    //         let s = registerDate.getSeconds();
    //
    //         h = (h < 10 ? '0' : '') + h;
    //         mm = (mm < 10 ? '0' : '') + mm;
    //         s = (s < 10 ? '0' : '') + s;
    //
    //         return [h, mm, s].join(":");
    //     }else{
    //         let y = registerDate.getFullYear();
    //         let m = registerDate.getMonth() + 1;
    //         let d = registerDate.getDate();
    //
    //         m = (m < 10 ? '0' : '') + m;
    //         d = (d < 10 ? '0' : '') + d;
    //
    //         return [y, m, d].join("-");
    //     }
    //
    // }
    //
    // function timeForToday(value) {
    //     const today = new Date();
    //     const timeValue = new Date(value);
    //
    //     const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
    //     if (betweenTime < 1) return '방금전';
    //     if (betweenTime < 60) {
    //         return `${betweenTime}분전`;
    //     }
    //
    //     const betweenTimeHour = Math.floor(betweenTime / 60);
    //     if (betweenTimeHour < 24) {
    //         return `${betweenTimeHour}시간전`;
    //     }
    //
    //     const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    //     if (betweenTimeDay < 365) {
    //         return `${betweenTimeDay}일전`;
    //     }
    //
    //     return `${Math.floor(betweenTimeDay / 365)}년전`;
    // }

    return {save:save, getList:getList}
})();






























