
// 도시락 버튼 클릭 시

$('.mobliebutton').click(function(){
    console.log("들어옴");
    $('.mobile-usermenu').css('transform', 'translateX(0)');
})

$('.close-button').click(function(){
    $('.mobile-usermenu').css('transform', '');
})