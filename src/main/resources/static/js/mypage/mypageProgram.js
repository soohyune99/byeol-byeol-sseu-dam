/* mypageProgram.html */

globalThis.page = 0;

showMyprogram();
getMemberInfo();

/* 기본 회원 정보 조회 */
function getMemberInfo(){
    mypageService.getMyInfo(
        memberId, showMemberInfo
    )
}

function showMemberInfo(member) {
    $(".mypage-memberProfileName").attr('src', member.memberProfileName);
    $(".mypage-memberName").html(member.memberName);
    $(".mypage-memberEmail").html(member.memberEmail);
    $(".mypage-memberType").html(member.memberCategory);
    $(".mypage-memberPoint").html(member.memberPoint);
}

/* 수강 내역 조회 */
function showMyprogram(){
    mypageService.getMyProgramList(
        memberId, globalThis.page, showMyprogramList
    );
    globalThis.page++;
}

/* 더보기 클릭 시 */
function showMyprogramMore(){
    if(globalThis.page != 0){
        $(".myprogram-more-btn").remove();
    }

    mypageService.getMyProgramList(
        memberId, globalThis.page, showMyprogramList
    );
    globalThis.page++;
}

/* 총 건수 */
function showTotalCount(){
    mypageService.getCountMyprogram(
        memberId, showCountMyprogram
    )
}

function showMyprogramList(myprograms){
    let text = "";
    myprograms.forEach(myprogram => {
        text += `<li data-v-775998d8="" class="byeolbyeol-pay-list-item col-12" onclick="openMyprogramModal(` + myprogram.myprogramId + `)">`;
        text += `<a data-v-775998d8="" href="javascript:;" class="byeolbyeol-pay-list-item-link">`;
        text += `<h4 data-v-775998d8="" style="float: left;">쓰담학교</h4>`;
        text += `<p style="float: right; font-size: 13px;" class="myprogram-date ` + myprogram.myprogramId + `">` + moment(myprogram.programDate).format("YYYY-MM-DD") + `</p>`;
        text += `<div data-v-775998d8="" style="clear: both;" class="byeolbyeol-pay-list-item-info">`;
        text += `<div data-v-0ee3c6fc="" data-v-775998d8="" class="user-profile-picture profile-picture">`;
        text += `<div data-v-0ee3c6fc="" data-name="image" class="is-square myprogram-img ` + myprogram.myprogramId + `" data-src="https://static.cdn.byeolbyeol.com/upload/profile-default/byeolbyeol_19.jpg?h=320&amp;w=320" lazy="loaded" style="background-image: url(` + myprogram.programFileProfileName + `);">`;
        text += `</div>`;
        text += `</div>`;
        text += `<div data-v-775998d8="" class="user-info">`;
        text += `<span data-v-775998d8="" class="name myprogram-name ` + myprogram.myprogramId + `">` + myprogram.programName + `</span>`;
        text += `<span data-v-775998d8="" class="service-name myprogram-place ` + myprogram.myprogramId + `">` + myprogram.programPlace + `</span>`;
        text += `</div>`;
        text += `<span data-v-775998d8="" class="price text-14 myprogram-status ` + myprogram.myprogramId + `">` + myprogram.myprogramStatus + `</span>`;
        text += `</div>`;
        text += `</a>`;
        text += `</li>`;
    });

    if(myprograms.length == 5){
        text += `<button onclick="javascript:showMyprogramMore();" class="myprogram-more-btn" style="width: 100%; height: 70px; background: transparent; border: none; font-size: 12px; font-weight:bold;">더보기</button>`;
    }

    $(".byeolbyeol-pay-list-item-wrap").append(text);
    showTotalCount();
}

function showCountMyprogram(count){
    $(".total-count > strong").html(count);
}

function openMyprogramModal(myprogramId){
    let programName = $(".myprogram-name." + myprogramId);
    let programStatus = $(".myprogram-status." + myprogramId);
    let programPlace = $(".myprogram-place." + myprogramId);
    let programDate = $(".myprogram-date." + myprogramId);
    let imgUrl = $(".myprogram-img." + myprogramId).css('background-image').split('\"')[1];

    $("#programDetailModal").css('display', 'block');

    $(".modal-programName").html(programName.text());
    $(".modal-myprogramStatus").html(programStatus.text());
    $(".modal-programPlace").html(programPlace.text());
    $(".modal-programDate").html(programDate.text());
    $(".myprogram-image").attr('src', imgUrl);
}

function closeMyprogramModal(){
    $("#programDetailModal").css('display', 'none');
}



