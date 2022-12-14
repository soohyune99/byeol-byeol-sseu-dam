/* jubggingqr.html */

console.log("hi");

let url = decodeURI(window.location.href).split("/");
let memberId = url[url.length - 3];
let courseName = url[url.length - 2];
let spotNumber = url[url.length - 1];

insertMycourse();

function insertMycourse(){
    let formData = new FormData();

    formData.append('memberId', memberId);
    formData.append('courseName', courseName);
    formData.append('spotNumber', parseInt(spotNumber));
    // formData.append("memberId", 1);
    // formData.append("courseName", "4");
    // formData.append("spotNumber", 5);

    spotNumber = parseInt(spotNumber);

    $.ajax({
        url: "/jubgging/" + memberId + "/" + courseName + "/" + spotNumber,
        type: "post",
        // data: JSON.stringify(memberId, courseName, spotNumber),
        // contentType: "application/json; charset=utf-8",
        success: function(course, status, xhr){
            if(callback){
                callback(course);
            }
        },
        error: function(xhr, status, err){
            console.log("error: " + err);
        }
    });
}






