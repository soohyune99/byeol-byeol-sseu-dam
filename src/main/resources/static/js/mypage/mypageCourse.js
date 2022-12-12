/* mypageCourse.html */

let $unlockCourse = $(".mycourseWrap .unlock");
let $unlockCourseModal = $(".not-open-course");

const memberId = 1;


getMemberInfo();


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

/* ============================= memberInfo ============================= */

function getMemberInfo(){
    mypageService.getMyInfo(
        memberId, showMemberInfo
    )
}

function showMemberInfo(member){
    $(".mypage-memberProfileName").attr('src', member.memberProfileName);
    $(".mypage-memberName").html(member.memberName);
    $(".mypage-memberEmail").html(member.memberEmail);
    $(".mypage-memberType").html(member.memberCategory);
    $(".mypage-memberPoint").html(member.memberPoint);
}

/* ================================ course ================================ */

getMyCourse();

/* 진행 중인 코스 및 완주 코스 조회 */
function getMyCourse(){
    mypageService.getCourses(
        memberId, showCourse
    );
}

/* 코스탭 클릭 시 */
function clickCourseTab(courseId){

}

function showCourse(courses){
    let text = "";
    let spotText = "";
    let courseAr = [];          // 진행중이거나 완주한 코스들
    let courseArray = [];       // 중복 제거한 코스들
    let activeCourseName = "";  // 진행중인 코스
    let activeSpotNumber = 0;   // 진행중인 스팟

    courses.forEach(course => {
        course.mycourses.forEach(mycourse => {
            courseAr.push(mycourse.courseName);

            if (course.courseName == mycourse.courseName && mycourse.courseFinishedStatus == '진행중') {
                activeCourseName = mycourse.courseName;
                activeSpotNumber = mycourse.spotNumber;
            }
        });
    });

    courseArray = Array.from(new Set(courseAr));

    courses.forEach(course => {
        if(course.courseName == activeCourseName){
            text += `<li class="mycourseTab active" onclick="javascript:clickCourseTab(` + course.courseId + `)">`;
            text += `<a>`;
            text += course.courseName.split(" ")[0];
            text += `</a>`;
            text += `</li>`;
        }else if(courseArray.includes(course.courseName)) {
            text += `<li class="mycourseTab" onclick="javascript:clickCourseTab(` + course.courseId + `)">`;
            text += `<a>`;
            text += course.courseName.split(" ")[0];
            text += `</a>`;
            text += `</li>`;
        }else {
            text += `<li class="mycourseTab unlock" onclick="javascript:unlockCourseModalOpen();">`;
            text += `<a>`;
            text += course.courseName.split(" ")[0] + " 🔒︎";
            text += `</a>`;
            text += `</li>`;
        }

        if(course.courseName == activeCourseName){
            course.spots.forEach(spot => {
                    spotText += `<li class="noticeContentWrap active">`;
                    spotText += `<div class="left">`;
                    spotText += `<p class="categoryAndDate">`;
                    spotText += `<span>` + spot.spotName + `</span>`;
                    spotText += `</p>`;
                    spotText += `<p class="noticeContent">` + spot.spotAddress + `</p>`;
                    spotText += `</div>`;
                    spotText += `</li>`;
            });
        }
    });
    $(".mycourseWrap").html(text);
    $(".courseList").html(spotText);
}