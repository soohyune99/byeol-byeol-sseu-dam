/* jubggingqr.html */

let url = decodeURI(window.location.href).split("/");
let courseName = url[url.length - 2];
let spotNumber = url[url.length - 1];

insertMycourse();

console.log(memberId);
console.log(courseName);
console.log(spotNumber);

function insertMycourse(){
    jubggingService.insertCourse(
        afterInsertMycourse
    );
}

function afterInsertMycourse(mycourse){
    console.log("콜백")
    console.log(mycourse);
    console.log(mycourse.courseName)
    console.log(mycourse.createdDate)
    $(".jubgging-courseName").html(mycourse.courseName);
    $(".jubgging-spotNumber").html(mycourse.spotName);
    $(".jubgging-date").html(mycourse.createdDate);
}




