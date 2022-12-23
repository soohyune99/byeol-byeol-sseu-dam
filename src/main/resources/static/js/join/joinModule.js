/* joinCollectorStepTwo.html */

let joinService = (function() {
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

    return { sendVerification:sendVerification }
})();
