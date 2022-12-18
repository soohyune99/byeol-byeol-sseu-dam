/* jubggingqr.html */

let jubggingService = (function(){

    function insertCourse(callback, error){
        $.ajax({
            url: "/course/" + courseName + "/" + spotNumber,
            type: "get",
            success: function(mycourse, status, xhr){
                if(callback){
                    callback(mycourse);
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