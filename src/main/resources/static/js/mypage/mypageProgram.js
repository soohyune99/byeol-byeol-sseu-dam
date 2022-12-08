/* mypageProgram.html */

const memberId = 1;

showMyprogram();

function showMyprogram(){
    mypageService.getMyProgramList(
        memberId, showMyprogramList
    );
}

function showMyprogramList(programs){
    let text = "";

    programs.forEach(program => {
        text += `<li data-v-775998d8="" class="soomgo-pay-list-item col-12">`;
        text += `<a data-v-775998d8="" href="javascript:cancelModalOpen();" class="soomgo-pay-list-item-link">`;
        text += `<h4 data-v-775998d8="" style="float: left;">쓰담학교</h4>`;
        text += `<p style="float: right; font-size: 13px;">` + program.programDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); + `</p>`;
        text += `<div data-v-775998d8="" style="clear: both;" class="soomgo-pay-list-item-info">`;
        text += `<div data-v-0ee3c6fc="" data-v-775998d8="" class="user-profile-picture profile-picture">`;
        text += `<div data-v-0ee3c6fc="" data-name="image" class="is-square" data-src="https://static.cdn.soomgo.com/upload/profile-default/soomgo_19.jpg?h=320&amp;w=320" lazy="loaded" style="background-image: url('http://www.bongchuny.or.kr/upload/editor/se2/3659220484.png');">`;
        text += `</div>`;
        text += `</div>`;
        text += `<div data-v-775998d8="" class="user-info">`;
        text += `<span data-v-775998d8="" class="name">` + program.programName + `</span>`;
        text += `<span data-v-775998d8="" class="service-name">` + program.programPlace + `</span>`;
        text += `</div>`;
        text += `<span data-v-775998d8="" class="price text-14">` + program.programStatus + `</span>`;
        text += `</div>`;
        text += `</a>`;
        text += `</li>`;
    });

    $(".byeolbyeol-pay-list-item-wrap").html(text);
    $(".total-count > span").html(programs.length);
}






