/* mypagePoint.html */

const memberId = 1;

showMypoint();

function showMypoint(){
    mypageService.getMypointList(
        memberId, showMypointList
    )
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

    $("#shop_mypage_pointlist").html(text);
}


