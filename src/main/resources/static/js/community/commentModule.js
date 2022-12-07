/* communityRead.html */


let commentService = (function(){
    function getCommentList(boardId, callback, error){
        $.ajax({
            url: "/comment/" + boardId,
            type: "get",
            data: JSON.stringify(boardId),
            contentType: "application/json; charset=utf-8",
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

    function save(comment, callback, error){
        $.ajax({
            url: "/comment/new",
            type: "post",
            data: JSON.stringify(comment),
            contentType: "application/json; charset=utf-8",
            success: function(result, status, xhr){
                if(callback){
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
    return {getCommentList:getCommentList, save: save}
})();

