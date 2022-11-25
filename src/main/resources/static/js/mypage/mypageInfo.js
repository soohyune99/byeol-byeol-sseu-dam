/* mypageInfo.js */

const $file = $("#__BVID__268");
const $thumbnail = $("#user-profile");

/* 프로필 사진 변경 시 썸네일 변경 */

$file.on('change', function(e){
    var reader = new FileReader();
    let type = e.target.files[0].type;

    reader.readAsDataURL(e.target.files[0]);

    reader.onload = function(e){
        let url = e.target.result;
        console.log(url);
        console.log(url.includes('image'));

        if(url.includes('image')){
            $thumbnail.attr('src', url);
        } else {
            $thumbnail.attr('src', 'https://www.jigushop.co.kr/common/img/default_profile.png');
        }
    }
});