/* communityRead.html */


let commentService = (function(){
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
    return {save: save}
})();

