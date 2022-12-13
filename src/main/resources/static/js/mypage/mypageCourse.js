/* mypageCourse.html */

let $unlockCourse = $(".mycourseWrap .unlock");
let $unlockCourseModal = $(".not-open-course");

globalThis.activeCourseName = '';   // 진행중인 코스
globalThis.activeSpotNumber = 0;    // 진행중인 스팟


showMyInfo();


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

/* 회원 정보 */
function showMyInfo(){
    $(".mypage-memberProfileName").attr('src', memberProfileName);
    $(".mypage-memberName").html(memberName);
    $(".mypage-memberEmail").html(memberEmail);
    $(".mypage-memberType").html(memberCategory);
    console.log(memberPoint);
    $(".mypage-memberPoint").html(memberPoint);
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
    $(".mycourseTab").removeClass("active");
    $(".mycourseTab." + courseId).addClass("active");

    mypageService.getCourse(
        courseId, showMyCourse
    )
}

function showCourse(courses){
    let text = "";
    let spotText = "";
    let courseAr = [];                              // 진행중이거나 완주한 코스들
    let courseArray = [];                           // 중복 제거한 코스들
    let mycourseLength = 0;                         // 내가 참여한 코스 수

    if(courses.length == 0) {
        $(".myTabContents.noticeTab").css('display', 'none');

    }

    courses.forEach(course => {
        mycourseLength += course.mycourses.length;
        console.log(mycourseLength);

        course.mycourses.forEach(mycourse => {

            courseAr.push(mycourse.courseName);

            if (course.courseName == mycourse.courseName && mycourse.courseFinishedStatus == '진행중') {
                globalThis.activeCourseName = mycourse.courseName;
                globalThis.activeSpotNumber = mycourse.spotNumber;
            }
        });

        if(mycourseLength == 0) {
            $(".myTabContents.noticeTab").css('display', 'none');
        }
    });

    courseArray = Array.from(new Set(courseAr));

    courses.forEach(course => {
        if(course.courseName == globalThis.activeCourseName){
            text += `<li class="mycourseTab active ` + course.courseId + `" onclick="javascript:clickCourseTab(` + course.courseId + `)">`;
            text += `<a>`;
            text += course.courseName.split(" ")[0];
            text += `</a>`;
            text += `</li>`;
        }else if(courseArray.includes(course.courseName)) {
            text += `<li class="mycourseTab ` + course.courseId + `" onclick="javascript:clickCourseTab(` + course.courseId + `)">`;
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

        if(course.courseName == globalThis.activeCourseName){
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

        showSpot(globalThis.activeSpotNumber);
    });
    $(".mycourseWrap").html(text);
    $(".courseList").html(spotText);
}

function showMyCourse(course){
    let text = "";
    console.log("콜백")

    course.spots.forEach(spot => {
        text += `<li class="noticeContentWrap active">`;
        text += `<div class="left">`;
        text += `<p class="categoryAndDate">`;
        text += `<span>` + spot.spotName + `</span>`;
        text += `</p>`;
        text += `<p class="noticeContent">` + spot.spotAddress + `</p>`;
        text += `</div>`;
        text += `</li>`;
    });

    if(course.courseName == globalThis.activeCourseName){
        showSpot(globalThis.activeSpotNumber);
    }else {
        showSpot(course.spots.length);
    }
    $(".courseList").html(text);
}

function showSpot(activeSpotNumber){
    let $courseLocation = $(".mycourseLocation");   // 진행 위치
    let $statusBar = $(".participatingCourseLine"); // 진행상태 바
    $courseLocation.css("padding-right", '0%');
    $courseLocation.css("padding-left", '0%');

    if(activeSpotNumber == 1) {
        $courseLocation.css('padding-right', '76%');
        $statusBar.css('width', '13%');
        $(".mypage-courseStatus").html("참여 중인");
    } else if(activeSpotNumber == 2){
        $courseLocation.css('padding-right', '38%%');
        $statusBar.css('width', '32%');
        $(".mypage-courseStatus").html("참여 중인");
    } else if(activeSpotNumber == 3){
        $courseLocation.css('padding-left', '0%');
        $statusBar.css('width', '50%');
        $(".mypage-courseStatus").html("참여 중인");
    } else if(activeSpotNumber == 4){
        $courseLocation.css('padding-left', '38%')
        $statusBar.css('width', '69%');
        $(".mypage-courseStatus").html("참여 중인");
    } else if(activeSpotNumber == 5){
        $courseLocation.css('padding-left', '76%')
        $statusBar.css('width', '100%');
        $(".mypage-courseStatus").html("완주한");
    }
}