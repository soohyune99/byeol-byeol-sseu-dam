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

function afterInsertMycourse(){
    console.log("콜백 돌아옴")
}




