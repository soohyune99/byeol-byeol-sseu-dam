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

    function updateOKComment(comment, callback, error){
        $.ajax({
            url: "/comment/update",
            type: "patch",
            data: JSON.stringify(comment),
            contentType: "application/json; charset=utf-8",
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

    function deleteComment(commentId, callback, error){
        $.ajax({
            url: "/comment/" + commentId,
            type: "delete",
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
    return {getCommentList:getCommentList, save: save, updateOKComment:updateOKComment, deleteComment:deleteComment}
})();

