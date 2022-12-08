/* mypageProgram.html */

const memberId = 1;

showMyprogram();

function showMyprogram(){
    mypageService.getMyProgramList(
        memberId, showMyprogramList
    );
}

function showMyprogramList(myprograms){
    let text = "";
    console.log(myprograms);
    myprograms.forEach(myprogram => {
        text += `<li data-v-775998d8="" class="byeolbyeol-pay-list-item col-12">`;
        text += `<a data-v-775998d8="" href="javascript:cancelModalOpen();" class="byeolbyeol-pay-list-item-link">`;
        text += `<h4 data-v-775998d8="" style="float: left;">쓰담학교</h4>`;
        text += `<p style="float: right; font-size: 13px;">` + myprogram.programDate + `</p>`;
        text += `<div data-v-775998d8="" style="clear: both;" class="byeolbyeol-pay-list-item-info">`;
        text += `<div data-v-0ee3c6fc="" data-v-775998d8="" class="user-profile-picture profile-picture">`;
        text += `<div data-v-0ee3c6fc="" data-name="image" class="is-square" data-src="https://static.cdn.byeolbyeol.com/upload/profile-default/byeolbyeol_19.jpg?h=320&amp;w=320" lazy="loaded" style="background-image: url(` + myprogram.programFileProfileName + `);">`;
        text += `</div>`;
        text += `<!--<img class="is-square" src="` + myprogram.programFileProfileName + `">-->`;
        text += `</div>`;
        text += `<div data-v-775998d8="" class="user-info">`;
        text += `<span data-v-775998d8="" class="name">` + myprogram.programName + `</span>`;
        text += `<span data-v-775998d8="" class="service-name">` + myprogram.programPlace + `</span>`;
        text += `</div>`;
        text += `<span data-v-775998d8="" class="price text-14">` + myprogram.myprogramStatus + `</span>`;
        text += `</div>`;
        text += `</a>`;
        text += `</li>`;
    });

    $(".byeolbyeol-pay-list-item-wrap").html(text);
    $(".total-count > strong").html(programs.length);
}






