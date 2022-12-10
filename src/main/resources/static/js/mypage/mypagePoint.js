/* mypagePoint.html */

const memberId = 1;
globalThis.page = 0;

showMypoint();

/* 포인트 내역 조회 */
function showMypoint(){
    mypageService.getMypointList(
        memberId, globalThis.page, showMypointList
    );
    globalThis.page++;
}

/* 더보기 클릭 시 */
function showMyprogramMore(){
    if(globalThis.page != 0){
        $(".mypoint-more-btn").remove();
    }

    mypageService.getMypointList(
        memberId, globalThis.page, showMypointList
    );
    globalThis.page++;
}

function showMypointList(mypoints){
    let text = "";

    mypoints.forEach(mypoint => {
        let symbol = mypoint.mypointInout > 0 ? '+' : '-';

        text += `<tr class="content">`;
        text += `<td class="point-date">` + mypoint.createdDate + `</td>`;
        text += `<td>` + mypoint.mypointContent + `</td>`;
        text += `<td class="text-right text-success point-change">` + symbol + mypoint.mypointInout + "포인트" + `</td>`;
        text += `</tr>`;
    });

    if(mypoints.length >= 5){
        text += `<button onclick="javascript:showMypointMore();" class="mypoint-more-btn" style="width: 100%; height: 70px; background: transparent; border: none; font-size: 12px; font-weight:bold;">더보기</button>`;
    }

    $("#shop_mypage_pointlist").append(text);

    console.log(mypoints.length);

    if(!mypoints.length && $("tr.content").length == 0){
        $("#shop_mypage_pointlist_empty").css('display', 'block');
        $(".item-subject").css('display', 'none');
    }
}


