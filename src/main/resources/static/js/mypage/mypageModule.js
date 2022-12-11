/* mypageProgram.html */

let mypageService = (function(){
    function getMyProgramList(memberId, page, callback, error){
        $.ajax({
            url: "/mypage/program/" + memberId + "/" + page,
            type: "get",
            success: function(programs, status, xhr){
                if(callback){
                    callback(programs);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMypointList(memberId, page, callback, error){
        $.ajax({
            url: "/mypage/point/" + memberId + "/" + page,
            type: "get",
            success: function(points, status, xhr){
                if(callback){
                    callback(points);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMyCommunityList(memberId, page, callback, error){
        $.ajax({
            url: "/mypage/community/" + memberId + "/" + page,
            type: "get",
            success: function(myboards, status, xhr){
                if(callback){
                    callback(myboards);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMyCommentList(memberId, page, callback, error){
        $.ajax({
            url: "/mypage/comment/" + memberId + "/" + page,
            type: "get",
            success: function(mycomments, status, xhr){
                if(callback){
                    callback(mycomments);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMyInfo(memberId, callback, error){
        $.ajax({
            url: "/mypage/" + memberId,
            type: "get",
            success: function(myinfo, status, xhr){
                if(callback){
                    callback(myinfo);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function checkPassword(memberId, password, callback, error){
        $.ajax({
            url: "/mypage/check/" + memberId,
            type: "post",
            data: JSON.stringify(password),
            contentType: "application/json; charset=utf-8",
            success: function(check, status, xhr){
                if(callback){
                    callback(check);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function updateUserInfo(formData, callback, error) {
        $.ajax({
            url: "/mypage/update",
            type: "post",
            data: formData,
            enctype: 'multipart/form-data',
            cache: false,
            contentType: false,
            processData: false,
            success: function (member, status, xhr) {
                if (callback) {
                    callback(member);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function dropOutMember(memberId, callback, error){
        $.ajax({
            url: "/mypage/dropout/" + memberId,
            type: "get",
            success: function(myinfo, status, xhr){
                if(callback){
                    callback(myinfo);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMyOrderList(memberId, page, callback, error){
        $.ajax({
            url: "/mypage/order/" + memberId + "/" + page,
            type: "get",
            success: function(myorders, status, xhr){
                if(callback){
                    callback(myorders);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMyCancelList(memberId, page, callback, error){
        $.ajax({
            url: "/mypage/cancel/" + memberId + "/" + page,
            type: "get",
            success: function(myorders, status, xhr){
                if(callback){
                    callback(myorders);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMyOrder(cancelId, callback, error){
        $.ajax({
            url: "/mypage/cancel/" + cancelId,
            type: "get",
            success: function(mycancel, status, xhr){
                if(callback){
                    callback(mycancel);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function cancelMyOrder(orderId, callback, error){
        $.ajax({
            url: "/mypage/cancelOrder/" + orderId,
            type: "get",
            success: function(orderId, status, xhr){
                if(callback){
                    callback(orderId);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMyPickupList(memberId, page, callback, error){
        $.ajax({
            url: "/mypage/pickup/" + memberId + "/" + page,
            type: "get",
            success: function(mypickups, status, xhr){
                if(callback){
                    callback(mypickups);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMyPickup(pickupId, callback, error){
        $.ajax({
            url: "/mypage/pickup/" + pickupId,
            type: "post",
            success: function(mypickup, status, xhr){
                if(callback){
                    callback(mypickup);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function uploadProfileFile(file, callback, error) {
        var data = new FormData();
        data.append("file", file);

        $.ajax({
            url: "/mypage/upload",
            type: "post",
            data: data,
            enctype: 'multipart/form-data',
            cache: false,
            contentType: false,
            processData: false,
            success: function (file, status, xhr) {
                if (callback) {
                    callback(file);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    return { getMyProgramList:getMyProgramList, getMypointList:getMypointList, getMyCommunityList:getMyCommunityList,
        getMyCommentList:getMyCommentList, getMyInfo:getMyInfo, checkPassword:checkPassword, updateUserInfo:updateUserInfo,
        getMyOrderList:getMyOrderList, getMyCancelList:getMyCancelList, getMyOrder:getMyOrder, cancelMyOrder:cancelMyOrder,
        getMyPickupList:getMyPickupList, getMyPickup:getMyPickup, uploadProfileFile:uploadProfileFile }
})();




