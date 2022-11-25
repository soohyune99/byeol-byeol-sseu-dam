
// 도시락 버튼 클릭 시

$('.mobliebtn').click(function(){
    $('.mobile-usermenu').css('transform', 'translateX(0)');
})

$('.close-button').click(function(){
    $('.mobile-usermenu').css('transform', '');
})