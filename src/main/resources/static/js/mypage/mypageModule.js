/* mypageProgram.html */

let mypageService = (function(){
    function getMyProgramList(memberId, callback, error){
        $.ajax({
            url: "/mypage/program/" + memberId,
            type: "get",
            success: function(comments, status, xhr){
                if(callback){
                    callback(comments);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    return { getMyProgramList:getMyProgramList }
});




