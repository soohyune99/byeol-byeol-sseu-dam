/* jubggingqr.html */

let jubggingService = (function(){

    function insertCourse(callback, error){
        $.ajax({
            url: "/insert/" + courseName + "/" + spotNumber,
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
    return { insertCourse:insertCourse }
})();