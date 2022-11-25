/* mypageCourse.html */

let $unlockCourse = $(".myTabMenu .unlock");
let $unlockCourseModal = $(".swal2-container.swal2-center");


$unlockCourse.on('click', function(){
    unlockCourseModalOpen();
});


/* 미참여 코스 안내 모달 열기 */
function unlockCourseModalOpen(){
    $unlockCourseModal.css('display', 'block');
}


/* 미참여 코스 안내 모달 닫기 */
function unlockCourseModalClose(){
    $unlockCourseModal.css('display', 'none');
}





