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

    function getCountMyprogram(memberId, callback, error){
        $.ajax({
            url: "/mypage/program/count/" + memberId,
            type: "get",
            success: function(count, status, xhr){
                if(callback){
                    callback(count);
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

    function plusBoardView(boardId, callback, error){
        $.ajax({
            url: "/board/view/" + boardId,
            type: "patch",
            success: function(boardId, status, xhr){
                if(callback){
                    callback(boardId);
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

    function sendVerification(phoneNumber, callback, error){
        $.ajax({
            url: "/mypage/send/" + phoneNumber,
            type: "post",
            success: function(vertificationNumber, status, xhr){
                if(callback){
                    callback(vertificationNumber);
                }
            },
            error: function(xhr, status, err){
                if(error){
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

    function logoutMember(callback, error){
        $.ajax({
            url: "/logout/rest",
            type: "get",
            success: function(status, xhr){
                if(callback){
                    callback();
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

    function getCountMyorder(memberId, callback, error){
        $.ajax({
            url: "/mypage/order/count/" + memberId,
            type: "get",
            success: function(count, status, xhr){
                if(callback){
                    callback(count);
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

    function getCountMycancel(memberId, callback, error){
        $.ajax({
            url: "/mypage/cancel/count/" + memberId,
            type: "get",
            success: function(count, status, xhr){
                if(callback){
                    callback(count);
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

    function getCountMypickup(memberId, callback, error){
        $.ajax({
            url: "/mypage/pickup/count/" + memberId,
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

    function getCourses(memberId, callback, error){
        $.ajax({
            url: "/mypage/course/" + memberId,
            type: "get",
            success: function(courses, status, xhr){
                if(callback){
                    callback(courses);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMyCourses(memberId, callback, error){
        $.ajax({
            url: "/mypage/mycourse/" + memberId,
            type: "get",
            success: function(mycourses, status, xhr){
                if(callback){
                    callback(mycourses);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getCourse(courseId, callback, error){
        $.ajax({
            url: "/mypage/course/" + courseId,
            type: "post",
            success: function(courses, status, xhr){
                if(callback){
                    callback(courses);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getBadges(callback, error){
        $.ajax({
            url: "/mypage/badge",
            type: "post",
            success: function(badge, status, xhr){
                if(callback){
                    callback(badge);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function getMybadges(memberId, callback, error){
        $.ajax({
            url: "/mypage/badge/" + memberId,
            type: "get",
            success: function(mybadges, status, xhr){
                if(callback){
                    callback(mybadges);
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

    function timeForToday(value) {
        const today = new Date();
        const timeValue = new Date(value);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금전';
        if (betweenTime < 60) {
            return `${betweenTime}분전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일전`;
        }

        return `${Math.floor(betweenTimeDay / 365)}년전`;
    }


    return { getMyProgramList:getMyProgramList, getCountMyprogram:getCountMyprogram, getMypointList:getMypointList,
        getMyCommunityList:getMyCommunityList, getMyCommentList:getMyCommentList, plusBoardView:plusBoardView,
        getMyInfo:getMyInfo, checkPassword:checkPassword, updateUserInfo:updateUserInfo,
        sendVerification:sendVerification, dropOutMember:dropOutMember,
        getMyOrderList:getMyOrderList, getCountMyorder:getCountMyorder, getMyOrder:getMyOrder,
        getMyCancelList:getMyCancelList, getCountMycancel:getCountMycancel, cancelMyOrder:cancelMyOrder,
        getMyPickupList:getMyPickupList, getMyPickup:getMyPickup, getCountMypickup:getCountMypickup,
        getCourses:getCourses, getMyCourses:getMyCourses,
        getCourse:getCourse, getBadges:getBadges, getMybadges:getMybadges, uploadProfileFile:uploadProfileFile,
        logoutMember:logoutMember, timeForToday:timeForToday }
})();




