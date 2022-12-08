/* mypageProgram.html */

let mypageService = (function(){
    function getMyProgramList(memberId, callback, error){
        $.ajax({
            url: "/mypage/program/" + memberId,
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

    function getMypointList(memberId, callback, error){
        $.ajax({
            url: "/mypage/point/" + memberId,
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

    return { getMyProgramList:getMyProgramList, getMypointList:getMypointList }
})();




